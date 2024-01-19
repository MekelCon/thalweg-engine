package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Interpolation;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.PlayTransitionTaskComponent;
import fr.thalweg.engine.system.rendering.WorldRenderingSystem;

public class PlayTransitionTask extends OverTimeTask {
    private static final Class<PlayTransitionTaskComponent> COMPONENT = PlayTransitionTaskComponent.class;
    private static final Family FAMILY = Family.all(COMPONENT, WorkingFlag.class).get();
    private final ComponentMapper<PlayTransitionTaskComponent> cm;

    public PlayTransitionTask() {
        super(FAMILY);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected float getDelay(Entity entity) {
        var transitionTaskComponent = cm.get(entity);
        return transitionTaskComponent.data.delay;
    }

    @Override
    protected float getDuration(Entity entity) {
        var transitionTaskComponent = cm.get(entity);
        return transitionTaskComponent.data.duration;
    }

    @Override
    protected Interpolation getInterpolation(Entity entity) {
        var transitionTaskComponent = cm.get(entity);
        return transitionTaskComponent.interpolation;
    }

    @Override
    protected void begin(Entity entity) {
        super.begin(entity);
        var transitionTaskComponent = cm.get(entity);
        transitionTaskComponent.shader = createTransitionShader();
        transitionTaskComponent.texture = new Texture(Gdx.files.internal(
                transitionTaskComponent.root.getSubFolder(transitionTaskComponent.data.transition)));
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
        var transitionTaskComponent = cm.get(entity);
        transitionTaskComponent.shader.dispose();
        transitionTaskComponent.texture.dispose();
    }

    private ShaderProgram createTransitionShader() {
        var vertexShader = Gdx.files.internal("shader/vertex.glsl").readString();
        var fragmentShader = Gdx.files.internal("shader/fragment.glsl").readString();
        var transitionShader = new ShaderProgram(vertexShader, fragmentShader);
        if (!transitionShader.isCompiled()) {
            Gdx.app.error("Shader", transitionShader.getLog());
            Gdx.app.exit();
        }
        transitionShader.bind();
        transitionShader.setUniformi("u_texture", 0);
        transitionShader.setUniformi("u_mask", 1);
        return transitionShader;
    }
}
