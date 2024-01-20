package fr.thalweg.engine.infra.data.task;

import com.badlogic.gdx.utils.Array;

public abstract class TaskArrayData extends TaskData {
    public Array<TaskData> todos = new Array<>();
}

