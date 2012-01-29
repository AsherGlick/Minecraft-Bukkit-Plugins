package iggy.SuspendBlock;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

//import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SuspendBlock extends JavaPlugin {
	public static SuspendBlock plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public final SuspendBlockListener blockListen = new SuspendBlockListener(this);
	public final SuspendPlayerListener playerListen = new SuspendPlayerListener(this);
	
	public Set<Location> suspended = new HashSet<Location>(); // list of suspended blocks
	public Map<Player, Boolean> suspendEnable = new HashMap<Player, Boolean>();// list of players actively suspending blocks
	
	public void onDisable() {
		PluginDescriptionFile pdFile = this.getDescription();
		this.logger.info(pdFile.getName() + " is now disabled");
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvent(Event.Type.BLOCK_BREAK, this.blockListen, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BURN, this.blockListen, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_DAMAGE, this.blockListen, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_FADE, this.blockListen, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_IGNITE, this.blockListen, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_PHYSICS,this.blockListen, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.REDSTONE_CHANGE, this.blockListen, Event.Priority.Normal, this);
		
		
		
		
		//pm.registerEvent(Event.Type.PLAYER_INTERACT, this.playerListen, Event.Priority.Normal, this);
		
		PluginDescriptionFile pdFile = this.getDescription();
		this.logger.info(pdFile.getName() + " version " + pdFile.getVersion() +" is enabled");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		
		if (player == null) {
			this.logger.info("This command can only be run by a player");
			return false;
		}
		
		//World world = player.getWorld();
		
		if (commandLabel.equalsIgnoreCase("phasein") || commandLabel.equalsIgnoreCase("pi")){
			if (player.isOp() || (player.hasPermission("suspend.phasein"))){
					
			}
			else {
				player.sendMessage("You dont have permissions to do this");
			}
		}
		if (commandLabel.equalsIgnoreCase("phaseout") || commandLabel.equalsIgnoreCase("po")){
			if(player.isOp() || player.hasPermission("suspend.phaseout")) {
				Block block = player.getTargetBlock(null, 50);
				this.suspended.add(block.getLocation());
				player.sendMessage("added " + block.getType().toString() + " to the phased out");
			}
			else {
				player.sendMessage("You dont have permissions to do this");
			}
			
		}
		return false;
	}
}
