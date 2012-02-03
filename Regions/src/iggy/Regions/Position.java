package iggy.Regions;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class Position {
	public long _x;
	public long _z;
	public String _world;
	
	
	
	Position (String world,long x, long z){
		_x=x;
		_z=z;
		_world = world;
	}
	Position (Location location) {
		_x = floorDivide(location.getBlockX(),8);
		_z = floorDivide(location.getBlockZ(),8);
		_world = location.getWorld().getName();
	}
	
	private long floorDivide(long input, int mod) {
		return input<0?(input/8)-1:input/8;
	}
	
	public void placeTorches() {
		World plotWorld = Bukkit.getWorld(_world);
		Location corner1 = new Location(plotWorld,_x*8,0,_z*8);
		Location corner2 = new Location(plotWorld,_x*8,0,_z*8+7);
		Location corner3 = new Location(plotWorld,_x*8+7,0,_z*8);
		Location corner4 = new Location(plotWorld,_x*8+7,0,_z*8+7);
		plotWorld.getHighestBlockAt(corner1).setType(Material.TORCH);
		plotWorld.getHighestBlockAt(corner2).setType(Material.TORCH);
		plotWorld.getHighestBlockAt(corner3).setType(Material.TORCH);
		plotWorld.getHighestBlockAt(corner4).setType(Material.TORCH);
	}
	
	
	public boolean setFromString(String input) {
		for (int i = 0; i< input.length(); i++){
			if (input.charAt(i)==','){
				_world = input.substring(0,i);
				input = input.substring(i+1);
				break;
			}
		}
		for (int i = 0; i< input.length(); i++){
			if (input.charAt(i)==','){
				_x = Long.getLong(input.substring(0,i));
				input = input.substring(i+1);
				break;
			}
		}
		// check to make sure there is no extra comma
		for (int i = 0; i< input.length(); i++){
			if (input.charAt(i)==','){
				return false;
			}
		}
		_z = Long.getLong(input);
		return true;
	}
	@Override
	public String toString() {
		return (_world+","+_x+","+_z);
	}
}
