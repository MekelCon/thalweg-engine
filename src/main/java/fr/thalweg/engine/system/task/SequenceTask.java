package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import lombok.Builder;

@Builder
public class SequenceTask implements Task {

    public Array<Task> data;
    public int currentIndex;

    @Override
    public boolean work(Entity entity, float deltaTime) {
        if (data.get(currentIndex).work(entity, deltaTime)) {
            currentIndex++;
        }
        return currentIndex >= data.size;
    }

    @Override
    public void added() {
        Gdx.app.log("TMP", "ADDED !");
        currentIndex = 0;
    }
}
