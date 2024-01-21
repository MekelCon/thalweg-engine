package fr.thalweg.engine.component.task;

import com.badlogic.gdx.graphics.Pixmap;

public class SetCursorTaskComp extends TaskComp {

    public String cursor;
    public int xHotspot = 0;
    public int yHotspot = 0;
    public Pixmap _icon;

    @Override
    public void reset() {
        cursor = null;
        xHotspot = 0;
        yHotspot = 0;
        _icon.dispose();
        _icon = null;
    }
}
