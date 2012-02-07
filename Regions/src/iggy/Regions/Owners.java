package iggy.Regions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Owners {
	Set <String> _players = new HashSet<String>();
	
	public boolean addOwner(String player){
		return _players.add(player);
	}
	public boolean addOwners(List<String> players){
		boolean sucess = true;
		for (String player : players){
			sucess &= _players.add(player);
		}
		return sucess;
	}
	public boolean hasOwner(String player){
		return _players.contains(player);
	}
	public List<String> getOwners() {
		List<String> owners = new ArrayList<String>();
		// TODO: fill in the arraylist with the hashset
		Iterator<String> ownerIterator = _players.iterator();
		while (ownerIterator.hasNext()){
			owners.add(ownerIterator.next());
		}
		return owners;
	}
}
