package Model;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Geometry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voxel {
    String name;
    public Geometry voxel;
    public RigidBodyControl voxelControl;
}
