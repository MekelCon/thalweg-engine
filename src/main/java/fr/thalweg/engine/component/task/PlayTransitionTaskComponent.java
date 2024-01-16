package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.gen.engine.model.PlayTransitionTaskData;

public class PlayTransitionTaskComponent implements Component {
    public PlayTransitionTaskData data;
    public Directory root;
    public ShaderProgram shader;
}
