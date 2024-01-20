package fr.thalweg.engine.transformer.toECS;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.component.PolygonComponent;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.ZIndexComponent;
import fr.thalweg.engine.component.trigger.AutoTriggerComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;
import fr.thalweg.engine.infra.data.TaskData;
import fr.thalweg.engine.infra.data.ThalwegActorData;
import fr.thalweg.engine.infra.data.TriggerData;
import fr.thalweg.engine.infra.data.XYData;
import fr.thalweg.engine.model.Directory;

import java.util.Optional;

public class ToEntity {


    public static Entity from(Engine ecsEngine, Directory root, ThalwegActorData source) {
        var result = ecsEngine.createEntity();
        handleTexture(ecsEngine, root, source).ifPresent(result::add);
        handleVertices(ecsEngine, source).ifPresent(result::add);
        handleZIndex(ecsEngine, source).ifPresent(result::add);
        handleTriggers(ecsEngine, source).ifPresent(triggerComponents -> triggerComponents.forEach(result::add));
        return result;
    }

    private static Optional<ZIndexComponent> handleZIndex(Engine ecsEngine, ThalwegActorData source) {
        if (source.getTexture() != null || (source.getVertices() != null && !source.getVertices().isEmpty()) || source.getPosition() != null) {
            var zIndexComponent = ecsEngine.createComponent(ZIndexComponent.class);
            zIndexComponent.zIndex = ToZIndex.from(source.getPosition());
            return Optional.of(zIndexComponent);
        }
        return Optional.empty();
    }

    private static Optional<SpriteComponent> handleTexture(Engine ecsEngine, Directory root, ThalwegActorData source) {
        if (source.getTexture() != null) {
            var textureRegion = new TextureRegion(new Texture(root.getSubFolder(source.getTexture())));
            textureRegion.getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
            var sprite = new Sprite(textureRegion);
            var position = ToVector2Position.from(source.getPosition());
            var scale = ToVector2Scale.from(source.getScale());
            sprite.setPosition(position.x, position.y);
            sprite.setScale(scale.x, scale.y);
            var spriteComponent = ecsEngine.createComponent(SpriteComponent.class);
            spriteComponent.sprite = sprite;
            return Optional.of(spriteComponent);
        }
        return Optional.empty();
    }

    private static Optional<PolygonComponent> handleVertices(Engine ecsEngine, ThalwegActorData source) {
        if (source.getVertices() != null && !source.getVertices().isEmpty()) {
            var vertices = new float[source.getVertices().size * 2];
            for (int i = 0; i < source.getVertices().size; i++) {
                XYData point = source.getVertices().get(i);
                vertices[i * 2] = point.x;
                vertices[i * 2 + 1] = point.y;
            }
            var polygon = new Polygon(vertices);
            var position = ToVector2Position.from(source.getPosition());
            var scale = ToVector2Scale.from(source.getScale());
            polygon.setPosition(position.x, position.y);
            polygon.setScale(scale.x, scale.y);
            var polygonComponent = ecsEngine.createComponent(PolygonComponent.class);
            polygonComponent.polygon = polygon;
            return Optional.of(polygonComponent);
        }
        return Optional.empty();
    }

    private static Optional<Array<Component>> handleTriggers(Engine ecsEngine, ThalwegActorData source) {
        if (source.getTriggers() != null && !source.getTriggers().isEmpty()) {
            Array<Component> triggerComponents = handleTrigger(ecsEngine, source.getTriggers());
            return Optional.of(triggerComponents);
        }
        return Optional.empty();
    }

    private static Array<Component> handleTrigger(Engine ecsEngine, Array<TriggerData> triggers) {
        var result = new Array<Component>();
        TaskData onMouseEnter = null;
        TaskData onMouseLeave = null;
        for (TriggerData triggerData : triggers) {
            if (triggerData.getType() != null && triggerData.getTodo() != null) {
                switch (triggerData.getType()) {
                    case AUTO -> result.add(createAutoTriggerComponent(ecsEngine, triggerData.getTodo()));
                    case MOUSEENTER -> onMouseEnter = triggerData.getTodo();
                    case MOUSELEAVE -> onMouseLeave = triggerData.getTodo();
                }
            }
        }
        if (onMouseEnter != null || onMouseLeave != null) {
            var mouseTriggerComponent = ecsEngine.createComponent(MouseTriggerComponent.class);
            mouseTriggerComponent.onMouseEnter = onMouseEnter;
            mouseTriggerComponent.onMouseLeave = onMouseLeave;
            result.add(mouseTriggerComponent);
        }
        return result;
    }

    private static AutoTriggerComponent createAutoTriggerComponent(Engine ecsEngine, TaskData todo) {
        var autoTriggerComponent = ecsEngine.createComponent(AutoTriggerComponent.class);
        autoTriggerComponent.todo = todo;
        return autoTriggerComponent;
    }
}
