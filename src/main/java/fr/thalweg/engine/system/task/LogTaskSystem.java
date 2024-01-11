package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.component.task.LogTaskComponent;

public class LogTaskSystem extends IteratingSystem {

    private final ComponentMapper<LogTaskComponent> lm;

    public LogTaskSystem() {
        super(Family.all(LogTaskComponent.class).get());
        this.lm = ComponentMapper.getFor(LogTaskComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        LogTaskComponent lc = lm.get(entity);
        Gdx.app.log("LogTask", lc.data.getMessage());
        entity.remove(LogTaskComponent.class);
    }
}
