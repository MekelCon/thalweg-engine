package fr.thalweg.engine.system.task;

import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.infra.schema.task.LogTaskSchema;
import lombok.Builder;

@Builder
public class LogTask implements Task {

    private LogTaskSchema data;

    @Override
    public boolean work(float deltaTime) {
        Gdx.app.log("LogTask", data.getMessage());
        return true;
    }
}
