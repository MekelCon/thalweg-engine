package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;

public class MouseTriggerSystem extends IteratingSystem {

    private final Viewport viewport;
    private final ComponentMapper<SpriteComponent> spriteComponentMapper;

    public MouseTriggerSystem(Viewport viewport) {
        super(Family.all(MouseTriggerComponent.class, SpriteComponent.class).get());
        this.viewport = viewport;

        this.spriteComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Vector2 mouseXYWorld = this.viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        SpriteComponent spriteComponent = this.spriteComponentMapper.get(entity);
        if (spriteComponent.sprite.getBoundingRectangle().contains(mouseXYWorld)) {
            Gdx.app.log("TMP", "in Rectangle bounds !");
        }
    }
}