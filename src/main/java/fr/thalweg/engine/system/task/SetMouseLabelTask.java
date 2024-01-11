package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Engine;
import fr.thalweg.engine.component.TextComponent;
import fr.thalweg.gen.engine.model.SetMouseLabelTaskData;
import lombok.Builder;

@Builder
public class SetMouseLabelTask implements Task {

    private final Engine ecsEngine;
    private SetMouseLabelTaskData data;

    @Override
    public boolean work(float deltaTime) {
        ecsEngine.addEntity(ecsEngine.createEntity()
                .add(TextComponent.builder()
                        .onMouse(true)
                        .text(data.getLabel())
                        .build()));
        return true;
    }
}
