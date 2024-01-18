package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Pixmap;
import fr.thalweg.gen.engine.model.SetCursorTaskData;

public class SetCursorTaskComponent implements Component {

    public SetCursorTaskData data;
    public Pixmap icon;
}
