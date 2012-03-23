package iggy.Teleport;

import java.util.Date;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class TeleportPlayerListener implements Listener {
	public static Teleport plugin;
	
	public TeleportPlayerListener (Teleport instance) {
		plugin = instance;
		
	}
	
	
	
	public void jump(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Date nowdate = new Date();
		Date thendate = plugin.lastJump.put(player, nowdate);
		
		// prevent rapid jumping
		long nowtime = nowdate.getTime();
		long thentime = 0;
		if (thendate != null){thentime = thendate.getTime();}
		if ((nowtime - thentime) < 500) {return;}
		
		// make sure the player has the right stuff
		PlayerInventory inventory = player.getInventory();
		if (event.getPlayer().getItemInHand().getType() == Material.COMPASS){
			if (inventory.contains(Material.FEATHER)) {
				World world = event.getPlayer().getWorld();
				//look for block
				Block targetBlock = null;
				
				List<Block> blocklist = player.getLineOfSight(null, 200);
				
				double lastheight = player.getLocation().getY();
				for (int i = 0; i < blocklist.size(); i++) {
					if (blocklist.get(i).getY() < lastheight-1) {
						plugin.logger.info("looped, error" + blocklist.size());
						continue;
					}
					if (blocklist.get(i).getType() != Material.AIR) {
						targetBlock = blocklist.get(i);
						plugin.logger.info("Block was element " + i + " player at" + player.getLocation().getY());
						break;
					}
					lastheight = blocklist.get(i).getY();
				}
				
				
				if (targetBlock == null){
					event.getPlayer().sendMessage("No Block found");
					return;
				}
				
				// Get the location of the block
				Location blockLocation = targetBlock.getLocation();
				
				int testBlockHeight = blockLocation.getBlockY();
				
				
				//get the next open space above it
				while(testBlockHeight < (world.getMaxHeight())) {
					Block blocka = world.getBlockAt(blockLocation.getBlockX(), testBlockHeight, blockLocation.getBlockZ());
					Block blockb = world.getBlockAt(blockLocation.getBlockX(), testBlockHeight-1, blockLocation.getBlockZ());
					if (blocka.getType() == Material.AIR && blockb.getType() == Material.AIR){
						player.sendMessage("Found a point!" + targetBlock.getType().toString() + "at" + targetBlock.getY());
						break;
					}
					testBlockHeight++;
				}
				
				// teleport
				Location teleLocation = new Location (world,blockLocation.getBlockX()+0.5, testBlockHeight, blockLocation.getBlockZ()+0.5,player.getLocation().getYaw(),player.getLocation().getPitch());
				player.teleport(teleLocation);
				player.setFallDistance(0);
				ItemStack[] tempInvin = inventory.getContents();
				for (int i = 0; i < tempInvin.length; i++) {
					if (tempInvin[i] == null) continue;
					if (tempInvin[i].getType() == Material.FEATHER) {
						tempInvin[i].setAmount(tempInvin[i].getAmount()-1);
						break;
					}
				}
				inventory.setContents(tempInvin);
			}
			else {
				player.sendMessage("You need to have a feather in order to teleport");
			}
		}
	}
	
	/******************************** PLAYER CLICK ********************************\
	| This function is used when a player left clicks in the game. If they click   |
	| on a sign that is an activator for a city then they will not teleport but    |
	| instead they will activate the city. It will also change where the compass   |
	| is pointing.                                                                 |
	\******************************************************************************/
	@EventHandler(priority = EventPriority.NORMAL)
	public void playerClick (PlayerInteractEvent event){
		if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (plugin.cityActivators.containsKey(event.getClickedBlock().getLocation())) {
				BlockState blockState = event.getClickedBlock().getState();
				Sign sign = (Sign)blockState;
				
				String city = plugin.cityActivators.get(event.getClickedBlock().getLocation());
				String player = event.getPlayer().getName();
				if (!plugin.addActivation (player,city)) {
					event.getPlayer().setCompassTarget(plugin.cityTeleports.get(city));
				}
				
			}
			else{
				jump(event);
			}
		}
		else if (event.getAction() == Action.LEFT_CLICK_AIR) {
			jump(event);
		}
	}
	/********************************* PLAYER MOVE ********************************\
	| When the player moves, if it is in the hash table then remove it from the    |
	| table and re-send the block                                                  |
	\******************************************************************************/
	@EventHandler (priority = EventPriority.NORMAL)
	public void playerMove (PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (plugin.teleportingPlayers.containsKey(player)) {
			Location oldPosition = event.getFrom();
			plugin.teleportingPlayers.remove(player);
			
			Block block = player.getWorld().getBlockAt(oldPosition);
			player.sendBlockChange(oldPosition, block.getType(), block.getData());
		}
	}
}
