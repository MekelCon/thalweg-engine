package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
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
