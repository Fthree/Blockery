package Util;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;

public class QuadUtil {
    public Material getMaterial(AssetManager assetManager, String textureName) {
        Material material = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        material.setTexture("ColorMap",
                assetManager.loadTexture("Textures/" + textureName));
        return material;
    }
}
