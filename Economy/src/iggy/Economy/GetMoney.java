package iggy.Economy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class GetMoney implements Listener{
	Economy plugin;
	GetMoney (Economy instance) {
		plugin = instance;
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void breakMoney (BlockBreakEvent event) {
		plugin.giveMoney(event.getPlayer(), 5);
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void placeMoney (BlockPlaceEvent event) {
		plugin.giveMoney(event.getPlayer(), 1);
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void killMob (EntityDeathEvent event) {
		
	}
}
