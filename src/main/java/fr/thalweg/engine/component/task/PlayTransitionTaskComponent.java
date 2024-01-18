package fr.thalweg.engine.component.task;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.gen.engine.model.PlayTransitionTaskData;

public class PlayTransitionTaskComponent implements TaskComponent {
    public PlayTransitionTaskData data;
    public Directory root;
    public ShaderProgram shader;
    public Texture texture;

    @Override
    public void reset() {
        shader.dispose();
        texture.dispose();
        data = null;
        root = null;
        shader = null;
    }
}
