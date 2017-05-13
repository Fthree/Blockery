package GameSystem;

import Component.PlayerComponent;
import Component.VoxelComponent;
import Entity.IEntity;
import Entity.Player;
import Entity.Voxel;
import Util.ComponentUtil;
import com.hazelcast.cache.impl.CacheService;
import com.jme3.app.Application;
import com.jme3.scene.Node;

import java.util.List;

public class MapSystem {
    List<Voxel> mapVoxels;

    Node map;
    CacheService cacheService;

    public void initialize(Node rootNode) {
        cacheService = new CacheService();
        rootNode.attachChild(map);
    }

    public boolean matchType(IEntity entity) {
        return !ComponentUtil.getVoxelComponent(entity).equals(new VoxelComponent());
    }

    public void update() {

    }
}
