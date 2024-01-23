package fr.thalweg.engine.system.rendering;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.component.PolygonComponent;
import fr.thalweg.engine.component.SpriteComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;

public class MouseTriggerDebugRenderingSystem extends IteratingSystem {
    private final Array<Entity> renderQueue;
    private final ShapeRenderer shape;
    private final ComponentMapper<SpriteComponent> sm;
    private final ComponentMapper<PolygonComponent> pm;
    private final Viewport viewport;

    public MouseTriggerDebugRenderingSystem(Viewport viewport) {
        super(Family.all(MouseTriggerComponent.class).get(), 1);
        this.shape = new ShapeRenderer();
        this.renderQueue = new Array<>();
        this.sm = ComponentMapper.getFor(SpriteComponent.class);
        this.pm = ComponentMapper.getFor(PolygonComponent.class);
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        shape.setProjectionMatrix(viewport.getCamera().combined);
        shape.begin(ShapeRenderer.ShapeType.Line);
        for (Entity entity : renderQueue) {
            drawSpriteBounds(entity);
            drawPolygonBounds(entity);
        }
        shape.end();
        renderQueue.clear();
    }

    private void drawSpriteBounds(Entity entity) {
        var spriteComponent = sm.get(entity);
        if (spriteComponent != null) {
            Rectangle rectangle = spriteComponent.sprite.getBoundingRectangle();
            shape.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }

    private void drawPolygonBounds(Entity entity) {
        var polygonComponent = pm.get(entity);
        if (polygonComponent != null) {
            int verticesLength = polygonComponent.polygon.getTransformedVertices().length;
            shape.line(
                    polygonComponent.polygon.getTransformedVertices()[0],
                    polygonComponent.polygon.getTransformedVertices()[1],
                    polygonComponent.polygon.getTransformedVertices()[verticesLength - 2],
                    polygonComponent.polygon.getTransformedVertices()[verticesLength - 1]);
            for (int i = 0; i < verticesLength - 2; i = i + 2) {
                shape.line(
                        polygonComponent.polygon.getTransformedVertices()[i],
                        polygonComponent.polygon.getTransformedVertices()[i + 1],
                        polygonComponent.polygon.getTransformedVertices()[i + 2],
                        polygonComponent.polygon.getTransformedVertices()[i + 3]);
            }
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}