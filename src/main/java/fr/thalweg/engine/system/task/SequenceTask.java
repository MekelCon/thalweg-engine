package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import lombok.Builder;

@Builder
public class SequenceTask implements Task {

    public Array<Task> data;

    @Override
    public boolean work(Entity entity, float deltaTime) {
        for (Task task : data) {
            if (task.work(entity, deltaTime)) {
                data.removeIndex(0);
            }
        }
        return data.size == 0;
    }
}
