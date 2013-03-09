package iggy.Quester;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;



public class Quester extends JavaPlugin{
	Logger logger = Logger.getLogger("Minecraft");
	
	String pluginTitle;
	PluginDescriptionFile pdFile;
	
	DeathListener deathListener;
  //////////////////////////////////////////////////////////////////////////////
 ////////////////////////////// ENABLE / DISABLE //////////////////////////////
//////////////////////////////////////////////////////////////////////////////
	@Override
	public void onDisable() {
		info(" Version " + pdFile.getVersion() +" is disabled");
	}
	
	@Override
	public void onEnable () {
		pdFile = this.getDescription();
		String pluginName = pdFile.getName();
		pluginTitle = "[\033[2;35m"+pluginName+"\033[0m]";
		
		deathListener = new DeathListener(this);
		
		info ("Version " + pdFile.getVersion() +" is enabled");
	}
	

	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		
		info (player.getName() + " tried to run the command " + commandLabel);
		
		if (!player.isOp()) {
			player.sendMessage(ChatColor.RED+"This command can only be run by an Admin" + ChatColor.WHITE);
			return false;
		}
		
		//World world = player.getWorld();
		
		if (commandLabel.equalsIgnoreCase("GODMODE")){
			
		}
		return false;
	}
  //////////////////////////////////////////////////////////////////////////////
 /////////////////////////////// DISPLAY HELPERS //////////////////////////////
//////////////////////////////////////////////////////////////////////////////
	/********************************** LOG INFO **********************************\
	| The log info function is a simple function to display info to the console    |
	| logger. It also prepends the plugin title (with color) to the message so     |
	| that the plugin that sent the message can easily be identified               |
	\******************************************************************************/
	public void info(String input) {
		this.logger.info("  " + pluginTitle + " " + input);
	}
	
	/********************************* LOG SEVERE *********************************\
	| The log severe function is very similar to the log info function in that it  |
	| displays information to the console, but the severe function sends a SEVERE  |
	| message instead of an INFO. It also turns the message text red               |
	\******************************************************************************/
	public void severe (String input) {
		this.logger.severe(pluginTitle+" \033[31m"+input+"\033[0m");
	}
}
