package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.component.PolygonComponent;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.TodoComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;

public class MouseTriggerSystem extends IteratingSystem {

    private final Array<Entity> triggerQueue = new Array<>();
    private final Viewport viewport;
    private final ComponentMapper<MouseTriggerComponent> mouseTriggerComponentMapper;
    private final ComponentMapper<SpriteComponent> spriteComponentMapper;
    private final ComponentMapper<PolygonComponent> polygonComponentMapper;
    private Entity currentTouchedEntity;

    public MouseTriggerSystem(Viewport viewport) {
        super(Family.all(MouseTriggerComponent.class).get());
        this.viewport = viewport;
        this.mouseTriggerComponentMapper = ComponentMapper.getFor(MouseTriggerComponent.class);
        this.spriteComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
        this.polygonComponentMapper = ComponentMapper.getFor(PolygonComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Entity nexCurrent = null;
        for (Entity entity : triggerQueue) {
            Vector2 mouseXYWorld = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            if (hitAsPolygon(entity, mouseXYWorld)
                    || hitAsSprite(entity, mouseXYWorld)) {
                nexCurrent = entity;
            }
        }
        if (currentTouchedEntity != null
                && nexCurrent != currentTouchedEntity) {
            MouseTriggerComponent mouseTriggerComponent = mouseTriggerComponentMapper.get(currentTouchedEntity);
            currentTouchedEntity.add(TodoComponent.builder()
                    // Use copy to avoid side effect on removal
                    .todos(new Array<>(mouseTriggerComponent.onMouseLeave))
                    .build());
            currentTouchedEntity = null;
        }
        if (nexCurrent != null && nexCurrent != currentTouchedEntity) {
            MouseTriggerComponent nextMouseTriggerComponent = mouseTriggerComponentMapper.get(nexCurrent);
            nexCurrent.add(TodoComponent.builder()
                    // Use copy to avoid side effect on removal
                    .todos(new Array<>(nextMouseTriggerComponent.onMouseEnter))
                    .build());
            currentTouchedEntity = nexCurrent;
        }
        triggerQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        triggerQueue.add(entity);
    }

    private boolean hitAsSprite(Entity entity, Vector2 mouseXYWorld) {
        SpriteComponent spriteComponent = spriteComponentMapper.get(entity);
        return spriteComponent != null
                && spriteComponent.sprite.getBoundingRectangle().contains(mouseXYWorld);
    }

    private boolean hitAsPolygon(Entity entity, Vector2 mouseXYWorld) {
        PolygonComponent polygonComponent = this.polygonComponentMapper.get(entity);
        return polygonComponent != null
                && polygonComponent.polygon.contains(mouseXYWorld);
    }
}