package fr.thalweg.engine.component.trigger.todo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Todo {
    public abstract boolean doing(float deltaTime);
}
