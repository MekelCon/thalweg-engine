package fr.thalweg.engine.infra.data.task;

import com.badlogic.gdx.utils.Array;
import org.mapstruct.factory.Mappers;

public interface ArrayCloner {

    ArrayCloner INSTANCE = Mappers.getMapper(ArrayCloner.class);

    default Array<TaskData> mapTaskData(Array<TaskData> source) {
        if (source == null) {
            return null;
        }
        var res = new Array<>(source.size);
        for (TaskData t : source.toArray())
            res.add(t.copy());
        return new Array<>(source);
    }
}
