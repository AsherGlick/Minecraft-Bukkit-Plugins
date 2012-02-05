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

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import iggy.Economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import org.dynmap.DynmapAPI;
import org.dynmap.markers.MarkerAPI;


public class Regions extends JavaPlugin{
  //////////////////////////////////////////////////////////////////////////////
 ///////////////////////////// GLOBAL DECLARATIONS ////////////////////////////
//////////////////////////////////////////////////////////////////////////////
	Logger logger = Logger.getLogger("Minecraft");
	
	String pluginTitle;
	PluginDescriptionFile pdFile;
	
	Plugin dynmap;
	Plugin economy;
	DynmapAPI dynmapapi;
	MarkerAPI markerapi;
	Economy economyapi;
	
	World mainworld;
	World thenether;
	
	DisplayPlotTitles displayPlotTitles = new DisplayPlotTitles(this);
	
	
	public Map<Position,String> chunkNames = new HashMap<Position,String>();
	public Map<String,Owners> chunkOwners = new HashMap<String,Owners>();
	
	BlockMonitor pluginMonitor;
  //////////////////////////////////////////////////////////////////////////////
 ////////////////////////////// ENABLE / DISABLE //////////////////////////////
//////////////////////////////////////////////////////////////////////////////
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		info(" Version " + pdFile.getVersion() +" is disabled");
	}

	@Override
	public void onEnable() {
		pdFile = this.getDescription();
		String pluginName = pdFile.getName();
		pluginTitle = "[\033[2;33m"+pluginName+"\033[0m]";

		pluginMonitor = new BlockMonitor(this);
		
		// define worlds
		mainworld = Bukkit.getWorld("world");
		thenether = Bukkit.getWorld("world_nether");
		
		displayPlotTitles.EnableRegionDisplayNames();
		
		// add external plugin links
		PluginManager pm = getServer().getPluginManager();
		dynmap = pm.getPlugin("dynmap");
		economy = pm.getPlugin("Economy");
		if (dynmap == null){
			severe("cannot find dynmap");
			return;
		}
		if (economy == null) {
			severe("cannot find economy");
			return;
		}
		dynmapapi = (DynmapAPI) dynmap;
		info("Loaded Dynmap");
		economyapi = (Economy) economy;
		info ("Loaded Economy");
		
		//set up all the block listeners to prevent distruction
		
		//economy is required for buying new chunks
		//dynmap is required for mapfunctions
		
		if (!economy.isEnabled() || !dynmap.isEnabled()) {
			getServer().getPluginManager().registerEvents(new OurServerListener(), this);
			if (!economy.isEnabled()) {
				info("Waiting for Economy to be enabled");
			}
			if (!dynmap.isEnabled()) {
				info("Waiting for Dynmap to be enabled");
			}
		}
		if (economy.isEnabled()){
			activateEconomy();
		}
		if (dynmap.isEnabled()){
			activatedynmap();
		}
		info (" Version " + pdFile.getVersion() +" is enabled");
	}
  //////////////////////////////////////////////////////////////////////////////
 /////////////////////////////// INPUT COMMANDS ///////////////////////////////
//////////////////////////////////////////////////////////////////////////////
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		
		//World world = player.getWorld();
		/************************************ CLAIM ***********************************\
		|
		\******************************************************************************/
		if (commandLabel.equalsIgnoreCase("claim")){
			// if economy is enabled
			if (!economy.isEnabled()) {
				player.sendMessage("Economy plugin is not enabled, contact admin for help");
				return false;
			}
			// is plot is not already claimed
			else if (chunkOwners.containsKey(new Position(player.getLocation()))){
				player.sendMessage("This plot has allready been claimed, you cannot claim it");
				return false;
			}
			// is plot in the regular world or the nether
			else if (player.getWorld() != mainworld && player.getWorld() != thenether){
				player.sendMessage("You can only claim plots in the nether or the main world");
			}
			// is a name given
			else if (args.length == 0) {
				player.sendMessage("You need to specify a name for this plot");
				return false;
			}
			// try to claim block
			else {
				String plotName = args[0];
				for (int i = 1; i < args.length; i++) {
					plotName += " "+args[i];
				}
				// check to see if the name has already been taken
				if (chunkOwners.containsKey(plotName)) {
					player.sendMessage("This plot name has allready been taken");
					return false;
				}
				
				if (economyapi.chargeMoney(player, 1000)) {
					Position plot = new Position(player.getLocation());
					Owners owner = new Owners();
					owner.addOwner(player.getName());
					chunkOwners.put(plotName, owner);
					chunkNames.put(plot, plotName);
					
					Position plot2 = new Position(player.getLocation());
					if (chunkNames.get(plot2) == null) {
						severe("WHAT THE FUCK JAVA HOW DO YOUR VARIABLES WORK");
					}
					// find highest block at the four corners
					plot.placeTorches();
					
					player.sendMessage("You bought the plot "+plotName+"for $1000");
				}
				
				else {
					player.sendMessage("You dont have enough money to buy this plot");
					return false;
				}
			}
		}
		/*********************************** EXPAND ***********************************\
		|
		\******************************************************************************/
		if (commandLabel.equalsIgnoreCase("expand")) {
			// if economy is enabled
			// if plot is not already claimed
			// if plot is in regularworld or netherworld
			// if name is given
			// claim block
			// find highest block at the four courners
		}
		return false;
	}
  //////////////////////////////////////////////////////////////////////////////
 ////////////////////////////// WAIT FOR PLUGINS //////////////////////////////
//////////////////////////////////////////////////////////////////////////////
	// listener class to wait for the other plugins to enable
	private class OurServerListener implements Listener {
		// warnings are suppressed becasue this is called using registerEvents when the 
		@SuppressWarnings("unused")
		// required plugins are not enabled on start
		@EventHandler (priority = EventPriority.MONITOR)
        public void onPluginEnable(PluginEnableEvent event) {
            Plugin p = event.getPlugin();
            String name = p.getDescription().getName();
            if(name.equals("dynmap")) {
            }
            if(name.equals("Economy")) {
            	activateEconomy();
            }
        }
    }

	// funtion to finish activating the plugin once the other plugins are enabled
	public void activateEconomy(){
		//TODO: make these features not enabled if the plugin is not enabeled
		info ("Economy features (claim, expand) enabled");
	}
	
	public void activatedynmap() {
		markerapi =  dynmapapi.getMarkerAPI();
		if (markerapi == null){
			severe ("error loading the dynmap marker api");
			return;
		}
		
		//TODO: make the plots show up on the map
		info("dynmap features (view plots on map) enabled");
	}
  //////////////////////////////////////////////////////////////////////////////
 /////////////////////////////// DISPLAY HELPERS //////////////////////////////
//////////////////////////////////////////////////////////////////////////////
	public void info(String input) {
		this.logger.info(pluginTitle + input);
	}
	public void severe (String input) {
		this.logger.severe(pluginTitle+"\033[31m"+input+"\033[0m");
	}
	
}
