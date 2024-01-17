package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.LogTaskComponent;

public class LogTask extends OneShotTask {

    private static final Class<LogTaskComponent> COMPONENT = LogTaskComponent.class;
    private static final Family FAMILY = Family.all(COMPONENT, WorkingFlag.class).get();
    private final ComponentMapper<LogTaskComponent> cm;

    public LogTask() {
        super(FAMILY);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void work(Entity entity) {
        var logTaskDataComponent = cm.get(entity);
        Gdx.app.log("LOG TASK", logTaskDataComponent.data.getMessage());
    }
}
