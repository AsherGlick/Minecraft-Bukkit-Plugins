package iggy.AntiCreeper;

import org.bukkit.entity.CreatureType;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityListener;

public class CreeperBlocker extends EntityListener {
	public void onCreatureSpawn (CreatureSpawnEvent event){
		if (event.getCreatureType() == CreatureType.CREEPER) {
			if (event.getSpawnReason() == SpawnReason.BED || event.getSpawnReason() == SpawnReason.NATURAL) {
				event.setCancelled(true);
			}
		}
	}
}
