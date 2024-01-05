package fr.thalweg.engine.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.ZIndexComponent;

import java.util.Comparator;

public class EntityComparator implements Comparator<Entity> {
    private final ComponentMapper<ZIndexComponent> zIndexComponentMapper;

    public EntityComparator() {
        this.zIndexComponentMapper = ComponentMapper.getFor(ZIndexComponent.class);
    }

    @Override
    public int compare(Entity e1, Entity e2) {
        return (int) Math.signum(zIndexComponentMapper.get(e2).zIndex -
                zIndexComponentMapper.get(e1).zIndex);
    }
}