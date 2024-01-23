package fr.thalweg.engine;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import fr.thalweg.engine.component.task.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

import java.util.function.BiConsumer;

@Mapper(mappingControl = DeepClone.class)
public abstract class TaskUpdater {
    public final static ObjectMap<String, BiConsumer<TaskComp, TaskComp>> MAPPERS = new ObjectMap<>(30);
    final static TaskUpdater INSTANCE = Mappers.getMapper(TaskUpdater.class);

    static {
        MAPPERS.put(TaskTypeEnumData.LOG.value, (source, target) ->
                INSTANCE.updateInternal((LogTaskComp) source, (LogTaskComp) target));
        MAPPERS.put(TaskTypeEnumData.PARALLEL.value, (source, target) ->
                INSTANCE.updateInternal((ParallelTaskComp) source, (ParallelTaskComp) target));
        MAPPERS.put(TaskTypeEnumData.PLAY_TRANSITION.value, (source, target) ->
                INSTANCE.updateInternal((PlayTransitionTaskComp) source, (PlayTransitionTaskComp) target));
        MAPPERS.put(TaskTypeEnumData.SEQUENCE.value, (source, target) ->
                INSTANCE.updateInternal((SequenceTaskComp) source, (SequenceTaskComp) target));
        MAPPERS.put(TaskTypeEnumData.SET_CURSOR.value, (source, target) ->
                INSTANCE.updateInternal((SetCursorTaskComp) source, (SetCursorTaskComp) target));
        MAPPERS.put(TaskTypeEnumData.SET_MOUSE_LABEL.value, (source, target) ->
                INSTANCE.updateInternal((SetMouseLabelTaskComp) source, (SetMouseLabelTaskComp) target));
        MAPPERS.put(TaskTypeEnumData.WAIT.value, (source, target) ->
                INSTANCE.updateInternal((WaitTaskComp) source, (WaitTaskComp) target));
    }

    public void update(TaskComp source, TaskComp target) {
        MAPPERS.get(source.type.value).accept(source, target);
    }

    public abstract void updateInternal(LogTaskComp source, @MappingTarget LogTaskComp target);

    @Mapping(target = "_executors", ignore = true)
    public abstract void updateInternal(ParallelTaskComp source, @MappingTarget ParallelTaskComp target);

    @Mapping(target = "_interpolation", ignore = true)
    @Mapping(target = "_shader", ignore = true)
    @Mapping(target = "_texture", ignore = true)
    public abstract void updateInternal(PlayTransitionTaskComp source, @MappingTarget PlayTransitionTaskComp target);

    @Mapping(target = "_executor", ignore = true)
    public abstract void updateInternal(SequenceTaskComp source, @MappingTarget SequenceTaskComp target);

    @Mapping(target = "_icon", ignore = true)
    public abstract void updateInternal(SetCursorTaskComp source, @MappingTarget SetCursorTaskComp target);

    @Mapping(target = "_interpolation", ignore = true)
    public abstract void updateInternal(SetMouseLabelTaskComp source, @MappingTarget SetMouseLabelTaskComp target);

    @Mapping(target = "_interpolation", ignore = true)
    public abstract void updateInternal(WaitTaskComp source, @MappingTarget WaitTaskComp target);

    public <T extends TaskComp> Array<T> updateInternal(Array<T> source) {
        if (source == null) {
            return null;
        }
        var res = new Array<T>(source.size);
        for (T t : source) {
            res.add(ThalwegGame.INSTANCE.getEcsEngine().createTaskComponent(t));
        }
        return res;
    }
}