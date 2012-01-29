package iggy.helloworld;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorld extends JavaPlugin {
	// Give the plugin a new name within the code
	public static HelloWorld plugin;
	
	// Give the Logger a easy name too
	public final Logger logger = Logger.getLogger("Minecraft");

	// And one more for the player chat listener
	public final ServerChatPlayerListener playerListener = new ServerChatPlayerListener(this);
	
	
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdFile = this.getDescription();
		this.logger.info(pdFile.getName() + " is now disabled.");
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_CHAT, this.playerListener, Event.Priority.Normal, this);
		PluginDescriptionFile pdFile = this.getDescription();
		this.logger.info(pdFile.getName() + " version " + pdFile.getVersion() + " is enabled.");
	}
	
}
