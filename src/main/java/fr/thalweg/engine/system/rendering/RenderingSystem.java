package fr.thalweg.engine.system.rendering;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.TaskComponent;
import fr.thalweg.engine.component.ZIndexComponent;
import fr.thalweg.engine.component.flag.TransitionEntityFlag;
import fr.thalweg.engine.entity.EntityZIndexComparator;
import fr.thalweg.engine.system.task.PlayTransitionTask;
import fr.thalweg.gen.engine.model.WorldData;

public class RenderingSystem extends SortedIteratingSystem {
    private static final Matrix4 IDENTITY = new Matrix4();
    private final Array<Entity> renderQueue;
    private final ComponentMapper<SpriteComponent> sm;
    private final SpriteBatch batch;
    private final FrameBuffer worldBuffer;
    private final Viewport viewport;
    private final ComponentMapper<TaskComponent> tm;

    public RenderingSystem(WorldData world, SpriteBatch batch, Viewport viewport) {
        super(Family.all(ZIndexComponent.class, SpriteComponent.class).get(), new EntityZIndexComparator());
        this.renderQueue = new Array<>();
        this.sm = ComponentMapper.getFor(SpriteComponent.class);
        this.worldBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, world.getWidth(), world.getHeight(), false);
        this.worldBuffer.getColorBufferTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        this.batch = batch;
        this.viewport = viewport;
        this.tm = ComponentMapper.getFor(TaskComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        ScreenUtils.clear(Color.BLACK);
        drawWorldBuffer();
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

    private void renderWorldBuffer() {
        ImmutableArray<Entity> transitionEntities = this.getEngine().getEntitiesFor(
                Family.all(TransitionEntityFlag.class, TaskComponent.class).get());
        // A transition is on going
        for (Entity e : transitionEntities) {
            var taskComponent = tm.get(e);
            batch.setShader(((PlayTransitionTask) taskComponent.task).transitionShader);
        }
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
