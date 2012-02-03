package iggy.Economy;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class ItemSelector implements Listener {
	Economy plugin;
	ItemSelector(Economy instance) {
		plugin = instance;
	}
	
	public void placeItem(Location location,Material material){
		Item item = location.getWorld().dropItem(location, new ItemStack(material));
		item.setPickupDelay(2147483647);
		plugin.itemPlacements.put(item, location);
	}
	
	
	// click the item
	@EventHandler(priority = EventPriority.NORMAL)
	public void playerClickItem (PlayerInteractEntityEvent event){
		if (event.getRightClicked() instanceof Item) {
			Item clicked = (Item) event.getRightClicked();
			if (plugin.itemPlacements.containsKey(clicked)){
				event.getPlayer().sendMessage("You clicked a "+clicked.getEntityId());
			}
		}
	}
	
	// respawn the item
}
