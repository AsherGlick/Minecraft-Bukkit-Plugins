package iggy.Economy;


import java.util.HashMap;
import java.util.Iterator;
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
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

//import net.minecraft.server.CraftWorld;

public class Economy extends JavaPlugin{
	public static Economy plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public Map<String,Long> playerBanks = new HashMap<String,Long>();
	public Map<Material,Long> blockPrices = new HashMap<Material,Long>();
	PluginDescriptionFile pdFile;
	String pluginName;
	String pluginTitle;
	
	@Override
	public void onDisable() {

		saveMoney();
		savePrices();
		this.logger.info(pluginTitle + " version " + pdFile.getVersion() +" is disabled");
	}

	@Override
	public void onEnable() {
		pdFile = this.getDescription();
		pluginName = pdFile.getName();
		pluginTitle = "[\033[0;32m"+pluginName+"\033[0m]";
		loadMoney();
		loadPrices();
		this.logger.info(pluginTitle+ " version " + pdFile.getVersion() +" is enabled");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		
		//World world = player.getWorld();
		
		if (commandLabel.equalsIgnoreCase("buy")){
			if (player == null) {
				logger.info(pluginTitle+" This command can only be run by a player");
				return false;
			}
			if (args.length != 1) {
				player.sendMessage("Correct usage is /buy <blockname>");
			}
		}
		else if (commandLabel.equalsIgnoreCase("sell")){
			if (player == null) {
				logger.info(pluginTitle+" This commmand can only be run by a player");
			}
			player.getItemInHand();
		}
		else if (commandLabel.equalsIgnoreCase("price")){
			if (player == null) {
				logger.info(pluginTitle+" This command can only be run by a player");
			}
			int amount = player.getItemInHand().getAmount();
			Material material = player.getItemInHand().getType();
		}
		else if (commandLabel.equalsIgnoreCase("list")){
			
		}
		else if (commandLabel.equalsIgnoreCase("money")){
			if (args.length == 0){
				if (player == null){
					logger.info(pluginTitle+" You need to type in a player name");
				}
				else {
					long money = getMoney(player.getName());
					player.sendMessage("You have $"+ChatColor.GREEN+money+ChatColor.WHITE+" in the bank");
				}
			}
			else if (args.length == 1){
				if (player == null){
					String playername = getFullPlayerName(args[0]);
					if (playername == null) return false;
					long money = getMoney(playername);
					logger.info(pluginTitle+" "+playername+" has $"+money);
				}
				else if (player.hasPermission("economy.moneymonitor")||player.isOp()) {
					String playername = getFullPlayerName(args[0]);
					if (playername == null) return false;
					long money = getMoney(playername);
					player.sendMessage(playername+" has $"+ChatColor.GREEN+money+ChatColor.WHITE+" in the bank");
				}
			}
		}
		return false;
	}
	
	public String getFullPlayerName (String name) {
		String playername = "";
		Player findplayer = Bukkit.getServer().getPlayer(name);
		if (findplayer == null){
			logger.info(pluginTitle+" No online player found by that name");
			return null;
		}
		else {
			playername = findplayer.getName();
		}
		return playername;
	}
	
	public void saveMoney() {
		this.getConfig().set("banks", "");
		Iterator<Entry<String, Long>> bankIterator =  playerBanks.entrySet().iterator();
		while (bankIterator.hasNext()) {
			Entry<String, Long> pairs = bankIterator.next();
			this.getConfig().set("banks."+pairs.getKey(), pairs.getValue());
		}
		this.saveConfig();
		logger.info(pluginTitle+" Players' accounts saved");
	}
	public void loadMoney() {
		playerBanks.clear();
		ConfigurationSection bankConfig = this.getConfig().getConfigurationSection("banks");
		
		if (bankConfig == null) {
			this.logger.severe(pluginTitle+" Failed to load bank accounts from config (banks section not found)");
			return;
		}
		
		Set<String> players = bankConfig.getKeys(false);
		
		if (players == null) {
			this.logger.severe(pluginTitle+" Failed to load bank accounts from config (No players found)");
			return;
		}
		
		Iterator<String> it = players.iterator();
		while (it.hasNext()) {
			String player = it.next();
			
			long money = this.getConfig().getLong("banks."+player);
			playerBanks.put(player, money);
		}
		logger.info(pluginTitle+" Players' accounts loaded");
	}
	
	
	public void savePrices() {
		this.getConfig().set("blocks", "");
		Iterator<Entry<Material, Long>> blockIterator = blockPrices.entrySet().iterator();
		while (blockIterator.hasNext()) {
			Entry<Material, Long> pairs = blockIterator.next();
			this.getConfig().set("blocks."+pairs.getKey().toString(), pairs.getValue());
		}
		this.saveConfig();
		logger.info(pluginTitle+" Block Prices Saved");
	}
	public void loadPrices() {
		blockPrices.clear();
		for (int i = 0; i < Material.values().length; i++) {
			long price = -1;
			blockPrices.put(Material.values()[i], price);
		}
		
		ConfigurationSection blockConfig = this.getConfig().getConfigurationSection("blocks");
		if (blockConfig == null) {
			this.logger.severe(pluginTitle+" Failed to load Block prices from configuration (Blocks section not found)");
			return;
		}
		
		Set<String> blocks = blockConfig.getKeys(false);
		if (blocks == null) {
			this.logger.severe(pluginTitle+" Failed to load block prices from config (No blocks found)");
			return;
		}
		
		Iterator<String> it = blocks.iterator();
		while (it.hasNext()) {
			String blockname = it.next();
			Material block = Material.getMaterial(blockname);
			if (block == null) {
				this.logger.severe(pluginTitle+" unknown block found in price list ("+blockname+")");
				continue;
			}
			long price = this.getConfig().getLong("blocks."+blockname);
			blockPrices.put(block, price);
		}
		
		
		logger.info(pluginTitle+" Block Prices Loaded");
	}
	//Modify 
	public long getMoney(String player) {
		long playerMoney = 0;
		if (playerBanks.containsKey(player)){
			playerMoney = playerBanks.get(player);
		}
		else {
			playerMoney = createAccount (player);
		}
		return playerMoney;
	}
	public void setMoney(String player, long money) {
		playerBanks.put(player, money);
		saveMoney();
	}
	
	/*
	* returns the ammount of money placed in the new player's account
	*/
	public long createAccount(String player) {
		long money = 500;
		playerBanks.put(player, money);
		saveMoney();
		return money;
	}
	/******************************** CHARGE MONEY ********************************\
	| This function will charge money from the player, it will first check to see  |
	| if the player has enough money and will return true if the money was         |
	| successfully charged to the account. It will return false if it was not. If  |
	| a player account cannot be found then it creates an account with the default |
	| amount of money, then attempts to charge it                                  |
	\******************************************************************************/
	public boolean chargeMoney (Player player, long money) {
		return chargeMoney(player.getName(),money);
	}
	public boolean chargeMoney(String player, long money) {
		long playerMoney = getMoney(player);
		
		if (playerMoney > money) {
			playerBanks.put(player, playerMoney-money);
			info (player+" was charged $"+money);
			saveMoney();
			return true;
		}
		return false;
	}
	/********************************* GIVE MONEY *********************************\
	| This function gives money to the player, it will not first check the ammount |
	| of money in the players account (only a problem if the player has more then  |
	| 9000000000000000 money                                                       |
	\******************************************************************************/
	public boolean giveMoney (Player player,long money) {
		return giveMoney(player.getName(),money);
	}
	public boolean giveMoney (String player,long money) {
		setMoney(player,getMoney(player)+money);
		return false;
	}
	
	// helpfull functions for output
	public void info(String input) {
		this.logger.info(pluginTitle + input);
	}
	public void severe (String input) {
		this.logger.severe(pluginTitle+"\033[31m"+input+"\033[0m");
	}
}
