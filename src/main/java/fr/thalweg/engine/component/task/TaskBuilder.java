package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.infra.data.task.*;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.transformer.tolibgdx.ToInterpolation;

public interface TaskBuilder {

    static Component build(Engine ecs, TaskData todo, Directory root) {
        return switch (todo.type) {
            case LOG -> log(ecs, (LogTaskData) todo);
            case PARALLEL -> parallel(ecs, (TaskArrayData) todo, root);
            case PLAY_TRANSITION -> playTransition(ecs, (PlayTransitionTaskData) todo, root);
            case SEQUENCE -> sequence(ecs, (TaskArrayData) todo, root);
            case SET_CURSOR -> setCursor(ecs, (SetCursorTaskData) todo, root);
            case SET_MOUSE_LABEL -> setMouseLabel(ecs, (SetMouseLabelTaskData) todo);
            case WAIT -> wait(ecs, (WaitTaskData) todo);
        };
    }

    private static LogTaskComponent log(Engine ecs, LogTaskData data) {
        var result = ecs.createComponent(LogTaskComponent.class);
        result.data = data.copy();
        return result;
    }

    private static ParallelTaskComponent parallel(Engine ecs, TaskArrayData data, Directory root) {
        var result = ecs.createComponent(ParallelTaskComponent.class);
        result._executors = new Array<>(data.todos.size);
        for (TaskData todo : data.todos) {
            result.components.add(build(ecs, todo, root));
        }
        return result;
    }

    private static PlayTransitionTaskComponent playTransition(Engine ecs, PlayTransitionTaskData data, Directory root) {
        var result = ecs.createComponent(PlayTransitionTaskComponent.class);
        result.data = data.copy();
        result.interpolation = ToInterpolation.from(data.interpolation);
        result.root = root;
        result.texture = new Texture(Gdx.files.internal(
                root.getSubFolder(data.transition)));
        result.shader = createTransitionShader();
        return result;
    }

    private static SequenceTaskComponent sequence(Engine ecs, TaskArrayData data, Directory root) {
        var result = ecs.createComponent(SequenceTaskComponent.class);
        for (TaskData todo : data.todos) {
            result.components.add(build(ecs, todo, root));
        }
        return result;
    }

    private static SetCursorTaskComponent setCursor(Engine ecs, SetCursorTaskData data, Directory root) {
        var result = ecs.createComponent(SetCursorTaskComponent.class);
        result.data = data.copy();
        result.icon = new Pixmap(Gdx.files.internal(root.getSubFolder(data.cursor)));
        return result;
    }

    private static SetMouseLabelTaskComponent setMouseLabel(Engine ecs, SetMouseLabelTaskData data) {
        var result = ecs.createComponent(SetMouseLabelTaskComponent.class);
        result.data = data.copy();
        return result;
    }

    private static WaitTaskComponent wait(Engine ecs, WaitTaskData data) {
        WaitTaskComponent result = ecs.createComponent(WaitTaskComponent.class);
        result.data = data.copy();
        result.interpolation = ToInterpolation.from(data.interpolation);
        return result;
    }

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
}
