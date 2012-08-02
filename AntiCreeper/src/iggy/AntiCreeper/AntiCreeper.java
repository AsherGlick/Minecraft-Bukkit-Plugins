package iggy.AntiCreeper;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiCreeper extends JavaPlugin{
	public static AntiCreeper plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public final CreeperBlocker creeperBlocker = new CreeperBlocker();
	
	PluginDescriptionFile pdFile;
	String pluginName;
	String pluginTitle;
	
	
	public void onDisable() {
		info(" is now disabled");
	}
		
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.creeperBlocker, this);
		
		
		pdFile = this.getDescription();
		pluginName = pdFile.getName();
		pluginTitle = "[\033[0;32m"+pluginName+"\033[0m]";
		
		
		
		info(" version " + pdFile.getVersion() +" is enabled");
	}
	
	
  //////////////////////////////////////////////////////////////////////////////
 /////////////////////////////// DISPLAY HELPERS //////////////////////////////
//////////////////////////////////////////////////////////////////////////////
	public void info(String input) {
		this.logger.info("  "+pluginTitle + input);
	}
	public void severe (String input) {
		this.logger.severe(pluginTitle+"\033[31m"+input+"\033[0m");
	}
	
}
