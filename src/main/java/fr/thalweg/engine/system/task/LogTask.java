package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import fr.thalweg.gen.engine.model.LogTaskData;
import lombok.Builder;

@Builder
public class LogTask implements Task {

    public LogTaskData data;

    @Override
    public boolean work(Entity entity, float deltaTime) {
        Gdx.app.log("LOG TASK", data.getMessage());
        return true;
    }
}
