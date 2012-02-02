package iggy.Economy;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;


public class ShopGenerator extends ChunkGenerator {
    //This is where you stick your populators - these will be covered in another tutorial
	
	int storeLength = 2;
	int storeWidth = 2;
	
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
	    int y = 64;
	    //This will set the floor of each chunk at bedrock level to bedrock
	    for(int x=0; x<16; x++){
		    for(int z=0; z<16; z++) {
		    	if (chunkx > -storeWidth && chunkx < storeWidth && chunkz > -storeLength && chunkz < storeLength ) {
		    		result[xyzToByte(x,y,z)] = (byte) Material.BEDROCK.getId();
		    	}
		    	result[xyzToByte(x,0,z)] = (byte) Material.WATER.getId();
		    }
	    }
	    return result;
    }

}