package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.SetMouseLabelComponent;
import fr.thalweg.gen.engine.model.SetMouseLabelTaskData;
import lombok.Builder;

@Builder
public class SetMouseLabelTask implements Task {

    public SetMouseLabelTaskData data;

    @Override
    public boolean work(Entity entity, float deltaTime) {
        entity.add(SetMouseLabelComponent.builder().caller(this).build());
        return true;
    }
}
