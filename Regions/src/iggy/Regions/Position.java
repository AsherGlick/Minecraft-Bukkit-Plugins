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
import org.bukkit.Material;
import org.bukkit.World;

public class Position {
	public long _x;
	public long _z;
	public String _world;
	
	public Position (String world,long x, long z){
		_x=x;
		_z=z;
		_world = world;
	}
	
	public Position (Location location) {
		_x = floorDivide(location.getBlockX(),8);
		_z = floorDivide(location.getBlockZ(),8);
		_world = location.getWorld().getName();
	}
	
	public Position (){
		_x=0;
		_z=0;
		_world=null;
	}
	
	public double getMinimumXCorner() {
		return _x*8;
	}
	public double getMinimumZCorner() {
		return _z*8;
	}
	
	private long floorDivide(long input, int mod) {
		return input<0?((input+1)/8)-1:input/8;
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
				_x = Long.valueOf(input.substring(0,i));
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
		_z = Long.valueOf(input);
		return true;
	}
	@Override
	public String toString() {
		return (_world+","+_x+","+_z);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this._world != other._world && (this._world == null || !this._world.equals(other._world))) {
        	return false;
        }
        if (Double.doubleToLongBits(this._x) != Double.doubleToLongBits(other._x)) {
			return false;
        }
        if (Double.doubleToLongBits(this._z) != Double.doubleToLongBits(other._z)) {
        	return false;
        }
        	return true;
	}
		
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 19 * hash + (this._world != null ? this._world.hashCode() : 0);
		hash = 19 * hash + (int) (Double.doubleToLongBits(this._x) ^ (Double.doubleToLongBits(this._x) >>> 32));
		hash = 19 * hash + (int) (Double.doubleToLongBits(this._z) ^ (Double.doubleToLongBits(this._z) >>> 32));
		return hash;
	}
	
	@Override
	public Position clone() {
		try {
			Position l = (Position) super.clone();
			l._world = _world;
			l._x = _x;
            l._z = _z;
            return l;
		} catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
		return null;
	}
}
