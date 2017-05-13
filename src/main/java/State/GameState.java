package State;

import GameSystem.PlayerSystem;
import GameSystem.TerrainSystem;
import GameSystem.VoxelSystem;
import Messenging.Broadcast.EntityBroadcast;
import Messenging.Broadcast.InputBroadcast;
import Service.CacheService;
import Service.InputService;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.ColorRGBA;


public class GameState extends AbstractAppState {
    private SimpleApplication app;
    BulletAppState bulletAppState;

    CacheService cacheService;
    InputService inputService;

    TerrainSystem terrainSystem;
    VoxelSystem voxelSystem;
    PlayerSystem playerSystem;

    AppStateManager stateManager;

    EntityBroadcast entityBroadcast;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        this.app = (SimpleApplication)app;
        this.stateManager = stateManager;
        this.entityBroadcast = new EntityBroadcast();

        startGame();
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if(enabled) {
            startGame();
        } else {
            this.app.getRootNode().detachAllChildren();
        }
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        InputBroadcast inputBroadcast = inputService.getInputBroadcast();

        playerSystem.update(this.app, inputBroadcast, entityBroadcast);
        voxelSystem.update(this.app.getCamera(), inputBroadcast);
        terrainSystem.update();
    }

    private void startGame() {
        setupBulletappState();
        setupGame();
        setupServices();
        initializeSystems();
    }

    public void initializeSystems() {
        terrainSystem = new TerrainSystem(this.app.getRootNode()); //1
        playerSystem = new PlayerSystem(); //2
        voxelSystem = new VoxelSystem(this.app.getRootNode()); //3

        terrainSystem.initialize(this.app, bulletAppState, entityBroadcast);
        terrainSystem.setupTerrainAmbientLight();
        voxelSystem.initialize(this.app, bulletAppState, entityBroadcast);
        playerSystem.initialize(bulletAppState, entityBroadcast);
    }

    public void setupServices() {
        cacheService = new CacheService();
        inputService = new InputService();

        inputService.setup(app, this.app.getGuiNode());
    }

    public void setupBulletappState() {
        bulletAppState = new BulletAppState();
        bulletAppState.setDebugEnabled(true);
        this.stateManager.attach(bulletAppState);
    }

    public void setupGame() {
        this.app.getViewPort().setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1f));
        this.app.getFlyByCamera().setMoveSpeed(122f);
    }
}
