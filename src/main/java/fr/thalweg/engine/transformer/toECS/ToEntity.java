package fr.thalweg.engine.transformer.toECS;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import fr.thalweg.engine.component.TextureComponent;
import fr.thalweg.engine.component.TransformComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;
import fr.thalweg.engine.entity.ActorEntity;
import fr.thalweg.engine.gen.ThalwegActorSchema;
import fr.thalweg.engine.model.Directory;

import java.util.Optional;

public class ToEntity {


    public static ActorEntity from(Directory root, ThalwegActorSchema source) {
        ActorEntity result = new ActorEntity();
        handleTexture(root, source).ifPresent(result::add);
        handlePosition(source).ifPresent(result::add);
        handleTriggers(source).ifPresent(result::add);

        return result;
    }

    private static Optional<TextureComponent> handleTexture(Directory root, ThalwegActorSchema source) {
        if (source.getTexture() != null) {
            Texture t = new Texture(
                    root.getSubFolder(source.getTexture()));
            t.setFilter(
                    Texture.TextureFilter.Nearest,
                    Texture.TextureFilter.Nearest);
            return Optional.of(TextureComponent.builder()
                    .region(new TextureRegion(t))
                    .build());
        }
        return Optional.empty();
    }

    private static Optional<TransformComponent> handlePosition(ThalwegActorSchema source) {
        if (source.getPosition() != null) {
            return Optional.of(TransformComponent.builder()
                    .pos(new Vector3(
                            source.getPosition().getX(),
                            source.getPosition().getY(),
                            source.getPosition().getZ()))
                    .scale(ToVector2Scale.from(source.getScale()))
                    .build());
        }
        return Optional.empty();
    }

    private static Optional<MouseTriggerComponent> handleTriggers(ThalwegActorSchema source) {
        if (source.getTriggers() != null) {
            // TODO more check
            if (!source.getTriggers().isEmpty() &&
                    source.getTriggers().get(0).getVertices() != null) {
                float[] vertices = new float[source.getTriggers().get(0).getVertices().size()];
                for (int i = 0; i < vertices.length; i++) {
                    vertices[i] = source.getTriggers().get(0).getVertices().get(i);
                }
                return Optional.of(MouseTriggerComponent
                        .builder()
                        .polygon(new Polygon(vertices))
                        .build());
            }
        }
        return Optional.empty();
    }
}
