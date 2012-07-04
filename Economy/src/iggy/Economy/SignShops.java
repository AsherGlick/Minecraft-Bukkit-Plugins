package iggy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SignShops implements Listener{
	
	static Economy economy;
	SignShops (Economy instance) {
		economy = instance;
	}
	
	/******************************* PROTECT BLOCKS *******************************\
	| This function prevents any blocks from being BROKEN inside of the shop world |
	| by anyone other then the server operator                                     |
	\******************************************************************************/
	@EventHandler (priority = EventPriority.NORMAL)
	public void BlockProtect (BlockBreakEvent event){
		Block brokenBlock = event.getBlock();
		if (brokenBlock != null){
			if (economy.shopworld == brokenBlock.getWorld() && !event.getPlayer().isOp()) {
				event.setCancelled(true);
			}
		}
	}
	
	/******************************* PROTECT BLOCKS *******************************\
	| This function prevents any blocks from being PLACED inside of the shop world |
	| by anyone other then the server operator                                     |
	\******************************************************************************/
	@EventHandler (priority = EventPriority.NORMAL)
	public void BuildProtect (BlockPlaceEvent event){
		Block placedBlock = event.getBlock();
		if (placedBlock != null){
			if (economy.shopworld == placedBlock.getWorld() && !event.getPlayer().isOp()) {
				event.setCancelled(true);
			}
		}
	}
	
	/********************************* PLACE SIGN *********************************\
	| The place sign function handles the placement of the [SHOP] sign as well as  |
	| the [SELL] sign. When the shop sign is places the function searches for the  |
	| item number that it is placed with to see if it corrisponds to an actual     |
	| block, if it does then the name of the block is displayed and the sign is    |
	| placed.
	\******************************************************************************/
	@EventHandler (priority = EventPriority.NORMAL)
	public void placeSign(SignChangeEvent event) {
		Player player = event.getPlayer();
		if (player.getWorld() != economy.shopworld){
			return;
		}
		
		// If the sign is a [SHOP] or a [SHOP-STACK] sign
		if (event.getLine(0).equalsIgnoreCase("[SHOP]") || event.getLine(0).equalsIgnoreCase("[SHOP-STACK]")){
			if (event.getPlayer().isOp() || event.getPlayer().hasPermission("economy.makeshop")) {
				Material foundMaterial = Material.matchMaterial(event.getLine(1));
				if (foundMaterial == null){
					event.setLine(2, "FOUND");
					event.setLine(1, "NOT");
					event.setLine(0, "");
				}
				else{
					event.setLine(2, foundMaterial.name());
				}
			}
			else {
				event.setCancelled(true);
			}
		}
		
		// If the sign is a [SELL] sign
		if (event.getLine(0).equalsIgnoreCase("[SELL]")) {
			if (event.getPlayer().isOp() || event.getPlayer().hasPermission("economy.makeshop")) {
				event.setLine(1, ""+ChatColor.GOLD);
				event.setLine(2, "ALL THE THINGS");
				event.setLine(3, ""+ChatColor.BLACK);
			}
			else {
				event.setCancelled(true);
			}
		}
	}

	/********************************* CLICK SIGN *********************************\
	| This function is triggered whenever a sign is clicked. It reacts when a      |
	| [SELL] or a [SHOP] sign is clicked with the left mouse button
	\******************************************************************************/
	@EventHandler (priority = EventPriority.NORMAL)
	public void clickSign(PlayerInteractEvent event){
		if (event.getAction() != Action.LEFT_CLICK_BLOCK){
			return;	
		}
		Player player = event.getPlayer();
		if (player.getWorld() != economy.shopworld){
			return;
		}
		Block clickedBlock = event.getClickedBlock();
		if (clickedBlock == null) {
			return;
		}

		
		if (clickedBlock.getType() == Material.SIGN_POST || clickedBlock.getType() == Material.WALL_SIGN) {
			economy.info("player click sign");
			Sign clickedSign = (Sign) clickedBlock.getState();
			
			// If the player is trying to buy materials
			if (clickedSign.getLine(0).equalsIgnoreCase("[SHOP]") || clickedSign.getLine(0).equalsIgnoreCase("[SHOP-STACK]")) {
				
				int quantity = 1;
				if (clickedSign.getLine(0).equalsIgnoreCase("[SHOP-STACK]")) {
					quantity = 64;
				}
				
				Material purchaceMaterial = Material.matchMaterial(clickedSign.getLine(1));
				//find price
				long price;
				if (economy.blockPrices.containsKey(purchaceMaterial)){
					price = economy.blockPrices.get(purchaceMaterial) * quantity;
				}
				else {
					event.getPlayer().sendMessage("There was an error with this sign, contact the administration");
					return;
				}
				if (price < 0) {
					event.getPlayer().sendMessage("This block cannot be bought");
				}
				else if (economy.chargeMoney(event.getPlayer(), price)) {
					ItemStack item = new ItemStack(purchaceMaterial, quantity);
					event.getPlayer().getInventory().addItem(item);
					event.getPlayer().sendMessage("You just bought "+ quantity + " " +purchaceMaterial.name()+" for "+ChatColor.GREEN+"$"+price+ChatColor.WHITE);
				}
				else {
					event.getPlayer().sendMessage("You need "+ChatColor.GREEN+"$"+price+ChatColor.WHITE+" to buy "+quantity+" "+purchaceMaterial.name());
				}
				// cancel the event so nothing else happens
				event.setCancelled(true);
			}
			
			// If the player is trying to sell materials
			else if (clickedSign.getLine(0).equalsIgnoreCase("[SELL]")){				
				// Set the default quantity of the item to be questioned
				int amount = 1;
				Material material;
				
				String printedAmount = "a";
				String printedDurability = "";
				
				amount = player.getItemInHand().getAmount();
				material = player.getItemInHand().getType();
				// If the player is not holding anything dont bother to try to sell it
				if (material == Material.AIR){return;}
				
				long blockPrice = economy.blockPrices.get(material);
				if (blockPrice == -1) {
					player.sendMessage("We cannot buy this item");
				}
				else {
					// If the item has durability then modify the price based on how much durability is left
					long moneyEarned = amount*blockPrice/2;
					short maxDurability = material.getMaxDurability();
					double earningPercentage = 1.0;
					if( maxDurability != 0 ){
						short itemDurability = (short) (maxDurability - player.getItemInHand().getDurability());
						earningPercentage = (double) itemDurability / (double) maxDurability;
						// Apply the durability fee
						moneyEarned *= earningPercentage;
						// Include durability modification in the sell notification message
						printedDurability = " at "+earningPercentage*100.0+"% durability";
					}
					// If the user is selling more then one then change displayed amount
					if (amount > 1) { printedAmount = Integer.toString(amount);}
					// Display the sell notification to the user
					player.sendMessage("You sold " + printedAmount + " " + material.toString() + printedDurability + " for " + ChatColor.GREEN + "$" + moneyEarned + ChatColor.WHITE);
					
					// Remove the item and give the player money
					economy.giveMoney(player, moneyEarned);
					ItemStack item = player.getItemInHand();
					item.setType(Material.AIR);
					item.setAmount(0);
					player.setItemInHand(item);
					
				}
				event.setCancelled(true);
				
			}
		}
	}
}
