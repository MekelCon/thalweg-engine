package fr.thalweg.engine.system.task.onetime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.SetMouseLabelTaskComponent;
import fr.thalweg.engine.system.rendering.TextRenderingSystem;

import java.util.regex.Pattern;

public class SetMouseLabelTask extends OneShotTask {

    private static final Class<SetMouseLabelTaskComponent> COMPONENT = SetMouseLabelTaskComponent.class;
    private static final Family FAMILY = Family.all(COMPONENT, WorkingFlag.class).get();
    private static final Pattern FONT_TOKEN = Pattern.compile("\\[@.*]");
    private final ComponentMapper<SetMouseLabelTaskComponent> cm;

    public SetMouseLabelTask() {
        super(FAMILY);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void work(Entity entity) {
        var setMouseLabelTaskComponent = cm.get(entity);
        var txtRendering = getEngine().getSystem(TextRenderingSystem.class);
        if (setMouseLabelTaskComponent.data.getFontName() != null) {
            txtRendering.mouseLabel.setDefaultToken(FONT_TOKEN.matcher(txtRendering.mouseLabel.getDefaultToken()).replaceFirst("[@" + setMouseLabelTaskComponent.data.getFontName() + "]"));
        }
        txtRendering.mouseLabel.restart(setMouseLabelTaskComponent.data.getLabel());
    }
}
