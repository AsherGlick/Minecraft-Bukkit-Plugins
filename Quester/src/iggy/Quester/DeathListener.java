package iggy.Quester;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class DeathListener implements Listener {
	Quester plugin;
	DeathListener (Quester state) {
		plugin = state;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	//Called when a player dies
	@EventHandler (priority = EventPriority.NORMAL)
	public void catchDeath (PlayerDeathEvent event){
		//if (!event.isCancelled()) { event.setCancelled(shouldCancel(event.getBlock().getLocation(),event.getPlayer())); }
		Player player = (Player)event.getEntity();
		Location deathZone = event.getEntity().getLocation();
		
		ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);  //Material.SKULL_ITEM,1,short(3));
		SkullMeta headInfo = (SkullMeta) playerHead.getItemMeta();
		headInfo.setOwner(player.getDisplayName());
		playerHead.setItemMeta(headInfo);
		deathZone.getWorld().dropItem(deathZone, playerHead);
		
		plugin.info("Dropped a player head");
	}
}
