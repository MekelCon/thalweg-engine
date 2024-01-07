package fr.thalweg.engine.component.trigger.todo;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import fr.thalweg.engine.system.task.LogTask;
import fr.thalweg.engine.utils.BasicThalwegGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LogTaskTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new BasicThalwegGame());
    }

    @Test
    public void working() {
        LogTask logTask = LogTask.builder()
                .data(LogTaskSchema.builder()
                        .message("Hello")
                        .build())
                .build();
        assertTrue(logTask.work(0));
    }

}