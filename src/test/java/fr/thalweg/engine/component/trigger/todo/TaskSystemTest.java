package fr.thalweg.engine.component.trigger.todo;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import fr.thalweg.engine.utils.BasicThalwegGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TaskSystemTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new BasicThalwegGame());
    }

    @Test
    public void working() {
        TaskSystem taskSystem = TaskSystem.builder()
                .data(new LogTaskData()
                        .message("Hello"))
                .build();
        assertTrue(taskSystem.work(0));
    }

}