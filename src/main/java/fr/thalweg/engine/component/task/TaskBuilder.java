package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.transformer.tolibgdx.ToInterpolation;

public interface TaskBuilder {

    static TaskComp build(Engine ecs, TaskComp todo, Directory root) {
        return switch (todo.type) {
            case LOG -> log(ecs, (LogTaskComp) todo);
            case PARALLEL -> parallel(ecs, (ParallelTaskComp) todo);
            case PLAY_TRANSITION -> playTransition(ecs, (PlayTransitionTaskComp) todo, root);
            case SEQUENCE -> sequence(ecs, (SequenceTaskComp) todo);
            case SET_CURSOR -> setCursor(ecs, (SetCursorTaskComp) todo, root);
            case SET_MOUSE_LABEL -> setMouseLabel(ecs, (SetMouseLabelTaskComp) todo);
            case WAIT -> wait(ecs, (WaitTaskComp) todo);
        };
    }

    private static LogTaskComp log(Engine ecs, LogTaskComp comp) {
        var result = ecs.createComponent(LogTaskComp.class);
        TaskCompUpdater.INSTANCE.updateInternal(result, comp);
        return result;
    }

    private static ParallelTaskComp parallel(Engine ecs, ParallelTaskComp comp) {
        var result = ecs.createComponent(ParallelTaskComp.class);
        TaskCompUpdater.INSTANCE.updateInternal(result, comp);
        result._executors = new Array<>(comp.todos.size);
        return result;
    }

    private static PlayTransitionTaskComp playTransition(Engine ecs, PlayTransitionTaskComp comp, Directory root) {
        var result = ecs.createComponent(PlayTransitionTaskComp.class);
        TaskCompUpdater.INSTANCE.updateInternal(result, comp);
        result._interpolation = ToInterpolation.from(comp.interpolation);
        result._root = root;
        result._texture = new Texture(Gdx.files.internal(
                root.getSubFolder(comp.transition)));
        result._shader = createTransitionShader();
        return result;
    }

    private static SequenceTaskComp sequence(Engine ecs, SequenceTaskComp comp) {
        var result = ecs.createComponent(SequenceTaskComp.class);
        TaskCompUpdater.INSTANCE.updateInternal(result, comp);
        return result;
    }

    private static SetCursorTaskComp setCursor(Engine ecs, SetCursorTaskComp comp, Directory root) {
        var result = ecs.createComponent(SetCursorTaskComp.class);
        TaskCompUpdater.INSTANCE.updateInternal(result, comp);
        result._icon = new Pixmap(Gdx.files.internal(root.getSubFolder(result.cursor)));
        return result;
    }

    private static SetMouseLabelTaskComp setMouseLabel(Engine ecs, SetMouseLabelTaskComp comp) {
        var result = ecs.createComponent(SetMouseLabelTaskComp.class);
        TaskCompUpdater.INSTANCE.updateInternal(result, comp);
        return result;
    }

    private static WaitTaskComp wait(Engine ecs, WaitTaskComp comp) {
        var result = ecs.createComponent(WaitTaskComp.class);
        TaskCompUpdater.INSTANCE.updateInternal(result, comp);
        result._interpolation = ToInterpolation.from(result.interpolation);
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
