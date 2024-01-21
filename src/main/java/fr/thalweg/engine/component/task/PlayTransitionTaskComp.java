package fr.thalweg.engine.component.task;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import fr.thalweg.engine.ThalwegGame;

public class PlayTransitionTaskComp extends OverTimeTaskComp {
    public String transition;
    public ShaderProgram _shader;
    public Texture _texture;

    private static ShaderProgram createTransitionShader() {
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

    @Override
    public void build() {
        super.build();
        _texture = new Texture(Gdx.files.internal(ThalwegGame.INSTANCE.getRoot()
                .getSubFolder(transition)));
        _shader = createTransitionShader();
    }

    @Override
    public void reset() {
        super.reset();
        transition = null;
        _shader.dispose();
        _shader = null;
        _texture.dispose();
        _texture = null;
    }
}
