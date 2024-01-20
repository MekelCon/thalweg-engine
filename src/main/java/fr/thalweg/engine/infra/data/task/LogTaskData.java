package fr.thalweg.engine.infra.data.task;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class LogTaskData extends TaskData {
    public String message;

    public LogTaskData() {
        super();
    }

    @Override
    public LogTaskData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        LogTaskData clone(LogTaskData source);
    }
}

