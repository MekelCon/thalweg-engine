package fr.thalweg.engine.model;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import fr.thalweg.engine.utils.GdxTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new GdxTest());
    }

    @Test
    public void refuse() {
        assertThrows(IllegalArgumentException.class, () -> Directory.of(null));
        List<String> shouldNotBeAccepted = List.of("", "notExist");
        for (String notOK : shouldNotBeAccepted) {
            assertThrows(IllegalArgumentException.class, () -> Directory.of(notOK));
        }
    }

    @Test
    public void ok() {
        Directory directory = Directory.of("basic/configuration.yaml");
        assertNotNull(directory);
        assertEquals("basic/configuration.yaml", directory.get());
    }

}