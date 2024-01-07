package fr.thalweg.engine.transformer.toECS;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.component.PolygonComponent;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.ZIndexComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;
import fr.thalweg.engine.component.trigger.TriggerComponent;
import fr.thalweg.engine.entity.ActorEntity;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.system.task.ChangeCursorTask;
import fr.thalweg.engine.system.task.LogTask;
import fr.thalweg.engine.system.task.Task;
import fr.thalweg.gen.engine.model.*;

import java.util.List;
import java.util.Optional;

public class ToEntity {


    public static ActorEntity from(Directory root, ThalwegActorData source) {
        ActorEntity result = new ActorEntity();
        handleTexture(root, source).ifPresent(result::add);
        handleVertices(source).ifPresent(result::add);
        handleZIndex(source).ifPresent(result::add);
        handleTriggers(source).ifPresent(triggerComponents -> triggerComponents.forEach(result::add));
        return result;
    }

    private static Optional<ZIndexComponent> handleZIndex(ThalwegActorData source) {
        if (source.getTexture() != null
                || !source.getVertices().isEmpty()
                || source.getPosition() != null) {
            return Optional.of(ZIndexComponent.builder()
                    .zIndex(ToZIndex.from(source.getPosition()))
                    .build());
        }
        return Optional.empty();
    }

    private static Optional<SpriteComponent> handleTexture(Directory root, ThalwegActorData source) {
        if (source.getTexture() != null) {
            TextureRegion textureRegion = new TextureRegion(new Texture(
                    root.getSubFolder(source.getTexture())));
            textureRegion.getTexture().setFilter(
                    Texture.TextureFilter.Nearest,
                    Texture.TextureFilter.Nearest);
            Sprite sprite = new Sprite(textureRegion);
            Vector2 position = ToVector2Position.from(source.getPosition());
            Vector2 scale = ToVector2Scale.from(source.getScale());
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
            float[] vertices = new float[source.getVertices().size()];
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = source.getVertices().get(i);
            }
            Polygon polygon = new Polygon(vertices);
            Vector2 position = ToVector2Position.from(source.getPosition());
            Vector2 scale = ToVector2Scale.from(source.getScale());
            polygon.setPosition(position.x, position.y);
            polygon.setScale(scale.x, scale.y);
            return Optional.of(PolygonComponent.builder()
                    .polygon(polygon)
                    .build());
        }
        return Optional.empty();
    }

    private static Optional<Array<TriggerComponent>> handleTriggers(ThalwegActorData source) {
        if (source.getTriggers() != null
                && !source.getTriggers().isEmpty()) {
            Array<TriggerComponent> triggerComponents = new Array<>(source.getTriggers().size());
            handleMouseTrigger(source.getTriggers()).ifPresent(triggerComponents::add);
            return Optional.of(triggerComponents);
        }
        return Optional.empty();
    }

    private static Optional<MouseTriggerComponent> handleMouseTrigger(List<TriggerData> triggers) {
        Optional<Array<Task>> onMouseEnter = triggers.stream()
                .filter(triggerData -> TriggerTypeEnumData.MOUSEENTER.equals(triggerData.getType())
                        && triggerData.getTodos() != null
                        && !triggerData.getTodos().isEmpty())
                .findFirst()
                .map(triggerData -> handleTodos(triggerData.getTodos()));
        Optional<Array<Task>> onMouseLeave = triggers.stream()
                .filter(triggerData -> TriggerTypeEnumData.MOUSELEAVE.equals(triggerData.getType())
                        && triggerData.getTodos() != null
                        && !triggerData.getTodos().isEmpty())
                .findFirst()
                .map(triggerData -> handleTodos(triggerData.getTodos()));
        if (onMouseEnter.isPresent()
                || onMouseLeave.isPresent()) {
            MouseTriggerComponent result = MouseTriggerComponent.builder()
                    .build();
            onMouseEnter.ifPresent(taskArray -> result.onMouseEnter = taskArray);
            onMouseLeave.ifPresent(taskArray -> result.onMouseLeave = taskArray);
            return Optional.of(result);
        }
        return Optional.empty();
    }

    private static Array<Task> handleTodos(List<TaskData> todos) {
        Array<Task> result = new Array<>(todos.size());
        for (TaskData taskData : todos) {
            result.add(handleTaskData(taskData));
        }
        return result;
    }

    private static Task handleTaskData(TaskData data) {
        return switch (data.getType()) {
            case LOG -> createLogTask((LogTaskData) data);
            case CHANGE_CURSOR -> createChangeCursorTask((ChangeCursorTaskData) data);
        };
    }

    private static LogTask createLogTask(LogTaskData data) {
        return LogTask.builder().data(data).build();
    }

    private static ChangeCursorTask createChangeCursorTask(ChangeCursorTaskData data) {
        return ChangeCursorTask.builder().data(data).build();
    }
}
