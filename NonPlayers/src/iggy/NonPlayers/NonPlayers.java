package iggy.NonPlayers;



import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.topcat.npclib.NPCManager;

public class NonPlayers extends JavaPlugin{
	public static NonPlayers plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdFile;
	String pluginName;
	String pluginTitle;
	
	
	
	
	NPCManager manager;
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		this.logger.info(pluginTitle + " version " + pdFile.getVersion() +" is disabled");
	}

	@Override
	public void onEnable() {
		manager = new NPCManager(this);
		pdFile = this.getDescription();
		pluginName = pdFile.getName();
		pluginTitle = "[\033[0;43m"+pluginName+"\033[0m]";
		this.logger.info(pluginTitle+ " version " + pdFile.getVersion() +" is enabled");
		// TODO Auto-generated method stub
		Location point = new Location (this.getServer().getWorld("world"),0,1,0);
		Player iggy = (Player) manager.spawnHumanNPC("Iggystlev", point).getBukkitEntity();
		iggy.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
		String players[] = {"A Guard","Guard", "Notch", "God_Demeter", "Grandma","Smith","jeb","Killer","God","kroden3d","klingbolt","IbramGaunt00","Owner","Shopkeeper","Top","Guard","Ninja","hidden","LeaverBoy","metalheadcz","Icarus","Dominus","Miner","Goblin","Citizen","Maokai","Judge", "Guard","Herobrine","PigZombie","KingCharles","Oracle","Librarian","augphlosiom","Basic","Wolfram","EnlightendDead","Hidendra","Drakia", "Quaffle","Jereq","HoneyDew","Xephos","Israphel","Grim","Ezreal","Jason","Non","Gazz","Britannia","Little","aPunch","Chris","Joe","Engel","Blockmann","Chef","Archer","CaptainSparklez","Kaikez","Adventurer","Warami","SoldjahBoy","frosty","Joymo","AnewAaron","TheDarkStar","Geecku","Shoigun","Giik","seekercat","Yamatsukami","MakaHearts","Kraiten","WingOfWar","ThePengu","Raecchi","Amber","MizLiv","AngelGirl737","SuccubusQueen"};
		for (int i = 0; i < players.length; i++){
			Location pos = new Location(this.getServer().getWorld("world"),2*(i+1),64,0);
			Player player = (Player)manager.spawnHumanNPC(players[i], pos).getBukkitEntity();
			player.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
			player.getInventory();
			player.setSneaking(true);
			
			
		}
		
	}
	
	// helpfull functions for output
	public void info(String input) {
		this.logger.info(pluginTitle + input);
	}
	public void severe (String input) {
		this.logger.severe(pluginTitle+"\033[31m"+input+"\033[0m");
	}

}
