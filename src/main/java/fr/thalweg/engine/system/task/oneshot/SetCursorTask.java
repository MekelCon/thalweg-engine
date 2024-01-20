package fr.thalweg.engine.system.task.oneshot;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.component.task.SetCursorTaskComponent;
import fr.thalweg.engine.system.rendering.TextRenderingSystem;

public class SetCursorTask extends OneShotTask {

    private static final Class<SetCursorTaskComponent> COMPONENT = SetCursorTaskComponent.class;
    private final ComponentMapper<SetCursorTaskComponent> cm;

    public SetCursorTask() {
        super(COMPONENT);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void work(Entity entity) {
        var changeCursorTaskComponent = cm.get(entity);
        Gdx.graphics.setCursor(
                Gdx.graphics.newCursor(
                        changeCursorTaskComponent.icon,
                        changeCursorTaskComponent.data.xHotspot,
                        changeCursorTaskComponent.data.yHotspot));
        var txtRendering = getEngine().getSystem(TextRenderingSystem.class);
        txtRendering.cursorWidth = changeCursorTaskComponent.icon.getWidth();
        txtRendering.cursorHeight = changeCursorTaskComponent.icon.getHeight();
    }
}
