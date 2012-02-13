package iggy.Economy;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;


public class ShopGenerator extends ChunkGenerator {
    //This is where you stick your populators - these will be covered in another tutorial
	
	int shopRadius = 30;
	int waterRadius = 60;
	byte waterBorder = (byte) Material.LOG.getId();
	/*@Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        //return Arrays.asList((BlockPopulator) new BlankPopulator());
    }*/
    //This needs to be set to return true to override minecraft's default behaviour
    @Override
    public boolean canSpawn(World world, int x, int z) {
        return true;
    }
    //This converts relative chunk locations to bytes that can be written to the chunk
    public int xyzToByte(int x, int y, int z) {
    	return (x * 16 + z) * 128 + y;
    }

    @Override
    public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
	    byte[] result = new byte[32768];
	    
	    int bedrocky = 64;
	    int groundFloor = 65;
	    int floorHeight = 7;
	    
	    //This will set the floor of each chunk at bedrock level to bedrock
	    for(int x=0; x<16; x++){
		    for(int z=0; z<16; z++) {
		    	int totalx = x + chunkx*16;
		    	int totaly = z + chunkz*16;
		    	
		    	long circleValue = (totalx*totalx)+(totaly*totaly);
		    	
		    	
		    	// generate bedrock
		    	if (circleValue<=shopRadius*shopRadius) {
		    		
		    		result[xyzToByte(x,bedrocky,z)] = (byte) Material.BEDROCK.getId();
		    		
		    		result[xyzToByte(x,groundFloor,z)] = (byte) Material.DOUBLE_STEP.getId();
		    		
		    		if ((totalx*totalx)+(totaly*totaly)>(shopRadius-1)*(shopRadius-1)){
		    			for (int i = 0; i < floorHeight; i++) {
		    				result[xyzToByte(x,groundFloor+1+i,z)] = (byte) Material.SMOOTH_BRICK.getId();
		    			}
		    		}
		    	}
		    	
		    	if (circleValue<=waterRadius*waterRadius ) {
		    		result[xyzToByte(x,0,z)] = (byte) Material.WATER.getId();
		    		if (circleValue>(waterRadius-1)*(waterRadius-1)) {
			    		result[xyzToByte(x,0,z)] = waterBorder;
			    	}
		    	}
		    	
		    }
	    }
	    return result;
    }

}