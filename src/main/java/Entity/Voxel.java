package Entity;

import Component.IComponent;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Geometry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Voxel implements IEntity, Serializable {
    String name;
    List<IComponent> components;
}
