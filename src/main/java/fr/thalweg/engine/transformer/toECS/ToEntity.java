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
import fr.thalweg.engine.infra.data.ThalwegActorData;
import fr.thalweg.engine.infra.data.XYData;
import fr.thalweg.engine.infra.data.task.TaskData;
import fr.thalweg.engine.infra.data.trigger.TriggerData;
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
        if (source.texture != null || (source.vertices != null && !source.vertices.isEmpty()) || source.position != null) {
            var zIndexComponent = ecsEngine.createComponent(ZIndexComponent.class);
            zIndexComponent.zIndex = ToZIndex.from(source.position);
            return Optional.of(zIndexComponent);
        }
        return Optional.empty();
    }

    private static Optional<SpriteComponent> handleTexture(Engine ecsEngine, Directory root, ThalwegActorData source) {
        if (source.texture != null) {
            var textureRegion = new TextureRegion(new Texture(root.getSubFolder(source.texture)));
            textureRegion.getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
            var sprite = new Sprite(textureRegion);
            var position = ToVector2Position.from(source.position);
            var scale = ToVector2Scale.from(source.scale);
            sprite.setPosition(position.x, position.y);
            sprite.setScale(scale.x, scale.y);
            var spriteComponent = ecsEngine.createComponent(SpriteComponent.class);
            spriteComponent.sprite = sprite;
            return Optional.of(spriteComponent);
        }
        return Optional.empty();
    }

    private static Optional<PolygonComponent> handleVertices(Engine ecsEngine, ThalwegActorData source) {
        if (source.vertices != null && !source.vertices.isEmpty()) {
            var vertices = new float[source.vertices.size * 2];
            for (int i = 0; i < source.vertices.size; i++) {
                XYData point = source.vertices.get(i);
                vertices[i * 2] = point.x;
                vertices[i * 2 + 1] = point.y;
            }
            var polygon = new Polygon(vertices);
            var position = ToVector2Position.from(source.position);
            var scale = ToVector2Scale.from(source.scale);
            polygon.setPosition(position.x, position.y);
            polygon.setScale(scale.x, scale.y);
            var polygonComponent = ecsEngine.createComponent(PolygonComponent.class);
            polygonComponent.polygon = polygon;
            return Optional.of(polygonComponent);
        }
        return Optional.empty();
    }

    private static Optional<Array<Component>> handleTriggers(Engine ecsEngine, ThalwegActorData source) {
        if (source.triggers != null && !source.triggers.isEmpty()) {
            Array<Component> triggerComponents = handleTrigger(ecsEngine, source.triggers);
            return Optional.of(triggerComponents);
        }
        return Optional.empty();
    }

    private static Array<Component> handleTrigger(Engine ecsEngine, Array<TriggerData> triggers) {
        var result = new Array<Component>();
        TaskData onMouseEnter = null;
        TaskData onMouseLeave = null;
        for (TriggerData triggerData : triggers) {
            if (triggerData.todo != null) {
                switch (triggerData.type) {
                    case AUTO -> result.add(createAutoTriggerComponent(ecsEngine, triggerData.todo));
                    case MOUSEENTER -> onMouseEnter = triggerData.todo;
                    case MOUSELEAVE -> onMouseLeave = triggerData.todo;
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
