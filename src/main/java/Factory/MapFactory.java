package Factory;

import Component.ComponentType;
import Component.VoxelComponent;
import Component.VoxelGeometryComponent;
import Entity.Voxel;
import Service.MapService;
import com.jme3.app.Application;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapFactory {
    MapService mapService;

    public MapFactory() {
        mapService = new MapService();
    }

    public List<Voxel> createMap(Application app, Node map, float mapSize, float cubeSize) {
        List<VoxelGeometryComponent> voxelGeometries = mapService.createVoxel(app, 0.2f, map, new Vector3f(mapSize, 1.0f, mapSize));

        List<Voxel> voxels = new ArrayList<>();
        voxelGeometries.stream().forEach(voxelGeometryComponent -> {
            Spatial voxelQuad = voxelGeometryComponent.getTop();
            RigidBodyControl rigidBodyControl = new RigidBodyControl(CollisionShapeFactory.createMeshShape(voxelQuad), 0f);

            VoxelComponent voxelComponent = VoxelComponent.builder()
                    .type(ComponentType.VOXEL)
                    .control(rigidBodyControl)
                    .build();

            voxels.add(Voxel.builder()
                    .components(Arrays.asList(voxelComponent, voxelGeometryComponent))
                    .build());

            voxelQuad.addControl(rigidBodyControl);


        });
        return voxels;
    }
}
