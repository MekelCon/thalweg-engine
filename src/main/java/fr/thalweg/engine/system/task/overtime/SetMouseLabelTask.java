package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.flag.MouseLabelOwnerFlag;
import fr.thalweg.engine.component.task.SetMouseLabelTaskComponent;
import fr.thalweg.engine.system.rendering.TextRenderingSystem;

public class SetMouseLabelTask extends OverTimeTask {

    private static final Class<SetMouseLabelTaskComponent> COMPONENT = SetMouseLabelTaskComponent.class;
    private final ComponentMapper<SetMouseLabelTaskComponent> cm;
    private final ComponentMapper<MouseLabelOwnerFlag> mm;
    private TextRenderingSystem txtRendering;

    public SetMouseLabelTask() {
        super(COMPONENT);
        this.cm = ComponentMapper.getFor(COMPONENT);
        this.mm = ComponentMapper.getFor(MouseLabelOwnerFlag.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        txtRendering = getEngine().getSystem(TextRenderingSystem.class);
    }

    @Override
    protected void begin(Entity entity) {
        // Remove the current owner of the mouseLabel if exist
        var owners = getEngine().getEntitiesFor(Family.all(MouseLabelOwnerFlag.class).get());
        if (owners.size() != 0) {
            owners.first().remove(MouseLabelOwnerFlag.class);
        }
        // Entity is now the owner of the mouse label
        entity.add(getEngine().createComponent(MouseLabelOwnerFlag.class));
    }

    @Override
    protected void start(Entity entity) {
        if (mm.has(entity)) {
            var setMouseLabelTaskComponent = cm.get(entity);
            txtRendering.mouseLabel.restart(setMouseLabelTaskComponent.data.label);
        } // else it means another task take the mouse label ownership while we were waiting
    }

    @Override
    protected void update(Entity entity, float percent) {
        var setMouseLabelTaskComponent = cm.get(entity);
        // block ending until the typing display finishes
        setMouseLabelTaskComponent._complete = txtRendering.mouseLabel.hasEnded();
    }
}
