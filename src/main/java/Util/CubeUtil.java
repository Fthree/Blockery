package Util;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

import java.util.ArrayList;
import java.util.List;

public class CubeUtil {
    public Geometry createCube(String name, Vector3f position, Vector3f size) {
        Box cubeMesh = new Box(size.x, size.y, size.z);
        Geometry cubeGeo = new Geometry(name, cubeMesh);
        cubeGeo.setLocalTranslation(position);
        return cubeGeo;
    }

    public Geometry createUnshadedCube(AssetManager assetManager, String name, Vector3f position, Vector3f size) {
        Box cubeMesh = new Box(size.x, size.y, size.z);
        Geometry cubeGeo = new Geometry(name, cubeMesh);
        cubeGeo.setLocalTranslation(position);
        Material cubeMat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        cubeMat.setColor("Color", ColorRGBA.White);
        cubeGeo.setMaterial(cubeMat);
        return cubeGeo;
    }

    public Spatial createQuad(AssetManager assetManager, String quadName, Vector3f position, float faceSize) {
        Spatial quad = assetManager.loadModel(quadName);
        quad.setLocalTranslation(position);
        quad.setLocalScale(faceSize);
        return quad;
    }

    public Spatial createTexturedCube(String cubeName, AssetManager assetManager, Vector3f position, float cubeSize) {
        Spatial cube = assetManager.loadModel(cubeName);
        cube.setLocalTranslation(position);
        cube.setLocalScale(cubeSize);
        return cube;
    }

    public Geometry createOutlineCube(AssetManager assetManager, Vector3f position, Vector3f size) {
        Box cubeMesh = new Box(size.x, size.y, size.z);
        Geometry cubeGeo = new Geometry("Outline Box", cubeMesh);
        cubeGeo.setLocalTranslation(position);
        Material cubeMat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        cubeMat.getAdditionalRenderState().setWireframe(true);
        cubeMat.setColor("Color", ColorRGBA.White);
        cubeGeo.setMaterial(cubeMat);
        return cubeGeo;
    }

    public List<Spatial> createCubes(AssetManager assetManager, Vector3f size, float cubeSize, Vector3f centerPosition) {
        List<Spatial> ret = new ArrayList<>();

        for (int i = 0; i != size.z; i++) { //Z
            for (int j = 0; j != size.y; j++) { //Y
                for (int k = 0; k != size.x; k++) { //X
                    //Geometry current = this.createCube(assetManager, "box " + i * j * k, Optional.empty(), new Vector3f((cubeSize.x * 2) * k, (cubeSize.y * 2) * j, (cubeSize.z * 2) * i), cubeSize);

                    Spatial current = this.createQuad(assetManager, "Models/grassf/top.obj", new Vector3f((cubeSize * 2f) * k, (cubeSize * 2f) * j, (cubeSize * 2f) * i), cubeSize);
                    ret.add(current);
                }
            }
        }
        return ret;
    }
}
