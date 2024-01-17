package fr.thalweg.engine.system.task.onetime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.SetMouseLabelTaskComponent;
import fr.thalweg.engine.system.MouseLabelSystem;

public class SetMouseLabelTask extends OneShotTask {

    private static final Class<SetMouseLabelTaskComponent> COMPONENT = SetMouseLabelTaskComponent.class;
    private static final Family FAMILY = Family.all(COMPONENT, WorkingFlag.class).get();
    private final ComponentMapper<SetMouseLabelTaskComponent> cm;
    String pattern = "\\[@.*]";

    public SetMouseLabelTask() {
        super(FAMILY);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void work(Entity entity) {
        var setMouseLabelTaskComponent = cm.get(entity);
        var mouseLabelSystem = getEngine().getSystem(MouseLabelSystem.class);
        if (setMouseLabelTaskComponent.data.getFontName() != null) {
            mouseLabelSystem.mouseLabel.setDefaultToken(
                    mouseLabelSystem.mouseLabel.getDefaultToken().replaceFirst(
                            pattern,
                            "[@" + setMouseLabelTaskComponent.data.getFontName() + "]"));
        }

        mouseLabelSystem.mouseLabel.restart(setMouseLabelTaskComponent.data.getLabel());
    }
}
