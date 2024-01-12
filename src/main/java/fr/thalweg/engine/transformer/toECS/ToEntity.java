package fr.thalweg.engine.transformer.toECS;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.component.PolygonComponent;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.ZIndexComponent;
import fr.thalweg.engine.component.task.LogTaskComponent;
import fr.thalweg.engine.component.task.SetMouseLabelTaskComponent;
import fr.thalweg.engine.component.trigger.AutoTriggerComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.gen.engine.model.*;

import java.util.List;
import java.util.Optional;

public class ToEntity {


    public static Entity from(PooledEngine ecsEngine, Directory root, ThalwegActorData source) {
        var result = ecsEngine.createEntity();
        handleTexture(root, source).ifPresent(result::add);
        handleVertices(source).ifPresent(result::add);
        handleZIndex(source).ifPresent(result::add);
        handleTriggers(source).ifPresent(triggerComponents -> triggerComponents.forEach(result::add));
        return result;
    }

    private static Optional<ZIndexComponent> handleZIndex(ThalwegActorData source) {
        if (source.getTexture() != null
                || (source.getVertices() != null && !source.getVertices().isEmpty())
                || source.getPosition() != null) {
            return Optional.of(ZIndexComponent.builder()
                    .zIndex(ToZIndex.from(source.getPosition()))
                    .build());
        }
        return Optional.empty();
    }

    private static Optional<SpriteComponent> handleTexture(Directory root, ThalwegActorData source) {
        if (source.getTexture() != null) {
            var textureRegion = new TextureRegion(new Texture(
                    root.getSubFolder(source.getTexture())));
            textureRegion.getTexture().setFilter(
                    Texture.TextureFilter.Nearest,
                    Texture.TextureFilter.Nearest);
            var sprite = new Sprite(textureRegion);
            var position = ToVector2Position.from(source.getPosition());
            var scale = ToVector2Scale.from(source.getScale());
            sprite.setPosition(position.x, position.y);
            sprite.setScale(scale.x, scale.y);
            return Optional.of(SpriteComponent.builder()
                    .sprite(sprite)
                    .build());
        }
        return Optional.empty();
    }

    private static Optional<PolygonComponent> handleVertices(ThalwegActorData source) {
        if (source.getVertices() != null
                && !source.getVertices().isEmpty()) {
            var vertices = new float[source.getVertices().size()];
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = source.getVertices().get(i);
            }
            var polygon = new Polygon(vertices);
            var position = ToVector2Position.from(source.getPosition());
            var scale = ToVector2Scale.from(source.getScale());
            polygon.setPosition(position.x, position.y);
            polygon.setScale(scale.x, scale.y);
            return Optional.of(PolygonComponent.builder()
                    .polygon(polygon)
                    .build());
        }
        return Optional.empty();
    }

    private static Optional<Array<Component>> handleTriggers(ThalwegActorData source) {
        if (source.getTriggers() != null
                && !source.getTriggers().isEmpty()) {
            Array<Component> triggerComponents = handleMouseTrigger(source.getTriggers());
            return Optional.of(triggerComponents);
        }
        return Optional.empty();
    }

    private static Array<Component> handleMouseTrigger(List<TriggerData> triggers) {
        var result = new Array<Component>();
        var onMouseEnter = new Array<Component>();
        var onMouseLeave = new Array<Component>();
        for (TriggerData triggerData : triggers) {
            switch (triggerData.getType()) {
                case AUTO -> result.add(AutoTriggerComponent.builder()
                        .todos(handleTask(triggerData.getTodos()))
                        .build());
                case MOUSEENTER -> onMouseEnter = handleTask(triggerData.getTodos());
                case MOUSELEAVE -> onMouseLeave = handleTask(triggerData.getTodos());
            }
        }
        if (!onMouseEnter.isEmpty()
                || !onMouseLeave.isEmpty()) {
            var mouseTriggerComponent = MouseTriggerComponent.builder()
                    .build();
            mouseTriggerComponent.onMouseEnter = onMouseEnter;
            mouseTriggerComponent.onMouseLeave = onMouseLeave;
            result.add(mouseTriggerComponent);
        }
        return result;
    }

    private static Array<Component> handleTask(List<TaskData> todos) {
        var result = new Array<Component>(todos.size());
        for (TaskData taskData : todos) {
            result.add(handleTask(taskData));
        }
        return result;
    }

    private static Component handleTask(TaskData data) {
        return switch (data.getType()) {
            case LOG -> createLogTask((LogTaskData) data);
            case SET_MOUSE_LABEL -> createSetMouseLabel((SetMouseLabelTaskData) data);
        };
    }

    private static LogTaskComponent createLogTask(LogTaskData data) {
        return LogTaskComponent.builder().data(data).build();
    }

    private static SetMouseLabelTaskComponent createSetMouseLabel(SetMouseLabelTaskData data) {
        return SetMouseLabelTaskComponent.builder()
                .data(data).build();
    }
}
