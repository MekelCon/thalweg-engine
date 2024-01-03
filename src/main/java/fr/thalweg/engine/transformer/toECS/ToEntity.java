package fr.thalweg.engine.transformer.toECS;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import fr.thalweg.engine.component.TextureComponent;
import fr.thalweg.engine.component.TransformComponent;
import fr.thalweg.engine.entity.ActorEntity;
import fr.thalweg.engine.gen.ThalwegActorSchema;
import fr.thalweg.engine.model.Directory;

public class ToEntity {

    public static ActorEntity from(Directory root, ThalwegActorSchema source) {
        ActorEntity result = new ActorEntity();

        if (source.getTexture() != null) {
            Texture t = new Texture(
                    root.getSubFolder(source.getTexture()));
            t.setFilter(
                    Texture.TextureFilter.Nearest,
                    Texture.TextureFilter.Nearest);
            result.add(TextureComponent.builder()
                    .region(new TextureRegion(t))
                    .build());
        }

        if (source.getPosition() != null) {
            result.add(TransformComponent.builder()
                    .pos(new Vector3(
                            source.getPosition().getX(),
                            source.getPosition().getY(),
                            source.getPosition().getZ()))
                    .scale(ToVector2Scale.from(source.getScale()))
                    .build());
        }
        return result;
    }
}
