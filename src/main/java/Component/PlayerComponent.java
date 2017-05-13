package Component;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerComponent implements IComponent, Serializable {
    private ComponentType type = ComponentType.PLAYER;
    private CharacterControl playerControl;
    private Vector3f walkDirection;
    private float speed;
}
