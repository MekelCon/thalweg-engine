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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import fr.thalweg.engine.ThalwegGame;
import fr.thalweg.engine.component.TextureComponent;
import fr.thalweg.engine.component.TransformComponent;
import fr.thalweg.engine.entity.EntityComparator;

public class RenderingSystem extends SortedIteratingSystem {
    private final Array<Entity> renderQueue;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
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
        this.camera = new OrthographicCamera();
        camera.setToOrtho(
                true,
                ThalwegGame.get().getConfig().getVirtualScreen().getWidth(),
                ThalwegGame.get().getConfig().getVirtualScreen().getHeight()
        );
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
        camera.update();
        this.batch.setProjectionMatrix(camera.combined);

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


        FitViewport viewport = new FitViewport(
                256,
                256
        );
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.apply(true);
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.disableBlending();
        batch.begin();
        batch.draw(
                worldBuffer.getColorBufferTexture(),
                0,
                0);
        this.batch.end();
        batch.enableBlending();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Gdx.app.debug("RenderingSystem", "processEntity");
        renderQueue.add(entity);
    }
}
