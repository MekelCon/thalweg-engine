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
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.component.TextureComponent;
import fr.thalweg.engine.component.TransformComponent;
import fr.thalweg.engine.entity.EntityComparator;
import fr.thalweg.engine.gen.World;

public class RenderingSystem extends SortedIteratingSystem {
    private static final Matrix4 IDENTITY = new Matrix4();
    private final Array<Entity> renderQueue;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final FrameBuffer worldBuffer;
    private final ComponentMapper<TextureComponent> textureMapper;
    private final ComponentMapper<TransformComponent> transformMapper;
    private final Viewport viewport;


    public RenderingSystem(World world, SpriteBatch batch, OrthographicCamera camera, Viewport viewport) {
        super(
                Family.all(TransformComponent.class, TextureComponent.class).get(),
                new EntityComparator(),
                1
        );
        this.renderQueue = new Array<>();
        this.batch = batch;
        this.camera = camera;
        this.worldBuffer = new FrameBuffer(
                Pixmap.Format.RGBA8888,
                world.getWidth(),
                world.getHeight(),
                false);
        this.worldBuffer.getColorBufferTexture().setFilter(
                Texture.TextureFilter.Nearest,
                Texture.TextureFilter.Nearest);

        this.viewport = viewport;

        this.textureMapper = ComponentMapper.getFor(TextureComponent.class);
        this.transformMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.updateWorld();
        this.drawWorldBuffer();
        this.displayWorldBuffer();
    }

    private void updateWorld() {
        this.batch.setProjectionMatrix(camera.combined);
    }

    private void drawWorldBuffer() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

        this.worldBuffer.begin();
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
        this.batch.flush();
        this.worldBuffer.end();
    }

    private void displayWorldBuffer() {
        // Save current batch state
        Matrix4 originalMatrix = this.batch.getProjectionMatrix();
        ShaderProgram originalShader = this.batch.getShader();
        int originalBlendSrcFunc = this.batch.getBlendSrcFunc();
        int originalBlendDstFunc = this.batch.getBlendDstFunc();

        viewport.apply(true);

        batch.disableBlending();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
        this.batch.setProjectionMatrix(IDENTITY);
        this.batch.draw(this.worldBuffer.getColorBufferTexture(), -1, -1, 2, 2);
        this.batch.end();
        batch.enableBlending();

        // Restore batch state
        this.batch.setShader(originalShader);
        this.batch.setProjectionMatrix(originalMatrix);
        this.batch.setBlendFunction(originalBlendSrcFunc, originalBlendDstFunc);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Gdx.app.debug("RenderingSystem", "processEntity");
        renderQueue.add(entity);
    }
}
