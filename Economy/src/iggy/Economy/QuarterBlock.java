package iggy.Economy;

import org.bukkit.Location;
import org.bukkit.World;

public class QuarterBlock {
	long _x;
	long _y;
	long _z;
	World world;
	
	QuarterBlock (Location location) {
		_x = (long) (location.getX()*2);
		_y = (long) (location.getX());
		_z = (long) (location.getZ()*2);
		world = location.getWorld();
	}
	
	public Location getLocation() {
		return new Location (world,((double)_x)/2,_y,((double)_z)/2);
	}
}
