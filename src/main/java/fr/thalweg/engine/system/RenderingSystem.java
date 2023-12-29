package fr.thalweg.engine.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.ThalwegGame;
import fr.thalweg.engine.component.TextureComponent;
import fr.thalweg.engine.component.TransformComponent;
import fr.thalweg.engine.entity.EntityComparator;

public class RenderingSystem extends SortedIteratingSystem {
    private final Array<Entity> renderQueue;
    private final SpriteBatch batch;
    private final FrameBuffer worldBuffer;
    private final ComponentMapper<TextureComponent> textureMapper;
    private final ComponentMapper<TransformComponent> transformMapper;

    public RenderingSystem(SpriteBatch batch) {
        super(
                Family.all(TransformComponent.class, TextureComponent.class).get(),
                new EntityComparator(),
                1
        );
        this.renderQueue = new Array<>();
        this.batch = batch;
        this.worldBuffer = new FrameBuffer(
                Pixmap.Format.RGBA8888,
                ThalwegGame.get().getConfig().getVirtualScreen().getWidth(),
                ThalwegGame.get().getConfig().getVirtualScreen().getHeight(),
                false);
        this.worldBuffer.getColorBufferTexture().setFilter(
                Texture.TextureFilter.Nearest,
                Texture.TextureFilter.Nearest);
        this.textureMapper = ComponentMapper.getFor(TextureComponent.class);
        this.transformMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Matrix4 m = new Matrix4();
        m.setToOrtho2D(0, 0, 256, 256);
        this.batch.setProjectionMatrix(m);

        this.worldBuffer.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

        this.batch.begin();
        for (Entity entity : renderQueue) {
            TextureComponent textureComponent = textureMapper.get(entity);
            TransformComponent transformComponent = transformMapper.get(entity);
            batch.draw(
                    textureComponent.region,
                    transformComponent.pos.x,
                    transformComponent.pos.y,
                    0,
                    0,
                    textureComponent.region.getRegionWidth(),
                    textureComponent.region.getRegionHeight(),
                    transformComponent.scale.x,
                    transformComponent.scale.y,
                    transformComponent.rotation);
        }
        this.batch.end();
        this.worldBuffer.end();

        Matrix4 m2 = new Matrix4();
        m2.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.batch.setProjectionMatrix(m2);

        batch.disableBlending();
        batch.begin();
        batch.draw(
                worldBuffer.getColorBufferTexture(),
                0,
                Gdx.graphics.getHeight() + 500,
                256 * 5,
                -256 * 5);
        this.batch.end();
        batch.enableBlending();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Gdx.app.debug("RenderingSystem", "processEntity");
        renderQueue.add(entity);
    }
}
