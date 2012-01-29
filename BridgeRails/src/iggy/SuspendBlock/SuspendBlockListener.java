package iggy.SuspendBlock;


import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;



public class SuspendBlockListener extends BlockListener{
	public static SuspendBlock plugin;

	public SuspendBlockListener (SuspendBlock instance){
		plugin = instance;
	}
	
	public void onBlockEvent (BlockEvent event) {
		if (plugin.suspended.contains(event.getBlock().getLocation()));
	}
	public void onPistonRetract (BlockPistonExtendEvent event) {
		//event.
	}
	public void onPistonExtend (BlockPistonRetractEvent event) {
		if (plugin.suspended.contains(event.getRetractLocation())){
			event.setCancelled(true);
		}
	}
	/*
	public void onBlockPhysics (BlockPhysicsEvent event) {
		// if the block is in the protected list ignore the event
		if (plugin.suspended.containsKey(event.getBlock().getLocation())) {
			event.setCancelled(true);
		}
	}
	public void onBlockBreak (BlockBreakEvent event) {
		// if block is in the protected list ignore the event
		if (plugin.suspended.containsKey(event.getBlock().getLocation())){
			event.setCancelled(true);
		}
	}
	public void onBlockDamage (BlockDamageEvent event){
		if(true){}
	}*/
}
