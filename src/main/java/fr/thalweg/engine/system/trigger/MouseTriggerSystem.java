package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.component.TextureComponent;
import fr.thalweg.engine.component.TransformComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;

public class MouseTriggerSystem extends IteratingSystem {

    private final Viewport viewport;
    private final ComponentMapper<MouseTriggerComponent> mouseTriggerCompMapper;
    private final ComponentMapper<TextureComponent> textureCompMapper;
    private final ComponentMapper<TransformComponent> transformCompMapper;

    public MouseTriggerSystem(Viewport viewport) {
        super(Family.all(MouseTriggerComponent.class, TextureComponent.class, TransformComponent.class).get());
        this.viewport = viewport;

        this.mouseTriggerCompMapper = ComponentMapper.getFor(MouseTriggerComponent.class);
        this.textureCompMapper = ComponentMapper.getFor(TextureComponent.class);
        this.transformCompMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Vector2 mouseXYWorld = this.viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        TransformComponent transformComponent = this.transformCompMapper.get(entity);
        TextureComponent textureComponent = textureCompMapper.get(entity);
        Vector2 mouseXYRelative = worldToSprite(transformComponent.pos, mouseXYWorld);
        if (isInRectangleBound(mouseXYRelative, textureComponent)) {
            Gdx.app.log("TMP", "in Rectangle bounds !");
            MouseTriggerComponent mouseTriggerComponent = mouseTriggerCompMapper.get(entity);
            if (isOnMask(mouseXYRelative, mouseTriggerComponent, textureComponent)) {
                Gdx.app.log("TMP", "HIT !");
            }
        }
    }

    private boolean isInRectangleBound(Vector2 mouseXYRelative, TextureComponent transformComponent) {
        return new Rectangle(
                0,
                0,
                transformComponent.region.getRegionWidth(),
                transformComponent.region.getRegionHeight())
                .contains(mouseXYRelative);
    }

    private boolean isOnMask(Vector2 mouseXYRelative, MouseTriggerComponent mouseTriggerComponent, TextureComponent textureComponent) {
        if (mouseTriggerComponent.polygon == null
                && textureComponent == null) {
            return true;
        }
        if (mouseTriggerComponent.polygon != null
                && mouseTriggerComponent.polygon.contains(mouseXYRelative)) {
            return true;
        }
        if (textureComponent != null) {
            if (!textureComponent.region.getTexture().getTextureData().isPrepared()) {
                textureComponent.region.getTexture().getTextureData().prepare();
            }
            int colorInt = textureComponent.region.getTexture().getTextureData().consumePixmap().getPixel((int) mouseXYRelative.x, (int) mouseXYRelative.y);
            return colorInt != 0;
        }
        return false;
    }

    private Vector2 worldToSprite(Vector3 pos, Vector2 mouseXY) {
        return new Vector2(mouseXY.x - pos.x, mouseXY.y - pos.y);
    }
}