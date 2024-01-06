package fr.thalweg.engine.infra.schema.task;

import com.badlogic.gdx.Gdx;

public class ChangeCursorTask extends AbstractTask {
    private String cursor;
    private Integer xHotspot = 0;
    private Integer yHotspot = 0;

    @Override
    public boolean work(float deltaTime) {
        Gdx.app.log("ChangeCursorTask", "Impl todo");
        return true;
    }
}

