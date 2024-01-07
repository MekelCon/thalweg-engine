package fr.thalweg.engine.system.task;

import com.badlogic.gdx.Gdx;
import fr.thalweg.gen.engine.model.LogTaskData;
import lombok.Builder;

@Builder
public class LogTask implements Task {

    private LogTaskData data;

    @Override
    public boolean work(float deltaTime) {
        Gdx.app.log("LogTask", data.getMessage());
        return true;
    }
}
