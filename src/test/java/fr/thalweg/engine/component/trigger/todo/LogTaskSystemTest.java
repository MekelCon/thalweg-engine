package fr.thalweg.engine.component.trigger.todo;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import fr.thalweg.engine.system.task.LogTaskSystem;
import fr.thalweg.engine.utils.BasicThalwegGame;
import fr.thalweg.gen.engine.model.LogTaskData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LogTaskSystemTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new BasicThalwegGame());
    }

    @Test
    public void working() {
        LogTaskSystem logTaskSystem = LogTaskSystem.builder()
                .data(new LogTaskData()
                        .message("Hello"))
                .build();
        assertTrue(logTaskSystem.work(0));
    }

}