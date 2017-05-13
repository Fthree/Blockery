package Util;

import com.jme3.math.Vector3f;
import javaslang.Function3;

public class MathUtils {
    //Multiply current direction (usually normalized) by a distance and add this vector to the current location
    public static Function3<Vector3f, Vector3f, Vector3f, Vector3f> calculateForwardPosition = (location, direction, distance) -> location.add(direction.mult(distance));
}
