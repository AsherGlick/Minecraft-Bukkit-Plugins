package iggy.Modmode;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.Material;
//import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.potion.PotionEffect;
//import org.bukkit.potion.PotionEffectType;


public class Modmode extends JavaPlugin{
	public Map<Player, Double> lightningDegree = new HashMap<Player, Double>();
	public double distance = 7;
	@Override
	public void onEnable () {
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable () {
			public void run() {
				for (Entry<Player,Double> p : lightningDegree.entrySet()) {
					Player player = p.getKey();
					double deg = (double)p.getValue();
					if (deg >= 360) deg -= 360;
					
					//player.sendMessage("HI! DEG AT "+deg+" "+Math.sin(Math.PI*deg/180)*distance+":"+Math.cos(Math.PI*deg/180)*distance);
					//World world = player.getWorld();
					
					Location location = player.getLocation();
					//location.add(Math.acos(deg)*distance, 0, Math.asin(deg)*distance);
					location.add(Math.sin(Math.PI*deg/180)*distance, 0, Math.cos(Math.PI*deg/180)*distance);
					
					location.setY(player.getWorld().getHighestBlockYAt(location));
					
					p.getKey().getWorld().strikeLightningEffect(location);
					lightningDegree.put(player, deg+20);
				}
			}
		}, 2L, 2L);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		
		if (player == null) {
			//this.logger.info("This command can only be run by a player");
			return false;
		}
		
		//World world = player.getWorld();
		
		if (commandLabel.equalsIgnoreCase("GODMODE")){
			if (lightningDegree.containsKey(player)){
				lightningDegree.remove(player);
				player.sendMessage("Godmode deactivated");
			}
			else {
				player.sendMessage("Godmode activated");
				lightningDegree.put(player, 0D);
				if (player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					player.getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 999);
				}
				//player.addPotionEffect(new PotionEffect(PotionEffectType., 0, 0));
				//PotionEffect effect = new PotionEffect(null, 0, 0);
			}
			
		}
		
		if (commandLabel.equalsIgnoreCase("RAGE")) {
			for (double deg = 0; deg < 360; deg += 10){
				Location location = player.getLocation();
				//location.add(Math.acos(deg)*distance, 0, Math.asin(deg)*distance);
				location.add(Math.sin(Math.PI*deg/180)*distance, 0, Math.cos(Math.PI*deg/180)*distance);
				location.setY(player.getWorld().getHighestBlockYAt(location));
				
				
				player.getWorld().strikeLightningEffect(location);
			}
		}
		return false;
	}
}
