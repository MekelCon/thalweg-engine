package fr.thalweg.engine.component.task;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import fr.thalweg.engine.model.Directory;

public class PlayTransitionTaskComp extends OverTimeTaskComp {
    public String transition;

    public Directory _root;
    public ShaderProgram _shader;
    public Texture _texture;

    @Override
    public void reset() {
        super.reset();
        transition = null;
        _root = null;
        _shader.dispose();
        _shader = null;
        _texture.dispose();
        _texture = null;
    }
}
