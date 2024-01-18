package fr.thalweg.engine.system.task.oneshot;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.SetMouseLabelTaskComponent;
import fr.thalweg.engine.system.rendering.TextRenderingSystem;

public class SetMouseLabelTask extends OneShotTask {

    private static final Class<SetMouseLabelTaskComponent> COMPONENT = SetMouseLabelTaskComponent.class;
    private static final Family FAMILY = Family.all(COMPONENT, WorkingFlag.class).get();
    private final ComponentMapper<SetMouseLabelTaskComponent> cm;

    public SetMouseLabelTask() {
        super(FAMILY);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void work(Entity entity) {
        var setMouseLabelTaskComponent = cm.get(entity);
        var txtRendering = getEngine().getSystem(TextRenderingSystem.class);
        txtRendering.mouseLabel.restart(setMouseLabelTaskComponent.data.getLabel());
    }
}