package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.component.PolygonComponent;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;

public class MouseTriggerSystem extends TriggerSystem {

    private final Array<Entity> triggerQueue = new Array<>();
    private final Viewport viewport;
    private final ComponentMapper<MouseTriggerComponent> mm;
    private final ComponentMapper<SpriteComponent> sm;
    private final ComponentMapper<PolygonComponent> pm;
    private Entity currentTouchedEntity;

    public MouseTriggerSystem(Viewport viewport) {
        super(Family.all(MouseTriggerComponent.class).get());
        this.viewport = viewport;
        this.mm = ComponentMapper.getFor(MouseTriggerComponent.class);
        this.sm = ComponentMapper.getFor(SpriteComponent.class);
        this.pm = ComponentMapper.getFor(PolygonComponent.class);
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
        this.checkOnMouseLeave(nexCurrent);
        this.checkOnMouseEnter(nexCurrent);

        triggerQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        triggerQueue.add(entity);
    }

    private boolean hitAsSprite(Entity entity, Vector2 mouseXYWorld) {
        var spriteComponent = sm.get(entity);
        return spriteComponent != null
                && spriteComponent.sprite.getBoundingRectangle().contains(mouseXYWorld);
    }

    private boolean hitAsPolygon(Entity entity, Vector2 mouseXYWorld) {
        var polygonComponent = this.pm.get(entity);
        return polygonComponent != null
                && polygonComponent.polygon.contains(mouseXYWorld);
    }

    private void checkOnMouseLeave(Entity nexCurrent) {
        if (currentTouchedEntity != null
                && nexCurrent != currentTouchedEntity) {
            var mouseTriggerComponent = mm.get(currentTouchedEntity);
            createEntityForTriggered(mouseTriggerComponent.onMouseLeave);
            currentTouchedEntity = null;
        }
    }

    private void checkOnMouseEnter(Entity nexCurrent) {
        if (nexCurrent != null && nexCurrent != currentTouchedEntity) {
            var mouseTriggerComponent = mm.get(nexCurrent);
            createEntityForTriggered(mouseTriggerComponent.onMouseEnter);
            currentTouchedEntity = nexCurrent;
        }
    }
}