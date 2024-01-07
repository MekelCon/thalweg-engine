package fr.thalweg.engine.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.gen.engine.model.WorldData;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.ZIndexComponent;
import fr.thalweg.engine.entity.EntityZIndexComparator;

public class RenderingSystem extends SortedIteratingSystem {
    private static final Matrix4 IDENTITY = new Matrix4();
    private final Array<Entity> renderQueue;
    private final ComponentMapper<SpriteComponent> spriteComponentMapper;
    private final SpriteBatch batch;
    private final FrameBuffer worldBuffer;
    private final Viewport viewport;


    public RenderingSystem(WorldData world, SpriteBatch batch, Viewport viewport) {
        super(
                Family.all(ZIndexComponent.class, SpriteComponent.class).get(),
                new EntityZIndexComparator()
        );
        this.renderQueue = new Array<>();
        this.spriteComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
        this.worldBuffer = new FrameBuffer(
                Pixmap.Format.RGBA8888,
                world.getWidth(),
                world.getHeight(),
                false);
        this.worldBuffer.getColorBufferTexture().setFilter(
                Texture.TextureFilter.Nearest,
                Texture.TextureFilter.Nearest);

        this.batch = batch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        drawWorldBuffer();
        displayWorldBuffer();
        renderQueue.clear();
    }

    private void drawWorldBuffer() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

        worldBuffer.begin();
        batch.begin();
        for (Entity entity : renderQueue) {
            SpriteComponent spriteComponent = spriteComponentMapper.get(entity);
            spriteComponent.sprite.draw(batch);
        }
        batch.flush();
        worldBuffer.end();
    }

    private void displayWorldBuffer() {
        // Save current batch state
        Matrix4 originalMatrix = batch.getProjectionMatrix();
        ShaderProgram originalShader = batch.getShader();
        int originalBlendSrcFunc = batch.getBlendSrcFunc();
        int originalBlendDstFunc = batch.getBlendDstFunc();

        viewport.apply(true);

        batch.disableBlending();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
        batch.setProjectionMatrix(IDENTITY);
        batch.draw(worldBuffer.getColorBufferTexture(), -1, -1, 2, 2);
        batch.end();
        batch.enableBlending();

        // Restore batch state
        batch.setShader(originalShader);
        batch.setProjectionMatrix(originalMatrix);
        batch.setBlendFunction(originalBlendSrcFunc, originalBlendDstFunc);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
