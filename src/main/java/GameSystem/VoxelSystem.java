package GameSystem;

import Messenging.Broadcast.EntityBroadcast;
import Messenging.Broadcast.InputBroadcast;
import Model.Voxel;
import Service.VoxelService;
import com.jme3.app.Application;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class VoxelSystem {
    Node voxelNode;
    List<Voxel> voxels;
    VoxelService voxelService;
    Voxel pickedVoxel;

    float voxelSize = 0.4f;

    public VoxelSystem(Node rootNode) {
        voxelService = new VoxelService();
        voxels = new ArrayList<>();
        voxelNode = new Node();
        rootNode.attachChild(voxelNode);
    }

    public void initialize(Application app, BulletAppState bulletAppState, EntityBroadcast entityBroadcast) {
        IntStream.range(-10, 10).forEach(idx -> {
            int x = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            int y = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            Voxel voxel = voxelService.createSimpleVoxel(
                    "simple",
                    app.getAssetManager(),
                    new Vector3f(x,
                            entityBroadcast
                                    .getTerrain()
                                    .getTerrainQuad()
                                    .getHeight(new Vector2f(x, y)) + 2f, y),
                    voxelSize);

            voxels.add(voxel);
            voxelNode.attachChild(voxel.getVoxel());
            bulletAppState.getPhysicsSpace().add(voxel.getVoxelControl());
        });

    }

    public void update(Camera camera, InputBroadcast inputBroadcast) {

    }

}
