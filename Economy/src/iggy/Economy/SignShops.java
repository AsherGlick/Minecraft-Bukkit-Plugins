package iggy.Economy;

import org.bukkit.Bukkit;
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
	// this class does two things, allows admins to place shops
	// and allows players to buy from shops
	// sell signs and buy signs
	@EventHandler (priority = EventPriority.NORMAL)
	public void breakShop(BlockBreakEvent event) {
		if (event.getBlock().getWorld() == Bukkit.getWorld("shopworld")) {
			if (!event.getPlayer().isOp() && !event.getPlayer().hasPermission("economy.editshop")) {
				event.setCancelled(true);
			}
		}
	}
	@EventHandler (priority = EventPriority.NORMAL)
	public void placeShop(BlockPlaceEvent event) {
		if (event.getBlock().getWorld() == Bukkit.getWorld("shopworld")) {
			if (!event.getPlayer().isOp() && !event.getPlayer().hasPermission("economy.editshop")) {
				event.setCancelled(true);
			}
		}
	}
	/******************************* PROTECT BLOCKS *******************************\
	| This function prevents any blocks from being broken inside of the shop world |
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
	| This function prevents any blocks from being broken inside of the shop world |
	| by anyone other then the server operator                                     |
	\******************************************************************************/
	@EventHandler (priority = EventPriority.NORMAL)
	public void BuildProtect (BlockPlaceEvent event){
		Block brokenBlock = event.getBlock();
		if (brokenBlock != null){
			if (economy.shopworld == brokenBlock.getWorld() && !event.getPlayer().isOp()) {
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
	| Still debuggin this too
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
		// this mightneed to be 'Material.SIGN'
		if (clickedBlock.getType() == Material.SIGN_POST || clickedBlock.getType() == Material.WALL_SIGN) {
			economy.info("player click sign");
			Sign clickedSign = (Sign) clickedBlock.getState();
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
			else if (clickedSign.getLine(0).equalsIgnoreCase("[SELL]")){
				//[create a global hash table for players and when they last clicked (maybe what item as well)]
				// check to see when the last click was if it was over 500ms and less then 5000ms then it will sell
				
				// Set the default quantity of the item to be questioned
				int amount = 1;
				Material material;
				
				amount = player.getItemInHand().getAmount();
				material = player.getItemInHand().getType();
				
				long blockPrice = economy.blockPrices.get(material);
				if (blockPrice == -1) {
					player.sendMessage("We cannot buy this item");
				}
				else {
					//Get the percentage of the durability left in the item
					long moneyEarned = amount*blockPrice/2;
					short maxDurability = material.getMaxDurability();
					double earningPercentage = 1.0;
					if( maxDurability != 0 ){
						short itemDurability = (short) (maxDurability - player.getItemInHand().getDurability());
						earningPercentage = (double) itemDurability / (double) maxDurability;
						//Apply the durability fee
						moneyEarned *= earningPercentage;
					}
					//Sell the item
					player.sendMessage("You sold "+amount+" "+material.toString()+ " at "+earningPercentage*100.0+"% durability for "+ChatColor.GREEN+"$"+moneyEarned+ChatColor.WHITE);
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
