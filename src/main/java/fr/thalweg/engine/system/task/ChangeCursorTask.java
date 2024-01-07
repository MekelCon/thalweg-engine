package fr.thalweg.engine.system.task;

import com.badlogic.gdx.Gdx;
import com.thalweg.gen.engine.model.ChangeCursorTaskData;
import lombok.Builder;

@Builder
public class ChangeCursorTask implements Task {
    private ChangeCursorTaskData data;

    @Override
    public boolean work(float deltaTime) {
        Gdx.app.log("ChangeCursorTask", data.getCursor());
        return true;
    }
}

