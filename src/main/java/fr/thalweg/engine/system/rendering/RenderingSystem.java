package fr.thalweg.engine.system.rendering;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.ZIndexComponent;
import fr.thalweg.engine.entity.EntityZIndexComparator;
import fr.thalweg.gen.engine.model.WorldData;

public class RenderingSystem extends SortedIteratingSystem {
    private static final Matrix4 IDENTITY = new Matrix4();
    private final Array<Entity> renderQueue;
    private final ComponentMapper<SpriteComponent> sm;
    private final SpriteBatch batch;
    private final FrameBuffer worldBuffer;
    private final Viewport viewport;
    private final ShaderProgram transitionShader;
    private float sumDeltaTime;
    private float transitionPercent;


    public RenderingSystem(WorldData world, SpriteBatch batch, Viewport viewport) {
        super(
                Family.all(ZIndexComponent.class, SpriteComponent.class).get(),
                new EntityZIndexComparator()
        );
        this.renderQueue = new Array<>();
        this.sm = ComponentMapper.getFor(SpriteComponent.class);
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


        var vertexShader = Gdx.files.internal("shader/vertex.glsl").readString();
        var fragmentShader = Gdx.files.internal("shader/fragment.glsl").readString();
        transitionShader = new ShaderProgram(vertexShader, fragmentShader);
        if (!transitionShader.isCompiled()) {
            Gdx.app.error("Shader", transitionShader.getLog());
            Gdx.app.exit();
        }
        transitionShader.bind();
        transitionShader.setUniformi("u_texture", 0);
        transitionShader.setUniformi("u_mask", 1);
        new Texture(
                Gdx.files.internal("quercus/transition/screen_transition_2.png")).bind(1);
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        ScreenUtils.clear(Color.BLACK);
        drawWorldBuffer();
        calcTransitionPercent(deltaTime);
        renderWorldBuffer();
        renderQueue.clear();
    }

    private void drawWorldBuffer() {
        batch.setShader(null);
        batch.setColor(Color.WHITE);
        worldBuffer.begin();
        batch.begin();
        for (Entity entity : renderQueue) {
            var spriteComponent = sm.get(entity);
            spriteComponent.sprite.draw(batch);
        }
        batch.flush();
        worldBuffer.end();
    }

    private void calcTransitionPercent(float deltaTime) {
        if (transitionPercent < 1) {
            sumDeltaTime += deltaTime;
            transitionPercent = sumDeltaTime / 0.5f;
            batch.setShader(transitionShader);
            transitionShader.setUniformf("u_transitionPercent", transitionPercent);
        }
    }

    private void renderWorldBuffer() {
        viewport.apply(true);
        batch.setProjectionMatrix(IDENTITY);
        batch.draw(worldBuffer.getColorBufferTexture(), -1, -1, 2, 2);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
