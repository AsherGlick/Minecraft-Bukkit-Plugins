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
		Location point = new Location (this.getServer().getWorld("world"),0,64,0);
		Player iggy = (Player) manager.spawnHumanNPC("Iggystlev", point).getBukkitEntity();
		iggy.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
		String players[] = {"Guard", "Notch", "God_Demeter", "Grandma","Smith","jeb","Killer","God","kroden3d","klingbolt","IbramGaunt00","Owner","Shopkeeper","Top","Guard","Ninja"};
		for (int i = 0; i < players.length; i++){
			Location pos = new Location(this.getServer().getWorld("world"),2*i,64,0);
			Player player = (Player)manager.spawnHumanNPC(players[i], pos).getBukkitEntity();
			player.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
			player.setDisplayName("MAN");
			player.setSneaking(true);
			
		}
		
	}

}
