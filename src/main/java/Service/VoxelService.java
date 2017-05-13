package Service;

import Factory.VoxelFactory;
import Model.Voxel;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;

public class VoxelService {
    VoxelFactory voxelFactory;

    public VoxelService() {
        voxelFactory = new VoxelFactory();
    }

     public Voxel createSimpleVoxel(String name, AssetManager assetManager, Vector3f position, float size) {
         return voxelFactory.getVoxel(name, assetManager, position, size);
     }
}
