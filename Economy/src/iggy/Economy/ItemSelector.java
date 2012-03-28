package iggy.Economy;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
//import org.bukkit.block.Block;
import org.bukkit.entity.Item;
//import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
//import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ItemSelector implements Listener {
	Economy plugin;
	
	public Map<Item,Location> itemPlacements = new HashMap<Item,Location>();
	
	ItemSelector(Economy instance) {
		plugin = instance;
	}
	
	public void placeItem(Location location,Material material){
		Item item = location.getWorld().dropItem(location, new ItemStack(material));
		item.setVelocity(new Vector(0,0,0));
		item.setPickupDelay(2147483647);
		itemPlacements.put(item, location);
	}
	
	
	// click the item
	@EventHandler(priority = EventPriority.NORMAL)
	public void playerClickItem (PlayerInteractEntityEvent event){
		plugin.info("click");
		if (event.getRightClicked() instanceof Item) {
			plugin.info("ITEM!");
			Item clicked = (Item) event.getRightClicked();
			if (itemPlacements.containsKey(clicked)){
				event.getPlayer().sendMessage("You clicked a "+clicked.getEntityId());
			}
		}
		event.getPlayer().getLineOfSight(new HashSet<Byte>(), 6);
	}
	
	// new click the item (removed until future versions)
	/*
	@EventHandler(priority = EventPriority.NORMAL)
	public void playerclick (PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getItemInHand().getType() != Material.APPLE) {
			return;
		}
		plugin.info("launched LOS");
		Location eye = player.getEyeLocation();
		double eyex = eye.getX();
		double eyey = eye.getY();
		double eyez = eye.getZ();
		List<Block> blocks = player.getLineOfSight( new HashSet<Byte>() , 8);
		player.sendMessage("there were "+blocks.size()+" blocks selected");
		List<QuarterBlock> miniblocks = new ArrayList<QuarterBlock>();
		
		player.sendMessage(player.getLocation().getYaw() + "," + player.getLocation().getPitch());
		
		for (int i = 0; i < blocks.size(); i++) {
			
			
			
			Location blocklocation = blocks.get(i).getLocation();
			
			double delx = blocklocation.getX() - eyex;
			double dely = blocklocation.getY() - eyey;
			double delz = blocklocation.getZ() - eyez;
			
			blocklocation.setX((delx/2)+eyex);
			blocklocation.setY((dely/2)+eyey);
			blocklocation.setZ((delz/2)+eyez);
			
			miniblocks.add(new QuarterBlock(blocklocation));
		}
		
		for (int i = 0; i < miniblocks.size(); i++){
			Location loc = miniblocks.get(i).getLocation();
			placeItem(loc,Material.GOLD_INGOT);
			plugin.info("placed ingot at"+loc);
		}
		plugin.info("finished LOS miniblock");
	}*/
}
