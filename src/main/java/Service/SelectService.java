package Service;

import Options.MouseOptions;
import Util.CubeUtil;
import Util.MathUtils;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResult;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

public class SelectService {
    private CubeUtil cubeUtil;

    public SelectService() {
        cubeUtil = new CubeUtil();
    }

    public void handleSelection(Node voxels, CollisionResult result, MouseOptions mouseOptions, AssetManager assetManager) {
        if(mouseOptions.equals(MouseOptions.RIGHT)) {
            voxels.detachChild(result.getGeometry());
        }
        if(mouseOptions.equals(MouseOptions.LEFT)) {
            Vector3f size = new Vector3f(0.1f, 0.1f, 0.1f).mult(2f);
            Vector3f position = MathUtils.calculateForwardPosition.apply(result.getGeometry().getLocalTranslation(), result.getContactNormal(), size);
            voxels.attachChild(cubeUtil.createUnshadedCube(assetManager, "", position, new Vector3f(1f, 1f, 1f)));
        }
    }

    public void handleEmptySelection(Node voxels, Camera cam, float distance, MouseOptions mouseOptions, AssetManager assetManager) {
        if(mouseOptions.equals(MouseOptions.LEFT)) {
            Vector3f dist = new Vector3f(distance, distance, distance);
            Vector3f position = MathUtils.calculateForwardPosition.apply(cam.getLocation(), cam.getDirection(), dist);
            voxels.attachChild(cubeUtil.createUnshadedCube(assetManager, "", position, new Vector3f(1f, 1f, 1f)));
        }
    }
}
