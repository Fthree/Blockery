package Util;

import Component.*;
import Entity.IEntity;

import java.util.Optional;

public class ComponentUtil {
    public static Optional<IComponent> getComponent(ComponentType typeToGet, IEntity searchEntity) {
        return searchEntity.getComponents().stream().filter(iComponent -> iComponent.getType().equals(typeToGet)).findFirst();
    }

    public static VoxelComponent getVoxelComponent(IEntity searchEntity) {
        return (VoxelComponent)getComponent(ComponentType.VOXEL, searchEntity).orElse(new VoxelComponent());
    }

    public static VoxelGeometryComponent getVoxelGeometryComponent(IEntity searchEntity) {
        return (VoxelGeometryComponent)getComponent(ComponentType.VOXELGEOMETRY, searchEntity).orElse(new VoxelGeometryComponent());
    }

    public static PlayerComponent getPlayer(IEntity searchEntity) {
        return (PlayerComponent)getComponent(ComponentType.PLAYER, searchEntity).orElse(new PlayerComponent());
    }
}
