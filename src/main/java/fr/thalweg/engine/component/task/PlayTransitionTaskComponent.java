package fr.thalweg.engine.component.task;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Interpolation;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.gen.engine.model.OverTimeTaskData;
import fr.thalweg.gen.engine.model.PlayTransitionTaskData;

public class PlayTransitionTaskComponent extends OverTimeTaskComponent {

    public PlayTransitionTaskData data;

    public Directory root;
    public ShaderProgram shader;
    public Texture texture;

    public Interpolation interpolation;

    @Override
    public OverTimeTaskData getData() {
        return data;
    }

    @Override
    public void reset() {
        super.reset();
        root = null;
        data = null;
        shader = null;
        texture = null;
    }
}
