package fr.thalweg.engine.component.trigger.todo;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import fr.thalweg.engine.utils.BasicThalwegGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GDXAppLogTest {

    @BeforeAll
    public static
    void beforeAll() {
        new HeadlessApplication(new BasicThalwegGame());
    }

    @Test
    public void working() {
        GDXAppLog gdxAppLog = new GDXAppLog("Hello");
        assertTrue(gdxAppLog.doing(0));
    }

}