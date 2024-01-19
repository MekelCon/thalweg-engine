package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Pool;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.gen.engine.model.PlayTransitionTaskData;

public class PlayTransitionTaskComponent implements Component, Pool.Poolable {

    public PlayTransitionTaskData data;

    public Directory root;
    public ShaderProgram shader;
    public Texture texture;

    public Interpolation interpolation;

    @Override
    public void reset() {
        root = null;
        shader = null;
        texture = null;
    }
}
