package fr.thalweg.engine.component.task;

import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.ThalwegGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

@Mapper(mappingControl = DeepClone.class)
interface TaskCompUpdater {

    TaskCompUpdater INSTANCE = Mappers.getMapper(TaskCompUpdater.class);

    void updateInternal(@MappingTarget LogTaskComp existing, LogTaskComp source);

    @Mapping(target = "_executors", ignore = true)
    void updateInternal(@MappingTarget ParallelTaskComp existing, ParallelTaskComp base);

    @Mapping(target = "_interpolation", ignore = true)
    @Mapping(target = "_root", ignore = true)
    @Mapping(target = "_shader", ignore = true)
    @Mapping(target = "_texture", ignore = true)
    void updateInternal(@MappingTarget PlayTransitionTaskComp existing, PlayTransitionTaskComp source);

    @Mapping(target = "_executor", ignore = true)
    void updateInternal(@MappingTarget SequenceTaskComp existing, SequenceTaskComp source);

    @Mapping(target = "_icon", ignore = true)
    void updateInternal(@MappingTarget SetCursorTaskComp existing, SetCursorTaskComp source);

    @Mapping(target = "_interpolation", ignore = true)
    void updateInternal(@MappingTarget SetMouseLabelTaskComp existing, SetMouseLabelTaskComp source);

    @Mapping(target = "_interpolation", ignore = true)
    void updateInternal(@MappingTarget WaitTaskComp existing, WaitTaskComp source);

    default <T extends TaskComp> Array<T> updateInternal(Array<T> source) {
        if (source == null) {
            return null;
        }
        var res = new Array<T>(source.size);
        for (T t : source) {
            res.add((T) TaskBuilder.build(ThalwegGame.INSTANCE.getEcsEngine(), t, ThalwegGame.INSTANCE.getRoot()));
        }
        return res;
    }
}
