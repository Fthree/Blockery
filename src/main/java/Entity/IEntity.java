package Entity;

import Component.IComponent;

import java.util.List;


public interface IEntity {
    List<IComponent> getComponents();
    String getName();
    void setName(String name);
}
