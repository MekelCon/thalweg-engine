package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import lombok.Builder;

@Builder
public class ParallelTask implements Task {

    public Array<Task> data;

    @Override
    public boolean work(Entity entity, float deltaTime) {
        boolean ended = true;
        for (Task task : data) {
            if (!task.work(entity, deltaTime)) {
                ended = false;
            }
        }
        return ended;
    }
}
