package iggy.SuspendBlock;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class SuspendPlayerListener extends PlayerListener {
	public static SuspendBlock plugin;
	
	public SuspendPlayerListener(SuspendBlock instance) {
		plugin = instance;
	}
	
	public void onPlayerInteract (PlayerInteractEvent event){
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			event.getPlayer().sendMessage("[SERVER] you rightclicked a block!");
		}
		if (event.getAction() == Action.RIGHT_CLICK_AIR){
			event.getPlayer().sendMessage("[SERVER] you rightclicked air!");
		}
		if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
			event.getPlayer().sendMessage("[SERVER] you leftclicked a block!");
		}
		if (event.getAction() == Action.LEFT_CLICK_AIR) {
			event.getPlayer().sendMessage("[SERVER] you leftclicked air!");
		}
		if (event.getPlayer().getItemInHand().getType() == Material.WATCH) {
			event.getPlayer().sendMessage("[SERVER] you used the right item");
			PlayerInventory inventory = event.getPlayer().getInventory();
			ItemStack itemstack = new ItemStack(Material.COMPASS,1);
		}
	}
	
}
