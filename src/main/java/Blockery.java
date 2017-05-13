import Cache.CacheTypes;
import Service.HazelcastService;
import State.GameState;
import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;

public class Blockery extends SimpleApplication {
    public static void main(String[] args) {
        HazelcastService.initialize(CacheTypes.ENTITY);

        Blockery app = new Blockery();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        stateManager.attach(new GameState());
    }
    @Override
    public void simpleUpdate(float tpf) {

    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
