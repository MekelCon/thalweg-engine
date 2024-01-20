package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import fr.thalweg.engine.infra.data.*;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.transformer.tolibgdx.ToInterpolation;

public interface TaskBuilder {

    static Component build(Engine ecs, TaskData todo, Directory root) {
        return switch (todo.getType()) {
            case LOG -> log(ecs, (LogTaskData) todo);
            case PARALLEL -> parallel(ecs, (TaskArrayData) todo, root);
            case PLAY_TRANSITION -> playTransition(ecs, (PlayTransitionTaskData) todo, root);
            case SEQUENCE -> sequence(ecs, (TaskArrayData) todo, root);
            case SET_CURSOR -> setCursor(ecs, (SetCursorTaskData) todo, root);
            case SET_MOUSE_LABEL -> setMouseLabel(ecs, (SetMouseLabelTaskData) todo);
            case WAIT -> wait(ecs, (WaitTaskData) todo);
        };
    }

    private static Component log(Engine ecs, LogTaskData data) {
        var result = ecs.createComponent(LogTaskComponent.class);
        result.data = data.copy();
        return result;
    }

    private static ParallelTaskComponent parallel(Engine ecs, TaskArrayData data, Directory root) {
        var result = ecs.createComponent(ParallelTaskComponent.class);
        for (TaskData todo : data.getTodos()) {
            result.components.add(build(ecs, todo, root));
        }
        return result;
    }

    private static PlayTransitionTaskComponent playTransition(Engine ecs, PlayTransitionTaskData data, Directory root) {
        var result = ecs.createComponent(PlayTransitionTaskComponent.class);
        result.data = data.copy();
        result.interpolation = ToInterpolation.from(data.getInterpolation());
        result.root = root;
        return result;
    }

    private static SequenceTaskComponent sequence(Engine ecs, TaskArrayData data, Directory root) {
        var result = ecs.createComponent(SequenceTaskComponent.class);
        for (TaskData todo : data.getTodos()) {
            result.components.add(build(ecs, todo, root));
        }
        return result;
    }

    private static SetCursorTaskComponent setCursor(Engine ecs, SetCursorTaskData data, Directory root) {
        var result = ecs.createComponent(SetCursorTaskComponent.class);
        result.data = data.copy();
        result.icon = new Pixmap(Gdx.files.internal(root.getSubFolder(data.getCursor())));
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
}
