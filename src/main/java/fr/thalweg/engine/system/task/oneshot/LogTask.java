package fr.thalweg.engine.system.task.oneshot;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.component.task.LogTaskComponent;

public class LogTask extends OneShotTask {
    private static final Class<LogTaskComponent> COMPONENT = LogTaskComponent.class;
    private final ComponentMapper<LogTaskComponent> cm;

    public LogTask() {
        super(COMPONENT);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void work(Entity entity) {
        var logTaskDataComponent = cm.get(entity);
        Gdx.app.log("LOG TASK", logTaskDataComponent.data.getMessage());
    }
}
