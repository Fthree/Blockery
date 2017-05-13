package GameSystem;

import Messenging.Broadcast.EntityBroadcast;
import Model.Terrain;
import Service.TerrainService;
import com.jme3.app.Application;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class TerrainSystem {
    TerrainService terrainService;

    Node map;

    public TerrainSystem(Node rootNode) {
        terrainService = new TerrainService();
        map = new Node();
        rootNode.attachChild(map);
    }

    public void initialize(Application application, BulletAppState bulletAppState, EntityBroadcast entityBroadcast) {
        Terrain terrain = terrainService.setupTerrain(application.getAssetManager(), map, application.getCamera());

        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape(terrain.getTerrainQuad());
        RigidBodyControl landscape = new RigidBodyControl(sceneShape, 0);
        landscape.setPhysicsLocation(new Vector3f(0f, 0f, 0f));
        terrain.getTerrainQuad().addControl(landscape);

        bulletAppState.getPhysicsSpace().add(landscape);
        entityBroadcast.setTerrain(terrain);
    }

    public void setupTerrainAmbientLight() {
        AmbientLight sun = new AmbientLight();
        sun.setColor(ColorRGBA.White);
        map.addLight(sun);
    }

    public void update() {

    }
}
