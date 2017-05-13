package GameSystem;

import Component.VoxelComponent;
import Entity.IEntity;
import Entity.Player;
import Entity.Voxel;
import Util.ComponentUtil;
import com.jme3.scene.Node;

import java.util.List;

public interface ISystem {
    void initialize(Node rootNode);

    boolean matchType(IEntity entity);

    void update(List<Voxel> voxels, Player player);
}
