package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.gen.engine.model.PlayTransitionTaskData;
import lombok.Builder;

public class PlayTransitionTask extends TemporalTask {

    public final ShaderProgram transitionShader;
    private final Directory root;
    public PlayTransitionTaskData data;

    @Builder
    public PlayTransitionTask(PlayTransitionTaskData data, Directory root, Entity transitionEntity) {
        super(data);
        this.transitionShader = createTransitionShader();
        this.data = data;
        this.root = root;
    }

    @Override
    protected void begin(Entity entity) {
        super.begin(entity);
        new Texture(Gdx.files.internal(root.getSubFolder(data.getTransition())))
                .bind(1);
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
    }

    @Override
    protected void update(Entity entity, float percent) {
        transitionShader.bind();
        transitionShader.setUniformf("u_transitionPercent", percent);
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
