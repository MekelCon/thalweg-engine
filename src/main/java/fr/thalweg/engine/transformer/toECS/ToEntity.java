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
import fr.thalweg.engine.component.task.*;
import fr.thalweg.engine.component.trigger.AutoTriggerComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.gen.engine.model.*;

import java.util.List;
import java.util.Optional;

public class ToEntity {


    public static Entity from(Engine ecsEngine, Directory root, ThalwegActorData source) {
        var result = ecsEngine.createEntity();
        handleTexture(ecsEngine, root, source).ifPresent(result::add);
        handleVertices(ecsEngine, source).ifPresent(result::add);
        handleZIndex(ecsEngine, source).ifPresent(result::add);
        handleTriggers(ecsEngine, root, source).ifPresent(triggerComponents -> triggerComponents.forEach(result::add));
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
            var vertices = new float[source.getVertices().size()];
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = source.getVertices().get(i);
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

    private static Optional<Array<Component>> handleTriggers(Engine ecsEngine, Directory root, ThalwegActorData source) {
        if (source.getTriggers() != null && !source.getTriggers().isEmpty()) {
            Array<Component> triggerComponents = handleTrigger(ecsEngine, root, source.getTriggers());
            return Optional.of(triggerComponents);
        }
        return Optional.empty();
    }

    private static Array<Component> handleTrigger(Engine ecsEngine, Directory root, List<TriggerData> triggers) {
        var result = new Array<Component>();
        Component onMouseEnter = null;
        Component onMouseLeave = null;
        for (TriggerData triggerData : triggers) {
            if (triggerData.getType() != null && triggerData.getTodo() != null) {
                switch (triggerData.getType()) {
                    case AUTO -> result.add(createAutoTriggerComponent(ecsEngine, root, triggerData.getTodo()));
                    case MOUSEENTER -> onMouseEnter = handleTask(ecsEngine, root, triggerData.getTodo());
                    case MOUSELEAVE -> onMouseLeave = handleTask(ecsEngine, root, triggerData.getTodo());
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

    private static AutoTriggerComponent createAutoTriggerComponent(Engine ecsEngine, Directory root, TaskData todo) {
        var autoTriggerComponent = ecsEngine.createComponent(AutoTriggerComponent.class);
        autoTriggerComponent.todo = handleTask(ecsEngine, root, todo);
        return autoTriggerComponent;
    }

    private static Component handleTask(Engine ecsEngine, Directory root, TaskData data) {
        return switch (data.getType()) {
            case LOG -> createLogTask(ecsEngine, (LogTaskData) data);
            case PARALLEL -> createParallelTask(ecsEngine, root, (TaskArrayData) data);
            case PLAY_TRANSITION -> createPlayTransitionTask(ecsEngine, root, (PlayTransitionTaskData) data);
            case SEQUENCE -> createSequenceTask(ecsEngine, root, (TaskArrayData) data);
            case SET_MOUSE_LABEL -> createSetMouseLabelTask(ecsEngine, (SetMouseLabelTaskData) data);
        };
    }

    private static LogTaskComponent createLogTask(Engine ecsEngine, LogTaskData data) {
        var result = ecsEngine.createComponent(LogTaskComponent.class);
        result.data = data;
        return result;
    }

    private static ParallelTaskComponent createParallelTask(Engine ecsEngine, Directory root, TaskArrayData data) {
        Array<Component> taskArray = new Array<>(data.getTodos().size());
        for (TaskData taskData : data.getTodos()) {
            taskArray.add(handleTask(ecsEngine, root, taskData));
        }
        var result = ecsEngine.createComponent(ParallelTaskComponent.class);
        result.components = taskArray;
        return result;
    }

    private static PlayTransitionTaskComponent createPlayTransitionTask(Engine ecsEngine, Directory root, PlayTransitionTaskData data) {
        var result = ecsEngine.createComponent(PlayTransitionTaskComponent.class);
        result.root = root;
        result.data = data;
        return result;
    }

    private static SequenceTaskComponent createSequenceTask(Engine ecsEngine, Directory root, TaskArrayData data) {
        Array<Component> components = new Array<>(data.getTodos().size());
        for (TaskData taskData : data.getTodos()) {
            components.add(handleTask(ecsEngine, root, taskData));
        }
        var result = ecsEngine.createComponent(SequenceTaskComponent.class);
        result.components = components;
        return result;
    }

    private static SetMouseLabelTaskComponent createSetMouseLabelTask(Engine ecsEngine, SetMouseLabelTaskData data) {
        var result = ecsEngine.createComponent(SetMouseLabelTaskComponent.class);
        result.data = data;
        return result;
    }
}
