package iggy.AntiGrief;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiGrief extends JavaPlugin{
	public static AntiGrief plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public final MyBlockListener blockListener = new MyBlockListener(this);
	
	public void onDisable() {
		PluginDescriptionFile pdFile = this.getDescription();
		this.logger.info(pdFile.getName() + " is now disabled");
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_PLACE, this.blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, this.blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_IGNITE, this.blockListener, Event.Priority.Normal, this);
		
		PluginDescriptionFile pdFile = this.getDescription();
		this.logger.info(pdFile.getName() + " version " + pdFile.getVersion() +" is enabled");
	}
}
