package Util;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import javaslang.Function2;

import java.util.List;

public class GeneralUtils {
    public Function2<Node, List<Spatial>, Boolean> attachGoemetryToNode = (node, geometries) -> {
        geometries.stream().forEach(geometry -> node.attachChild(geometry));
        return true;
    };
}
