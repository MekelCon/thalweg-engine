package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import fr.thalweg.engine.component.task.PlayTransitionTaskComponent;
import fr.thalweg.engine.system.rendering.WorldRenderingSystem;

public class PlayTransitionTask extends OverTimeTask {
    private static final Class<PlayTransitionTaskComponent> COMPONENT = PlayTransitionTaskComponent.class;
    private final ComponentMapper<PlayTransitionTaskComponent> cm;

    public PlayTransitionTask() {
        super(COMPONENT);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void begin(Entity entity) {
        super.begin(entity);
        var transitionTaskComponent = cm.get(entity);
        transitionTaskComponent.texture.bind(1);
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        getEngine().getSystem(WorldRenderingSystem.class).transitioning = true;
    }

    @Override
    protected void update(Entity entity, float percent) {
        var transitionTaskComponent = cm.get(entity);
        transitionTaskComponent.shader.bind();
        transitionTaskComponent.shader.setUniformf("u_transitionPercent", percent);
    }


    @Override
    protected void end(Entity entity) {
        super.end(entity);
        getEngine().getSystem(WorldRenderingSystem.class).transitioning = false;
    }
}
