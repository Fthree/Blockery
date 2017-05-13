package Service;

import Model.Terrain;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.HillHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;

public class TerrainService {
    public Terrain setupTerrain(AssetManager assetManager, Node map, Camera cam) {
        Material terrainMat = new Material(assetManager, "Common/MatDefs/Terrain/Terrain.j3md");
        Texture grass = assetManager.loadTexture("Models/grassf/top.png");
        grass.setWrap(Texture.WrapMode.Repeat);
        terrainMat.setTexture("Tex1", grass);
        terrainMat.setFloat("Tex1Scale", 64f);
        try {
            HillHeightMap heightMap = new HillHeightMap(513, 1000, 2000, 2500, (byte) 5);
            ImageBasedHeightMap imageBasedHeightMap = new ImageBasedHeightMap(assetManager.loadTexture("map.png").getImage());

            imageBasedHeightMap.load();
            imageBasedHeightMap.normalizeTerrain(0.5f);
            TerrainQuad terrain = new TerrainQuad("terrain", 65, 513, imageBasedHeightMap.getHeightMap());
            terrain.setMaterial(terrainMat);
            /*terrain.setLocalTranslation(0f, 0f, 0f);
            terrain.setLocalScale(2f, 1f, 2f);*/
            map.attachChild(terrain);

            TerrainLodControl control = new TerrainLodControl(terrain, cam);
            terrain.addControl(control);

            return Terrain.builder().heightMap(imageBasedHeightMap).terrainQuad(terrain).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Terrain.builder().build();
    }
}
