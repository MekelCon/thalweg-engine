package fr.thalweg.engine.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.TransformComponent;

import java.util.Comparator;

public class EntityComparator implements Comparator<Entity> {
    private final ComponentMapper<TransformComponent> transformMapper;

    public EntityComparator() {
        this.transformMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public int compare(Entity e1, Entity e2) {
        return (int) Math.signum(transformMapper.get(e2).pos.z -
                transformMapper.get(e1).pos.z);
    }
}
