package iggy.Economy;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SignShops implements Listener{
	Economy plugin;
	SignShops (Economy instance) {
		plugin = instance;
	}
	// this class does two things, allows admins to place shops
	// and allows players to buy from shops
	// sell signs and buy signs
	
	//
	/********************************* PLACE SIGN *********************************\
	| Still debuggin this function
	\******************************************************************************/
	@EventHandler (priority = EventPriority.NORMAL)
	public void placeSign(SignChangeEvent event) {
		plugin.info("sign placed");
		Sign placedSign = (Sign) event.getBlock().getState();
		if (placedSign.getLine(0) == "[SHOP]"){
			if (event.getPlayer().isOp() || event.getPlayer().hasPermission("economy.makeshop")) {
				Material foundMaterial = Material.matchMaterial(placedSign.getLine(1));
				if (foundMaterial == null){
					placedSign.setLine(2, "FOUND");
					placedSign.setLine(1, "NOT");
					placedSign.setLine(0, "");
				}
				else{
					placedSign.setLine(2, foundMaterial.name());
				}
			}
			else {
				event.setCancelled(true);
			}
		}
	}
	// click sign
	/********************************* CLICK SIGN *********************************\
	| Still debuggin this too
	\******************************************************************************/
	@EventHandler (priority = EventPriority.NORMAL)
	public void clickSign(PlayerInteractEvent event){
		plugin.info("player click");
		Block clickedBlock = event.getClickedBlock();
		if (clickedBlock == null) {
			return;
		}
		// this might need to be 'Material.SIGN'
		if (clickedBlock.getType() == Material.SIGN_POST || clickedBlock.getType() == Material.WALL_SIGN) {
			plugin.info("player click sign");
			Sign clickedSign = (Sign) clickedBlock.getState();
			if (clickedSign.getLine(0) == "[SHOP]") {
				Material purchaceMaterial = Material.matchMaterial(clickedSign.getLine(1));
				//find price
				long price;
				if (plugin.blockPrices.containsKey(purchaceMaterial)){
					price = plugin.blockPrices.get(purchaceMaterial);
				}
				else {
					event.getPlayer().sendMessage("There was an error with this sign, contact the administration");
					return;
				}
				if (plugin.chargeMoney(event.getPlayer(), price)) {
					ItemStack item = new ItemStack(purchaceMaterial, 1);
					event.getPlayer().getInventory().addItem(item);
					event.getPlayer().sendMessage("You just bought some "+purchaceMaterial.name()+" for $"+price);
				}
				else {
					event.getPlayer().sendMessage("You need $"+price+" to buy 1 "+purchaceMaterial.name());
				}
			}
			else if (clickedSign.getLine(0) == "[SELL]"){
				//[create a global hash table for players and when they last clicked (maybe what item as well)]
				// check to see when the last click was if it was over 500ms and less then 5000ms then it will sell
			}
		}
	}
	
	
}
