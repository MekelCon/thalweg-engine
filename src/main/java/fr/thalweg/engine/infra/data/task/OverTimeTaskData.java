package fr.thalweg.engine.infra.data.task;

import fr.thalweg.engine.infra.data.InterpolationData;

public abstract class OverTimeTaskData extends TaskData {

    public float duration = 0f;
    public InterpolationData interpolation = InterpolationData.LINEAR;
    public float delay = 0f;
}

