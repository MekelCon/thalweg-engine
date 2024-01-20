package fr.thalweg.engine.component.task;

import com.badlogic.gdx.graphics.Pixmap;
import fr.thalweg.engine.infra.data.SetCursorTaskData;

public class SetCursorTaskComponent implements TaskComponent {

    public SetCursorTaskData data;
    public Pixmap icon;

    @Override
    public void reset() {
        data = null;
        icon.dispose();
        icon = null;
    }
}
