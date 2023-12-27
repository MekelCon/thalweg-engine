package fr.thalweg.engine.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.thalweg.engine.component.TextureComponent;
import fr.thalweg.engine.component.TransformComponent;
import fr.thalweg.engine.entity.EntityComparator;

public class RenderingSystem extends SortedIteratingSystem {
    private final SpriteBatch batch;
    private final ComponentMapper<TextureComponent> textureMapper;
    private final ComponentMapper<TransformComponent> transformMapper;

    public RenderingSystem(SpriteBatch batch) {
        super(
                Family.all(TransformComponent.class, TextureComponent.class).get(),
                new EntityComparator(),
                1
        );
        this.batch = batch;
        this.textureMapper = ComponentMapper.getFor(TextureComponent.class);
        this.transformMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Gdx.app.debug("RenderingSystem", "processEntity");
        TextureComponent textureComponent = textureMapper.get(entity);
        TransformComponent transformComponent = transformMapper.get(entity);

        batch.begin();

        batch.draw(
                textureComponent.region,
                0,
                0,
                0,
                0,
                textureComponent.region.getRegionWidth(),
                textureComponent.region.getRegionHeight(),
                transformComponent.scale.x,
                transformComponent.scale.y,
                transformComponent.rotation);

        batch.end();
    }
}
