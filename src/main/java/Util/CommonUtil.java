package Util;

import Model.Voxel;

import java.util.List;

public class CommonUtil {
    public String getUnshaded() {
        return "Common/MatDefs/Misc/Unshaded.j3md";
    }
    public Voxel voxelFromName(String name,List<Voxel> voxels) {
        return voxels.stream().filter(voxel -> voxel.getName().equals(name)).findFirst().orElse(new Voxel());
    }
}
