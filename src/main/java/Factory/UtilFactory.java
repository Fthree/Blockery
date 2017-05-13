package Factory;

import Util.*;
import lombok.Data;
import lombok.Getter;

@Data
public class UtilFactory {
    @Getter private static RayUtil rayUtil = new RayUtil();
    @Getter private static PreviewCubeUtil previewCubeUtil = new PreviewCubeUtil();
    @Getter private static MouseUtil mouseUtil = new MouseUtil();
    @Getter private static MathUtils mathUtils = new MathUtils();
    @Getter private static GeneralUtils generalUtils = new GeneralUtils();
    @Getter private static CubeUtil cubeUtil = new CubeUtil();
    @Getter private static QuadUtil quadUtil = new QuadUtil();
    @Getter private static CommonUtil commonUtil = new CommonUtil();
}
