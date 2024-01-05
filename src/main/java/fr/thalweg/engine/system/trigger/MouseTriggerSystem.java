package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.component.PolygonComponent;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;

public class MouseTriggerSystem extends IteratingSystem {

    private final Viewport viewport;
    private final ComponentMapper<SpriteComponent> spriteComponentMapper;
    private final ComponentMapper<PolygonComponent> polygonComponentMapper;

    public MouseTriggerSystem(Viewport viewport) {
        super(Family.all(MouseTriggerComponent.class).get());
        this.viewport = viewport;

        this.spriteComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
        this.polygonComponentMapper = ComponentMapper.getFor(PolygonComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Vector2 mouseXYWorld = this.viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        if (hitAsSprite(entity, mouseXYWorld)
                || hitAsPolygon(entity, mouseXYWorld)) {
            Gdx.app.log("TMP", "in Rectangle bounds !");
        }
    }

    private boolean hitAsSprite(Entity entity, Vector2 mouseXYWorld) {
        SpriteComponent spriteComponent = this.spriteComponentMapper.get(entity);
        return spriteComponent != null
                && spriteComponent.sprite.getBoundingRectangle().contains(mouseXYWorld);
    }

    private boolean hitAsPolygon(Entity entity, Vector2 mouseXYWorld) {
        PolygonComponent polygonComponent = this.polygonComponentMapper.get(entity);
        return polygonComponent != null
                && polygonComponent.polygon.contains(mouseXYWorld);
    }
}