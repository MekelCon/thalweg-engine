package fr.thalweg.engine.infra.data.task;

import com.badlogic.gdx.utils.Array;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

@Mapper(mappingControl = DeepClone.class)
interface TaskClone {

    TaskClone INSTANCE = Mappers.getMapper(TaskClone.class);

    default TaskData copy(TaskData source) {
        return switch (source.type) {
            case LOG -> copyInternal((LogTaskData) source);
            case PARALLEL -> copyInternal((ParallelTaskData) source);
            case PLAY_TRANSITION -> copyInternal((PlayTransitionTaskData) source);
            case SEQUENCE -> copyInternal((SequenceTaskData) source);
            case SET_CURSOR -> copyInternal((SetCursorTaskData) source);
            case SET_MOUSE_LABEL -> copyInternal((SetMouseLabelTaskData) source);
            case WAIT -> copyInternal((WaitTaskData) source);
        };
    }

    default Array<TaskData> copy(Array<TaskData> source) {
        if (source == null) {
            return null;
        }
        var res = new Array<>(source.size);
        for (TaskData t : source.toArray())
            res.add(INSTANCE.copy(t));
        return new Array<>(source);
    }

    LogTaskData copyInternal(LogTaskData source);

    ParallelTaskData copyInternal(ParallelTaskData source);

    PlayTransitionTaskData copyInternal(PlayTransitionTaskData source);

    SequenceTaskData copyInternal(SequenceTaskData source);

    SetCursorTaskData copyInternal(SetCursorTaskData source);

    SetMouseLabelTaskData copyInternal(SetMouseLabelTaskData source);

    WaitTaskData copyInternal(WaitTaskData source);
}
