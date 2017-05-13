package Messenging.Broadcast;

import Type.Keyboard;
import Type.Mouse;
import com.jme3.math.Vector3f;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputBroadcast {
    Keyboard keyboard;
    Mouse mouse;
    Vector3f direction;
}
