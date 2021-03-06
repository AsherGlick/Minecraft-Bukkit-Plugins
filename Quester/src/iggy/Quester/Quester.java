package iggy.Quester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
		pluginTitle = "[\033[34m"+pluginName+"\033[0m]";
		
		// Activate the on death listener class
		deathListener = new DeathListener(this);
		
		// Output that the plugin has been enabled
		info ("Version " + pdFile.getVersion() +" is enabled");
		loadQuests();
	}
	

	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = null;

		// Stop non-opped players from running these commands
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		
		// The complete quest function
		if (commandLabel.equalsIgnoreCase("completeQuest")){
			if (player != null) {
				if (!player.isOp()) {
					player.sendMessage(ChatColor.RED+"This command can only be run by an Admin" + ChatColor.WHITE);
					return false;
				}
			}
			// completeQuest player questname redstonex redstoney restonez
			if (args.length != 5 && args.length != 6) {
				String output = "Correct Usage: /completeQuest <player> <questname> <red stone x> <red stone y> <red stone z> [repeatable]";
				if (player != null) player.sendMessage(output);
				else info(output);
				return false;
			}
			
			String playername = args[0];
			String questname = args[1];
			final int redstonex = Integer.parseInt(args[2]);
			final int redstoney = Integer.parseInt(args[3]);
			final int redstonez = Integer.parseInt(args[4]);
			boolean repeatable = false;
			if (args.length == 6) {
				if (args[5].equals("yes") || args[5].equals("true")) {
					repeatable = true;
				}
			}
			
			if (completeQuest(playername, questname) || repeatable) {
				// Create the restone torch
				Bukkit.getWorld("world").getBlockAt(redstonex, redstoney, redstonez).setType(Material.REDSTONE_TORCH_ON);
				
				// Remove the redstone torch
				getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					
					public void run() {
						Bukkit.getWorld("world").getBlockAt(redstonex, redstoney, redstonez).setType(Material.AIR);
					}
				}, 20L);
				
			}
			saveQuests();
		}
		if (commandLabel.equalsIgnoreCase("lore")) {
			if (player == null) {
				info ("This command must be run by a player");
				return true;
			}
			if (!player.isOp()) {
				player.sendMessage(ChatColor.RED+"This command can only be run by an Admin" + ChatColor.WHITE);
				return false;
			}
			if (args.length == 0) {
				player.sendMessage("You must specify lore to add to the item");
				return false;
			}
			ItemStack currentItem = player.getItemInHand();
			if (currentItem.getType() == Material.AIR) {
				player.sendMessage("You cannot apply lore to Air");
				return false;
			}
			
			ItemMeta currentItemMeta = currentItem.getItemMeta();
			List<String> newargs = new ArrayList<String>();
			String currentLine = "";
			for (String arg : args) {
				if (arg.equals("\\n")){
					currentLine = currentLine.substring(0, currentLine.length() - 1);
					newargs.add(currentLine);
					currentLine = "";
				}
				else {
					currentLine += arg + " ";
				}
			}
			currentLine = currentLine.substring(0, currentLine.length() - 1);
			newargs.add(currentLine);
			currentItemMeta.setLore(newargs);
			currentItem.setItemMeta(currentItemMeta);
		}
		return false;
	}
	
	
	public boolean completeQuest(String playername, String questname) {
		Set<String> finishedPlayers = questList.get(questname);
		if (finishedPlayers == null) {
			finishedPlayers = new HashSet<String>();
		}
		
		// If the player has already completed the quest
		if (finishedPlayers.contains(playername)) {
			return false;
		}
		// Otherwise add their name to the list of people who have completed the quest
		finishedPlayers.add(playername);
		questList.put(questname, finishedPlayers);
		return true;
	}
	
	// A map containing StringQuestList PlayerCompleteSet
	private Map<String, Set<String>> questList = new HashMap<String,Set<String>>();
	
	public void saveQuests() {
		getConfig().set("quests", "");
		
		Map<String,List<String> > formattedQuestList = new HashMap<String,List<String>>();
		
		// Format the quest data into an easily saveable format
		Iterator<Entry<String, Set<String>>> questIterator = questList.entrySet().iterator();
		while (questIterator.hasNext()){
			
			Entry<String, Set<String>> pair = questIterator.next();
			List <String> completedList = new ArrayList<String>();
			for (String user : pair.getValue()) {
				completedList.add(user);
			}			
			
			formattedQuestList.put(pair.getKey(), completedList);
		}
		
		
		// write the plots by their plotnames at regions.plotname.plots
		Iterator<Entry<String, List<String>>> formattedQuestIterator = formattedQuestList.entrySet().iterator();
		while (formattedQuestIterator.hasNext()) {
			Entry<String, List<String>> pair = formattedQuestIterator.next();
			
			getConfig().set("quests."+pair.getKey()+".completed", pair.getValue());
		}
		
		this.saveConfig();
		info("Quests Saved");
	}
	
	public void loadQuests() {
		questList.clear();
		ConfigurationSection questSection = getConfig().getConfigurationSection("quests");
		if (questSection == null){
			severe("cannot load quests (quests section no found)");
			return;
		}
		Set<String> quests = questSection.getKeys(false);
		if (quests == null){
			severe("cannot load quests (no quests found)");
			return;
		}
		// for each region
		for (String quest : quests){
			 List<String> completedPlayersList = getConfig().getStringList("quests."+quest+".completed");
			 Set<String> completedPlayersSet = new HashSet<String>();
	
			 // Fill the set with all the players who completed the quest
			 if (completedPlayersList != null) {
				 for (String player : completedPlayersList) {
					 completedPlayersSet.add(player);
				 }
			 }
			 
			 questList.put(quest, completedPlayersSet);
		}
		info ("quests loaded");
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
		this.logger.info("" + pluginTitle + " " + input);
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
