package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.PlayTransitionTaskComponent;

public class PlayTransitionTask extends OverTimeTask {

    private static final Class<PlayTransitionTaskComponent> COMPONENT = PlayTransitionTaskComponent.class;
    private static final Family FAMILY = Family.all(COMPONENT, WorkingFlag.class).get();
    private final ComponentMapper<PlayTransitionTaskComponent> cm;

    public PlayTransitionTask() {
        super(FAMILY);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected float getDuration(Entity entity) {
        return cm.get(entity).data.getDuration();
    }

    protected void begin(Entity entity) {
        var transitionTaskComponent = cm.get(entity);
        transitionTaskComponent.shader = createTransitionShader();
        new Texture(Gdx.files.internal(transitionTaskComponent.root.getSubFolder(transitionTaskComponent.data.getTransition())))
                .bind(1);
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
    }

    @Override
    protected void update(Entity entity, float percent) {
        var transitionTaskComponent = cm.get(entity);
        transitionTaskComponent.shader.bind();
        transitionTaskComponent.shader.setUniformf("u_transitionPercent", percent);
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
