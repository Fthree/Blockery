package Entity;

import Component.IComponent;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;
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
public class Player implements IEntity, Serializable {
    String name;
    List<IComponent> components;

}
