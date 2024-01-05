package fr.thalweg.engine.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.SpriteComponent;

import java.util.Comparator;

public class EntityComparator implements Comparator<Entity> {
    private final ComponentMapper<SpriteComponent> spriteComponentMapper;

    public EntityComparator() {
        this.spriteComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
    }

    @Override
    public int compare(Entity e1, Entity e2) {
        return (int) Math.signum(spriteComponentMapper.get(e2).zIndex -
                spriteComponentMapper.get(e1).zIndex);
    }
}
