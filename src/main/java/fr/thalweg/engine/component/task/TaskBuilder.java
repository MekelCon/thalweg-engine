package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.transformer.tolibgdx.ToInterpolation;
import fr.thalweg.gen.engine.model.*;

public interface TaskBuilder {

    static Component build(Engine ecs, TaskData todo, Directory root) {
        return switch (todo.getType()) {
            case LOG -> log(ecs, (LogTaskData) todo);
            case PARALLEL -> parallel(ecs, (TaskArrayData) todo, root);
            case PLAY_TRANSITION -> playTransition(ecs, (PlayTransitionTaskData) todo, root);
            case SEQUENCE -> sequence(ecs, (TaskArrayData) todo, root);
            case SET_CURSOR -> setCursor(ecs, (SetCursorTaskData) todo, root);
            case SET_MOUSE_LABEL -> setMouseLabel(ecs, (SetMouseLabelTaskData) todo);
            case WAIT -> wait(ecs, (OverTimeTaskData) todo);
        };
    }

    private static Component log(Engine ecs, LogTaskData data) {
        var result = ecs.createComponent(LogTaskComponent.class);
        result.data = data;
        return result;
    }

    private static ParallelTaskComponent parallel(Engine ecs, TaskArrayData data, Directory root) {
        var result = ecs.createComponent(ParallelTaskComponent.class);
        Array<Component> taskComponents = new Array<>(data.getTodos().size());
        for (TaskData todo : data.getTodos()) {
            taskComponents.addAll(build(ecs, todo, root));
        }
        result.components = taskComponents;

        return result;
    }

    private static PlayTransitionTaskComponent playTransition(Engine ecs, PlayTransitionTaskData data, Directory root) {
        var result = ecs.createComponent(PlayTransitionTaskComponent.class);
        result.data = data;
        result.interpolation = ToInterpolation.from(data.getInterpolation());
        result.root = root;
        return result;
    }

    private static SequenceTaskComponent sequence(Engine ecs, TaskArrayData data, Directory root) {
        var result = ecs.createComponent(SequenceTaskComponent.class);
        Array<Component> taskComponents = new Array<>(data.getTodos().size());
        for (TaskData todo : data.getTodos()) {
            taskComponents.addAll(build(ecs, todo, root));
        }
        result.components = taskComponents;
        return result;
    }

    private static Component setCursor(Engine ecs, SetCursorTaskData data, Directory root) {
        var result = ecs.createComponent(SetCursorTaskComponent.class);
        result.data = data;
        result.icon = new Pixmap(Gdx.files.internal(root.getSubFolder(data.getCursor())));
        return result;
    }

    private static Component setMouseLabel(Engine ecs, SetMouseLabelTaskData data) {
        var result = ecs.createComponent(SetMouseLabelTaskComponent.class);
        result.data = data;
        return result;
    }

    private static WaitTaskComponent wait(Engine ecs, OverTimeTaskData data) {
        WaitTaskComponent result = ecs.createComponent(WaitTaskComponent.class);
        result.data = data;
        result.interpolation = ToInterpolation.from(data.interpolation);
        return result;
    }
}
