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
        handleTexture(root, source).ifPresent(result::add);
        handleVertices(source).ifPresent(result::add);
        handleZIndex(source).ifPresent(result::add);
        handleTriggers(root, source).ifPresent(triggerComponents -> triggerComponents.forEach(result::add));
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

    private static Optional<Array<Component>> handleTriggers(Directory root, ThalwegActorData source) {
        if (source.getTriggers() != null
                && !source.getTriggers().isEmpty()) {
            Array<Component> triggerComponents = handleMouseTrigger(root, source.getTriggers());
            return Optional.of(triggerComponents);
        }
        return Optional.empty();
    }

    private static Array<Component> handleMouseTrigger(Directory root, List<TriggerData> triggers) {
        var result = new Array<Component>();
        Component onMouseEnter = null;
        Component onMouseLeave = null;
        for (TriggerData triggerData : triggers) {
            switch (triggerData.getType()) {
                case AUTO -> result.add(AutoTriggerComponent.builder()
                        .todo(handleTask(root, triggerData.getTodo())).build());
                case MOUSEENTER -> onMouseEnter = handleTask(root, triggerData.getTodo());
                case MOUSELEAVE -> onMouseLeave = handleTask(root, triggerData.getTodo());
            }
        }
        if (onMouseEnter != null
                || onMouseLeave != null) {
            var mouseTriggerComponent = MouseTriggerComponent.builder()
                    .build();
            mouseTriggerComponent.onMouseEnter = onMouseEnter;
            mouseTriggerComponent.onMouseLeave = onMouseLeave;
            result.add(mouseTriggerComponent);
        }
        return result;
    }

    private static Component handleTask(Directory root, TaskData data) {
        return switch (data.getType()) {
            case LOG -> createLogTask((LogTaskData) data);
            case PARALLEL -> createParallelTask(root, (TaskArrayData) data);
            case PLAY_TRANSITION -> createPlayTransitionTask(root, (PlayTransitionTaskData) data);
            case SEQUENCE -> createSequenceTask(root, (TaskArrayData) data);
            case SET_MOUSE_LABEL -> createSetMouseLabelTask((SetMouseLabelTaskData) data);
        };
    }

    private static LogTaskComponent createLogTask(LogTaskData data) {
        return LogTaskComponent.builder().data(data).build();
    }

    private static ParallelTaskComponent createParallelTask(Directory root, TaskArrayData data) {
        Array<Component> taskArray = new Array<>(data.getTodos().size());
        for (TaskData taskData : data.getTodos()) {
            taskArray.add(handleTask(root, taskData));
        }
        return ParallelTaskComponent.builder().components(taskArray).build();
    }

    private static PlayTransitionTaskComponent createPlayTransitionTask(Directory root, PlayTransitionTaskData data) {
        return PlayTransitionTaskComponent.builder()
                .root(root)
                .data(data)
                .build();
    }

    private static SequenceTaskComponent createSequenceTask(Directory root, TaskArrayData data) {
        Array<Component> components = new Array<>(data.getTodos().size());
        for (TaskData taskData : data.getTodos()) {
            components.add(handleTask(root, taskData));
        }
        return SequenceTaskComponent.builder().components(components).build();
    }

    private static SetMouseLabelTaskComponent createSetMouseLabelTask(SetMouseLabelTaskData data) {
        return SetMouseLabelTaskComponent.builder().data(data).build();
    }
}
