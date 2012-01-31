package iggy.Regions;

import java.util.logging.Logger;

import iggy.Economy.Economy;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;
import org.dynmap.markers.MarkerAPI;

public class Regions extends JavaPlugin{
	Logger logger = Logger.getLogger("Minecraft");
	
	String pluginTitle;
	PluginDescriptionFile pdFile;
	
	Plugin dynmap;
	Plugin economy;
	DynmapAPI dynmapapi;
	MarkerAPI markerapi;
	Economy economyapi;
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		info(" Version " + pdFile.getVersion() +" is disabled");
	}

	@Override
	public void onEnable() {
		pdFile = this.getDescription();
		String pluginName = pdFile.getName();
		pluginTitle = "[\033[2;33m"+pluginName+"\033[0m]";
		// TODO Auto-generated method stub
		PluginManager pm = getServer().getPluginManager();
		dynmap = pm.getPlugin("dynmap");
		economy = pm.getPlugin("Economy");
		if (dynmap == null){
			severe("cannot find dynmap");
			return;
		}
		if (economy == null) {
			severe("cannot find economy");
			return;
		}
		dynmapapi = (DynmapAPI) dynmap;
		info("Loaded Dynmap");
		economyapi = (Economy) economy;
		info ("Loaded Economy");
		
		info (" Version " + pdFile.getVersion() +" is enabled");
	}
	
	public void info(String input) {
		this.logger.info(pluginTitle + input);
	}
	public void severe (String input) {
		this.logger.severe(pluginTitle+"\033[31m"+input+"\033[0m");
	}
	
}
