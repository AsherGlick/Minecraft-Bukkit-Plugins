package iggy.Regions;

import org.bukkit.Location;

public class Position {
	long _x;
	long _z;
	String _world;
	Position (String world,long x, long z){
		_x=x;
		_z=z;
		_world = world;
	}
	Position (Location location) {
		_x = location.getBlockX()/8;
		_z = location.getBlockZ()/8;
		_world = location.getWorld().getName();
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
