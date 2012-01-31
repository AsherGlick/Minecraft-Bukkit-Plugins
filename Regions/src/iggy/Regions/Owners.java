package iggy.Regions;

import java.util.HashSet;
import java.util.Set;

public class Owners {
	Set <String> _players = new HashSet<String>();
	
	public boolean addOwner(String player){
		return _players.add(player);
	}
	public boolean hasOwner(String player){
		return _players.contains(player);
	}
}
