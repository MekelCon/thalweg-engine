package fr.thalweg.engine.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.flag.WorkingFlag;

public class WorkingFlagListener implements EntityListener {

    public static Family LISTENING = Family.all(WorkingFlag.class).get();

    public WorkingFlagListener() {
    }

    @Override
    public void entityAdded(Entity entity) {

    }

    @Override
    public void entityRemoved(Entity entity) {

    }
}
