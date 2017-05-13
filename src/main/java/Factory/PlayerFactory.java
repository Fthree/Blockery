package Factory;

import Component.ComponentType;
import Component.PlayerComponent;
import Entity.Player;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;

import java.util.Arrays;

public class PlayerFactory {
    public static Player getPlayer(Vector3f initialPosition, float playerSpeed, int jumpSpeed, int fallSpeed, int gravity) {
        CharacterControl control = new CharacterControl(new CapsuleCollisionShape(1.2f, 0.2f), 0.1f);
        control.setFallSpeed(fallSpeed);
        control.setJumpSpeed(jumpSpeed);
        control.setGravity(gravity);
        control.setPhysicsLocation(initialPosition);

        PlayerComponent playerComponent = PlayerComponent.builder()
                .type(ComponentType.PLAYER)
                .playerControl(control)
                .speed(playerSpeed)
                .walkDirection(new Vector3f())
                .build();

        return Player.builder()
                .components(Arrays.asList(playerComponent))
                .build();
    }
}
