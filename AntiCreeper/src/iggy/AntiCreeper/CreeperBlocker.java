package iggy.AntiCreeper;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class CreeperBlocker implements Listener {
	@EventHandler (priority = EventPriority.NORMAL)
	public void onCreatureSpawn (CreatureSpawnEvent event){
		if (event.getEntityType() == EntityType.CREEPER) {
			if (event.getSpawnReason() != SpawnReason.EGG) {
				event.setCancelled(true);
			}
		}
	}
}
