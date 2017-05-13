package GameSystem;

import Component.PlayerComponent;
import Entity.IEntity;
import Entity.Player;
import Factory.PlayerFactory;
import Messenging.Broadcast.EntityBroadcast;
import Messenging.Broadcast.InputBroadcast;
import Type.Keyboard;
import Util.ComponentUtil;
import com.jme3.app.Application;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import lombok.Data;

@Data
public class PlayerSystem {
    private float playerSpeed = 0.1f;

    Player player;

    public PlayerSystem() {
    }

    public void initialize(BulletAppState bulletAppState, EntityBroadcast entityBroadcast) {
        Vector3f playerPosition = new Vector3f(0f, entityBroadcast.getTerrain().getTerrainQuad().getHeight(new Vector2f(0, 0)) + 1f, 0f);
        player = PlayerFactory.getPlayer(playerPosition, playerSpeed, 10, 30, 30);
        bulletAppState.getPhysicsSpace().add(ComponentUtil.getPlayer(player).getPlayerControl());
        entityBroadcast.setPlayer(player);
    }

    public boolean matchType(IEntity entity) {
        return !ComponentUtil.getPlayer(entity).equals(new PlayerComponent());
    }

    public void update(Application app, InputBroadcast inputBroadcast, EntityBroadcast entityBroadcast) {
        Vector3f camDir = new Vector3f();
        Vector3f camLeft = new Vector3f();
        Camera cam = app.getCamera();
        camDir.set(cam.getDirection()).multLocal(ComponentUtil.getPlayer(player).getSpeed());
        camLeft.set(cam.getLeft()).multLocal(ComponentUtil.getPlayer(player).getSpeed()).divide(2f);

        Vector3f walkDirection = ComponentUtil.getPlayer(player).getWalkDirection();
        walkDirection.set(0, 0, 0);

        Keyboard keyboard = inputBroadcast.getKeyboard();

        if (keyboard.isLeft()) {
            walkDirection.addLocal(camLeft);
        }
        if (keyboard.isRight()) {
            walkDirection.addLocal(camLeft.negate());
        }
        if (keyboard.isForward()) {
            walkDirection.addLocal(camDir);
        }
        if (keyboard.isBackward()) {
            walkDirection.addLocal(camDir.negate());
        }
        if (keyboard.isJump()) {
            ComponentUtil.getPlayer(player).getPlayerControl().jump();
        }

        ComponentUtil.getPlayer(player).setWalkDirection(walkDirection);
        ComponentUtil.getPlayer(player).getPlayerControl().setWalkDirection(walkDirection);
        cam.setLocation(ComponentUtil.getPlayer(player).getPlayerControl().getPhysicsLocation());
    }
}
