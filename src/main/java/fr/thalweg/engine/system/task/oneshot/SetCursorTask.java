package fr.thalweg.engine.system.task.oneshot;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.SetCursorTaskComponent;
import fr.thalweg.engine.system.rendering.TextRenderingSystem;

public class SetCursorTask extends OneShotTask {

    private static final Class<SetCursorTaskComponent> COMPONENT = SetCursorTaskComponent.class;
    private static final Family FAMILY = Family.all(COMPONENT, WorkingFlag.class).get();
    private final ComponentMapper<SetCursorTaskComponent> cm;

    public SetCursorTask() {
        super(FAMILY);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void work(Entity entity) {
        var changeCursorTaskComponent = cm.get(entity);
        Gdx.graphics.setCursor(
                Gdx.graphics.newCursor(
                        changeCursorTaskComponent.icon,
                        changeCursorTaskComponent.data.getxHotspot(),
                        changeCursorTaskComponent.data.getyHotspot()));
        var txtRendering = getEngine().getSystem(TextRenderingSystem.class);
        txtRendering.cursorWidth = changeCursorTaskComponent.icon.getWidth();
        txtRendering.cursorHeight = changeCursorTaskComponent.icon.getHeight();
    }
}
