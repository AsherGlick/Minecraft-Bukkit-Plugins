package iggy.Regions;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import iggy.Economy.Economy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
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
	
	public Map<Position,String> chunkNames = new HashMap<Position,String>();
	public Map<String,Owners> chunkOwners = new HashMap<String,Owners>();
	
	BlockMonitor pluginMonitor = new BlockMonitor(this);
	
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
		
		//set up all the block listeners to prevent distruction
		if (dynmap.isEnabled() && economy.isEnabled()){
			activate();
		}
		else {
			
			getServer().getPluginManager().registerEvents(new OurServerListener(), this);
			info("Waiting for Dynmap and Economy to be enabled");
		}
		
	}
	
	// listener class to wait for the other plugins to enable
	private class OurServerListener implements Listener {
		// warnings are suppressed becasue this is called using registerEvents when the 
		@SuppressWarnings("unused")
		// required plugins are not enabled on start
		@EventHandler (priority = EventPriority.MONITOR)
        public void onPluginEnable(PluginEnableEvent event) {
            Plugin p = event.getPlugin();
            String name = p.getDescription().getName();
            if(name.equals("dynmap") || name.equals("Economy")) {
                if(dynmap.isEnabled() && economy.isEnabled())
                    activate();
            }
        }
    }

	// funtion to finish activating the plugin once the other plugins are enabled
	public void activate(){
		info (" Version " + pdFile.getVersion() +" is enabled");
	}
	
	
	
	
	// helper functions for output
	public void info(String input) {
		this.logger.info(pluginTitle + input);
	}
	public void severe (String input) {
		this.logger.severe(pluginTitle+"\033[31m"+input+"\033[0m");
	}
	
}
