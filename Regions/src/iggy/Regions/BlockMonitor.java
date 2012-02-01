package iggy.Regions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockMonitor implements Listener{
	Regions plugin;
	BlockMonitor (Regions state) {
		plugin = state;
	}
	//Called when a block is broken by a player. 
	@EventHandler (priority = EventPriority.HIGHEST)
	public void stopBreak (BlockBreakEvent event){
		Position chunk = new Position(event.getBlock().getLocation());
		String chunkName = plugin.chunkNames.get(chunk);
		if (chunkName == null){return;}
		// if code reaches here then the block is in an existing chunk
		if (event.getPlayer() == null) {
			event.setCancelled(true);
			plugin.info("Block break event by non player in "+chunkName+" canceled");
		}
		Owners owners = plugin.chunkOwners.get(chunkName);
		if (owners == null) {
			plugin.severe("Chunk found but owners not! Check config file");
			event.getPlayer().sendMessage("[ERROR LOADING CHUNK OWNERS] tell admin");
			event.setCancelled(true);
		}
		if (!owners.hasOwner(event.getPlayer().getName())){
			event.setCancelled(true);
			event.getPlayer().sendMessage("Don't break other people's stuff!");
		} 
	}
	//Called when a block is destroyed as a result of being burnt by fire. 
	@EventHandler
	public void stopBurn (BlockBurnEvent event){
		
	}
	//Called when a block is ignited
	@EventHandler
	public void stopFire (BlockIgniteEvent event){
		
	}
 	//Called when a block is placed by a player. 
 	@EventHandler
 	public void stopBuild (BlockPlaceEvent event) {
 		
	}
}
