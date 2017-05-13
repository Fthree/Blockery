package Type;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mouse {
    boolean left = false;
    boolean right = false;
}
