/******************************************************************************\
|                                     ,,                                       |
|                    db             `7MM                                       |
|                   ;MM:              MM                                       |
|                  ,V^MM.    ,pP"Ybd  MMpMMMb.  .gP"Ya `7Mb,od8                |
|                 ,M  `MM    8I   `"  MM    MM ,M'   Yb  MM' "'                |
|                 AbmmmqMA   `YMMMa.  MM    MM 8M""""""  MM                    |
|                A'     VML  L.   I8  MM    MM YM.    ,  MM                    |
|              .AMA.   .AMMA.M9mmmP'.JMML  JMML.`Mbmmd'.JMML.                  |
|                                                                              |
|                                                                              |
|                                ,,    ,,                                      |
|                     .g8"""bgd `7MM    db        `7MM                         |
|                   .dP'     `M   MM                MM                         |
|                   dM'       `   MM  `7MM  ,p6"bo  MM  ,MP'                   |
|                   MM            MM    MM 6M'  OO  MM ;Y                      |
|                   MM.    `7MMF' MM    MM 8M       MM;Mm                      |
|                   `Mb.     MM   MM    MM YM.    , MM `Mb.                    |
|                     `"bmmmdPY .JMML..JMML.YMbmd'.JMML. YA.                   |
|                                                                              |
\******************************************************************************/
/******************************************************************************\
| Copyright (c) 2012, Asher Glick                                              |
| All rights reserved.                                                         |
|                                                                              |
| Redistribution and use in source and binary forms, with or without           |
| modification, are permitted provided that the following conditions are met:  |
|                                                                              |
| * Redistributions of source code must retain the above copyright notice,     |
|   this list of conditions and the following disclaimer.                      |
| * Redistributions in binary form must reproduce the above copyright notice,  |
|   this list of conditions and the following disclaimer in the documentation  |
|   and/or other materials provided with the distribution.                     |
|                                                                              |
| THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"  |
| AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE    |
| IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE   |
| ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE    |
| LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR          |
| CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF         |
| SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS     |
| INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN      |
| CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)      |
| ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE   |
| POSSIBILITY OF SUCH DAMAGE.                                                  |
\******************************************************************************/

package iggy.Regions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

public class BlockMonitor implements Listener{
	Regions plugin;
	BlockMonitor (Regions state) {
		plugin = state;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	//Called when a block is broken by a player. 
	@EventHandler (priority = EventPriority.HIGHEST)
	public void stopBreak (BlockBreakEvent event){
		event.setCancelled(shouldCancel(event.getBlock().getLocation(),event.getPlayer()));
	}
	//Called when a block is destroyed as a result of being burnt by fire. 
	@EventHandler (priority = EventPriority.HIGHEST)
	public void stopBurn (BlockBurnEvent event){
		event.setCancelled(shouldCancel(event.getBlock().getLocation(),null));
	}
	//Called when a block is ignited
	@EventHandler (priority = EventPriority.HIGHEST)
	public void stopFire (BlockIgniteEvent event){
		event.setCancelled(shouldCancel(event.getBlock().getLocation(),event.getPlayer()));
	}
 	//Called when a block is placed by a player. 
 	@EventHandler (priority = EventPriority.HIGHEST)
 	public void stopBuild (BlockPlaceEvent event) {
 		event.setCancelled(shouldCancel(event.getBlock().getLocation(),event.getPlayer()));
	}
 	
 	//Called when a bucket is placed
 	@EventHandler (priority = EventPriority.HIGHEST)
 	public void stopEmptyBuckets (PlayerBucketEmptyEvent event){
 		event.setCancelled(shouldCancel(event.getBlockClicked().getLocation(),event.getPlayer()));
 	}
 	
 	@EventHandler (priority = EventPriority.HIGHEST)
 	public void stopFillBuckets (PlayerBucketFillEvent event){
 		event.setCancelled(shouldCancel(event.getBlockClicked().getLocation(),event.getPlayer()));
 	} 
 	
 	/******************************** SHOULD CANCEL *******************************\
 	| this function reads in the situation and desides if the even should be canceld
 	| or not. It should be canceled if the event is happening inside a plot and the
 	| player causing the event is not an owner.
 	\******************************************************************************/

 	public boolean shouldCancel(Location location, Player player){
 		Position chunk = new Position(location);
		String chunkName = plugin.chunkNames.get(chunk);
		// if the plot does not exist it does not get canceled
		if (chunkName == null){return false;}
		// if code reaches here then the block is in an existing chunk
		if (player == null) {
			// if there is no player breaking the block in a plot then they cant be the owners of it
			plugin.info("Block break event by non player in "+chunkName+" canceled");
			return true;
		}
		Owners owners = plugin.chunkOwners.get(chunkName);
		if (owners == null) {
			plugin.severe("Region found but owners not! Check config file");
			player.sendMessage("[ERROR LOADING CHUNK OWNERS] tell admin");
			return true;
		}
		if (!owners.hasOwner(player.getName())){
			player.sendMessage("Don't break other people's stuff!");
			return true;
		} 
		return false;
 	}
}
