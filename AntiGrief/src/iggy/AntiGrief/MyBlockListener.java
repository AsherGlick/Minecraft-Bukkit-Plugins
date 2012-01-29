package iggy.AntiGrief;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class MyBlockListener extends BlockListener{
	public static AntiGrief plugin;
	
	// create an array of blocks that will be un-peaceable 
	public static Material[] placedBlacklist = {Material.TNT, Material.LAVA_BUCKET};
	public static Material[] destroyedBlacklist = {Material.TNT};
	
	public MyBlockListener(AntiGrief instance) {
		plugin = instance;
	}

	public void onBlockPlace (BlockPlaceEvent event) {
		Material block = event.getBlock().getType();
		Player player = event.getPlayer();
		
		// this can be used to check to see if a player is op or has a permissions system
		//if (player.isOp() || player.hasPermission("AntiGrief.place")) {}
		
		for (Material blockedBlock : placedBlacklist ) {
			if (blockedBlock == block) {
				player.sendMessage(ChatColor.YELLOW + "I'm afraid I can't let you do that " + player.getDisplayName());
				event.setCancelled(true);
			}
		}
	}
	
	public void onBlockBreak (BlockBreakEvent event) {
		Material block = event.getBlock().getType();
		Player player = event.getPlayer();
		
		// this can be used to check to see if a player is op or has a permissions system
		//if (player.isOp() || player.hasPermission("AntiGrief.place")) {}
		
		for (Material blockedBlock : destroyedBlacklist ) {
			if (blockedBlock == block) {
				player.sendMessage(ChatColor.YELLOW + "I'm afraid I can't let you do that " + player.getDisplayName());
				event.setCancelled(true);
			}
		}
	}
	
	public void onBlockIgnite (BlockIgniteEvent event) {
		if (event.getPlayer() != null) {
			Player player = event.getPlayer();
			player.sendMessage(ChatColor.YELLOW + "I'm afraid I can't let you do that " + player.getDisplayName());
		}
		else {
			Logger.getLogger("Minecraft").info("[ANTI-GRIEF] Ignition not by player");
		}
		event.setCancelled (true);
	}
}
