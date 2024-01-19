package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Pool;
import fr.thalweg.gen.engine.model.SetCursorTaskData;

public class SetCursorTaskComponent implements Component, Pool.Poolable {

    public SetCursorTaskData data;
    public Pixmap icon;

    @Override
    public void reset() {
        data = null;
        icon = null;
    }
}
