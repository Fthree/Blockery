package Factory;


import Model.Voxel;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

public class VoxelFactory {
    public Voxel getVoxel(String name, AssetManager assetManager, Vector3f position, float size) {
        Geometry voxel = UtilFactory.getCubeUtil().createUnshadedCube(
                assetManager,
                name,
                position,
                new Vector3f(size, size, size));

        RigidBodyControl voxelControl = new RigidBodyControl(CollisionShapeFactory.createBoxShape(voxel), 0.1f);
        voxelControl.setPhysicsLocation(position);
        voxelControl.setAngularDamping(100f);

        voxel.addControl(voxelControl);

        return Voxel.builder().name(name).voxel(voxel).voxelControl(voxelControl).build();
    }
}
