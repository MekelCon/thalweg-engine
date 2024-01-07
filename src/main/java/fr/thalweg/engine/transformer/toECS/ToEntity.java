package fr.thalweg.engine.transformer.toECS;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.gen.engine.model.LogTaskData;
import fr.thalweg.gen.engine.model.ThalwegActorData;
import fr.thalweg.engine.component.PolygonComponent;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.ZIndexComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;
import fr.thalweg.engine.entity.ActorEntity;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.system.task.LogTask;
import fr.thalweg.engine.system.task.Task;

import java.util.Optional;

public class ToEntity {


    public static ActorEntity from(Directory root, ThalwegActorData source) {
        ActorEntity result = new ActorEntity();
        handleTexture(root, source).ifPresent(result::add);
        handleVertices(source).ifPresent(result::add);
        handleZIndex(source).ifPresent(result::add);
        handleTriggers(source).ifPresent(result::add);
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

    private static Optional<MouseTriggerComponent> handleTriggers(ThalwegActorData source) {
        if (source.getTriggers() != null
                && !source.getTriggers().isEmpty()) {
            // TODO check trigger type
            // TODO : really build todo
            Array<Task> onMouseEnter = new Array<>();
            LogTaskData logTask = new LogTaskData();
            logTask.setMessage("Hello " + (source.getTexture() != null ? "Norah" : "A rectangle"));
            onMouseEnter.add(LogTask.builder().data(logTask).build());
            Array<Task> onMouseLeave = new Array<>();
            LogTaskData logTask2 = new LogTaskData();
            logTask2.setMessage("Bye " + (source.getTexture() != null ? "Norah" : "A rectangle"));
            onMouseLeave.add(LogTask.builder().data(logTask2).build());
            return Optional.of(MouseTriggerComponent.builder()
                    .onMouseEnter(onMouseEnter)
                    .onMouseLeave(onMouseLeave)
                    .build());
        }
        return Optional.empty();
    }
}
