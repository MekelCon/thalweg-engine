package fr.thalweg.engine.system.task.oneshot;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.component.task.SetCursorTaskComp;
import fr.thalweg.engine.system.rendering.TextRenderingSystem;

public class SetCursorTask extends OneShotTask {

    private static final Class<SetCursorTaskComp> COMPONENT = SetCursorTaskComp.class;
    private final ComponentMapper<SetCursorTaskComp> cm;

    public SetCursorTask() {
        super(COMPONENT);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void work(Entity entity) {
        var changeCursorTaskComponent = cm.get(entity);
        Gdx.graphics.setCursor(
                Gdx.graphics.newCursor(
                        changeCursorTaskComponent._icon,
                        changeCursorTaskComponent.xHotspot,
                        changeCursorTaskComponent.yHotspot));
        var txtRendering = getEngine().getSystem(TextRenderingSystem.class);
        txtRendering.cursorWidth = changeCursorTaskComponent._icon.getWidth();
        txtRendering.cursorHeight = changeCursorTaskComponent._icon.getHeight();
    }
}
