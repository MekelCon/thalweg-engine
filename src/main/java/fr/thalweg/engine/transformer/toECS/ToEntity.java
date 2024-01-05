package fr.thalweg.engine.transformer.toECS;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import fr.thalweg.engine.component.PolygonComponent;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.ZIndexComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;
import fr.thalweg.engine.entity.ActorEntity;
import fr.thalweg.engine.gen.ThalwegActorSchema;
import fr.thalweg.engine.model.Directory;

import java.util.Optional;

public class ToEntity {


    public static ActorEntity from(Directory root, ThalwegActorSchema source) {
        ActorEntity result = new ActorEntity();
        handleTexture(root, source).ifPresent(result::add);
        handleVertices(source).ifPresent(result::add);
        handleZIndex(source).ifPresent(result::add);
        handleTriggers(source).ifPresent(result::add);

        return result;
    }

    private static Optional<ZIndexComponent> handleZIndex(ThalwegActorSchema source) {
        if (source.getTexture() != null) {
            return Optional.of(ZIndexComponent.builder()
                    .zIndex(ToZIndex.from(source.getPosition()))
                    .build());
        }
        return Optional.empty();
    }

    private static Optional<SpriteComponent> handleTexture(Directory root, ThalwegActorSchema source) {
        if (source.getTexture() != null
                && source.getPosition() != null) {
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

    private static Optional<PolygonComponent> handleVertices(ThalwegActorSchema source) {
        if (!source.getVertices().isEmpty()) {
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

    private static Optional<MouseTriggerComponent> handleTriggers(ThalwegActorSchema source) {
        if (!source.getTriggers().isEmpty()) {
            // TODO check trigger type
            return Optional.of(MouseTriggerComponent.builder()
                    .build());
        }
        return Optional.empty();
    }
}
