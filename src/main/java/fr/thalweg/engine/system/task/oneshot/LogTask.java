package fr.thalweg.engine.system.task.oneshot;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.component.task.LogTaskComp;

public class LogTask extends OneShotTask {
    private static final Class<LogTaskComp> COMPONENT = LogTaskComp.class;
    private final ComponentMapper<LogTaskComp> cm;

    public LogTask() {
        super(COMPONENT);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void work(Entity entity) {
        var logTaskDataComponent = cm.get(entity);
        Gdx.app.log("LOG TASK", logTaskDataComponent.message);
    }
}
