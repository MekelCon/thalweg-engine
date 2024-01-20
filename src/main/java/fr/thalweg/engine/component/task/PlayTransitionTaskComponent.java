package fr.thalweg.engine.component.task;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import fr.thalweg.engine.infra.data.task.PlayTransitionTaskData;
import fr.thalweg.engine.model.Directory;

public class PlayTransitionTaskComponent extends OverTimeTaskComponent<PlayTransitionTaskData> {

    public Directory root;
    public ShaderProgram shader;
    public Texture texture;

    @Override
    public void reset() {
        super.reset();
        root = null;
        data = null;
        shader.dispose();
        shader = null;
        texture.dispose();
        texture = null;
    }
}
