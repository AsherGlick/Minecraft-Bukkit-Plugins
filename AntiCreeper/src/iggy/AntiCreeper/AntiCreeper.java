package iggy.AntiCreeper;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiCreeper extends JavaPlugin{
	public static AntiCreeper plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public final CreeperBlocker creeperBlocker = new CreeperBlocker();
	
	public void onDisable() {
		PluginDescriptionFile pdFile = this.getDescription();
		this.logger.info(pdFile.getName() + " is now disabled");
	}
		
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.creeperBlocker, this);
		
		PluginDescriptionFile pdFile = this.getDescription();
		this.logger.info(pdFile.getName() + " version " + pdFile.getVersion() +" is enabled");
	}
}
