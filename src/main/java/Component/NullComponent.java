package Component;

import lombok.Getter;

import java.io.Serializable;

public class NullComponent implements IComponent, Serializable {
    @Getter
    private ComponentType type = ComponentType.NULL;
}
