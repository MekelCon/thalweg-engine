package fr.thalweg.engine.transformer;

import fr.thalweg.gen.engine.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

@Mapper(mappingControl = DeepClone.class)
public interface DataCloner {

    DataCloner INSTANCE = Mappers.getMapper(DataCloner.class);

    default TaskData clone(TaskData taskData) {
        return switch (taskData.getType()) {
            case LOG -> clone((LogTaskData) taskData);
            case PARALLEL -> clone((ParallelTaskData) taskData);
            case PLAY_TRANSITION -> clone((PlayTransitionTaskData) taskData);
            case SEQUENCE -> clone((SequenceTaskData) taskData);
            case SET_CURSOR -> clone((SetCursorTaskData) taskData);
            case SET_MOUSE_LABEL -> clone((SetMouseLabelTaskData) taskData);
            case WAIT -> clone((OverTimeTaskData) taskData);
        };
    }

    LogTaskData clone(LogTaskData taskData);

    ParallelTaskData clone(ParallelTaskData taskData);

    PlayTransitionTaskData clone(PlayTransitionTaskData taskData);

    SequenceTaskData clone(SequenceTaskData taskData);

    SetCursorTaskData clone(SetCursorTaskData taskData);

    SetMouseLabelTaskData clone(SetMouseLabelTaskData taskData);

    OverTimeTaskData clone(OverTimeTaskData taskData);

}
