package Type;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Keyboard {
    boolean left = false;
    boolean right = false;
    boolean forward = false;
    boolean backward = false;
    boolean jump = false;
}
