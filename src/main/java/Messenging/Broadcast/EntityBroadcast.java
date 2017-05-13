package Messenging.Broadcast;

import Entity.Player;
import Model.Terrain;
import Model.Voxel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntityBroadcast {
    Terrain terrain;
    Player player;
    List<Voxel> voxels;
}
