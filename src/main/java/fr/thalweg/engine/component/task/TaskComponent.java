package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.transformer.tolibgdx.ToInterpolation;
import fr.thalweg.gen.engine.model.*;

public interface TaskComponent extends Component, Pool.Poolable {

    static TaskComponent build(Engine ecs, TaskData todo, Directory root) {
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

    private static LogTaskComponent log(Engine ecs, LogTaskData data) {
        var result = ecs.createComponent(LogTaskComponent.class);
        result.data = data;
        return result;
    }

    private static ParallelTaskComponent parallel(Engine ecs, TaskArrayData data, Directory root) {
        var res = ecs.createComponent(ParallelTaskComponent.class);
        Array<TaskComponent> taskComponents = new Array<>(data.getTodos().size());
        for (TaskData todo : data.getTodos()) {
            taskComponents.add(build(ecs, todo, root));
        }
        res.components = taskComponents;
        return res;
    }

    private static PlayTransitionTaskComponent playTransition(Engine ecsEngine, PlayTransitionTaskData data, Directory root) {
        var result = ecsEngine.createComponent(PlayTransitionTaskComponent.class);
        result.root = root;
        result.data = data;
        if (data.getInterpolation() != null) {
            result.interpolation = ToInterpolation.from(data.getInterpolation());
        }
        return result;
    }

    private static SequenceTaskComponent sequence(Engine ecs, TaskArrayData data, Directory root) {
        var res = ecs.createComponent(SequenceTaskComponent.class);
        Array<TaskComponent> taskComponents = new Array<>(data.getTodos().size());
        for (TaskData todo : data.getTodos()) {
            taskComponents.add(build(ecs, todo, root));
        }
        res.components = taskComponents;
        return res;
    }

    private static SetCursorTaskComponent setCursor(Engine ecsEngine, SetCursorTaskData data, Directory root) {
        var result = ecsEngine.createComponent(SetCursorTaskComponent.class);
        result.data = data;
        result.icon = new Pixmap(Gdx.files.internal(root.getSubFolder(data.getCursor())));
        return result;
    }

    private static SetMouseLabelTaskComponent setMouseLabel(Engine ecsEngine, SetMouseLabelTaskData data) {
        var result = ecsEngine.createComponent(SetMouseLabelTaskComponent.class);
        result.data = data;
        return result;
    }

    private static WaitTaskComponent wait(Engine ecsEngine, OverTimeTaskData data) {
        var result = ecsEngine.createComponent(WaitTaskComponent.class);
        result.data = data;
        return result;
    }
}
