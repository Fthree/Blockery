package Component;

import com.jme3.scene.Spatial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoxelGeometryComponent implements IComponent, Serializable {
    private ComponentType type;
    private Spatial top;
    private Spatial bottom;
    private Spatial left;
    private Spatial right;
    private Spatial front;
    private Spatial back;
}
