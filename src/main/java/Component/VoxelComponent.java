package Component;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoxelComponent implements IComponent, Serializable {
    private ComponentType type = ComponentType.VOXEL;
    private RigidBodyControl control;
    private Node voxel;
}
