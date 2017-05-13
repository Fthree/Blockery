package Service;

import Component.ComponentType;
import Component.VoxelGeometryComponent;
import Factory.UtilFactory;
import com.jme3.app.Application;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MapService {

   /* public List<Geometry> generateFlatCubeMap(Application app, Node map, Vector3f mapSize, float cubeSize) {
        List<Geometry> cubes = UtilFactory.getCubeUtil().createCubes(
                app.getAssetManager(),
                new Vector3f(mapSize.x, 1.0f, mapSize.z),
                cubeSize, new Vector3f());
        UtilFactory.getGeneralUtils().attachGoemetryToNode.apply(map, cubes);
        return cubes;
    }*/

    public List<VoxelGeometryComponent> createVoxel(Application app, float size, Node map, Vector3f mapSize) {
        List<VoxelGeometryComponent> voxels = new ArrayList<>();

        List<Spatial> quads = UtilFactory.getCubeUtil().createCubes(
                app.getAssetManager(),
                new Vector3f(mapSize.x, 1.0f, mapSize.z),
                size, new Vector3f());

        quads.stream().forEach(geometry -> {
            voxels.add(VoxelGeometryComponent.builder()
                    .type(ComponentType.VOXELGEOMETRY)
                    .top(geometry)
                    .bottom(geometry)
                    .left(geometry)
                    .right(geometry)
                    .front(geometry)
                    .back(geometry)
                    .build());
        });

        UtilFactory.getGeneralUtils().attachGoemetryToNode.apply(map, quads);

        return voxels;
    }
}
