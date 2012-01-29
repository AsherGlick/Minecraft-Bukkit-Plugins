package iggy.helloworld;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ServerChatPlayerListener extends PlayerListener{
	public static HelloWorld plugin;
	
	public ServerChatPlayerListener(HelloWorld instance) {
		plugin = instance;
	}
	
	public void onPlayerChat(PlayerChatEvent chat) {
		Player p = chat.getPlayer();
		String message = chat.getMessage();
		String message_lower = message.toLowerCase();
		ChatColor RED = ChatColor.RED;
		ChatColor WHITE = ChatColor.WHITE;
		if (message_lower.contains("hi") && message_lower.contains("server")) {
			p.sendMessage(RED + "[Server] " + WHITE + "Hello "+p.getName());
			// prevent the chat from appearing on everyone's screens
			chat.setCancelled(true);
		}
	}
}
