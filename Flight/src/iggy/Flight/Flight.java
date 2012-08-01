package iggy.Flight;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Flight extends JavaPlugin{
	public static Flight plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdFile;
	String pluginName;
	String pluginTitle;
	
	Map<String, Integer> flightTimeRemaining = new HashMap<String, Integer>();
	
	/********************************** ON ENABLE *********************************\
	|
	\******************************************************************************/
	@Override
	public void onEnable() {
		pdFile = this.getDescription();
		pluginName = pdFile.getName();
		pluginTitle = "[\033[0;36m"+pluginName+"\033[0m]";
		
		info(" version " + pdFile.getVersion() +" is enabled");
		
		
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for (Entry<String, Integer> entry : flightTimeRemaining.entrySet()) {
					Player player = getServer().getPlayer(entry.getKey());
					Integer timeLeft = entry.getValue();
					timeLeft--;
					if ((timeLeft % 6) == 0) {
						Integer minutesLeft = timeLeft/6;
						player.sendMessage("" + minutesLeft + " minutes remaining in flight");
					}
					
					//if (timeLeft <= 5 && timeLeft > 0) {
						player.sendMessage(""+(timeLeft*10)+" seconds remaining in flight");
					//}
					
					if (timeLeft == 0) {
						player.sendMessage("Flight Time Up");
						player.setAllowFlight(false);
					}
					
					else {
						flightTimeRemaining.put(entry.getKey(), timeLeft);
					}
				}
			}
		}, 0L, 200L);

		
	}
	
	/********************************* ON DISABLE *********************************\
	|
	\******************************************************************************/
	@Override
	public void onDisable() {
		this.saveConfig();
		info(" version " + pdFile.getVersion() +" is disabled");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		
		if (player==null) { info ("Only a player can run this command"); }
		
		if (commandLabel.equalsIgnoreCase("fly")) {
			
			ItemStack boots = player.getInventory().getBoots();
			
			String errorMessage = "You are not wearing boots with featherfall";
			
			if (boots == null) {
				player.sendMessage(errorMessage);
			}
			
			if (boots.containsEnchantment(Enchantment.PROTECTION_FALL)) {
				player.sendMessage("Flying Enabled for " + "2" + " minutes");
				
				player.setAllowFlight(true);
				
				flightTimeRemaining.put(player.getName(), 120);
				
			}
			else {
				player.sendMessage(errorMessage);
			}
		}
		
		return false;
	}
	
	
	
  //////////////////////////////////////////////////////////////////////////////
 /////////////////////////////// DISPLAY HELPERS //////////////////////////////
//////////////////////////////////////////////////////////////////////////////
	public void info(String input) {
		this.logger.info(pluginTitle + input);
	}
	public void severe (String input) {
		this.logger.severe(pluginTitle+"\033[31m"+input+"\033[0m");
	}
}
