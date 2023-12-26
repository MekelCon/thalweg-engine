package fr.thalweg.engine.model;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import fr.thalweg.engine.utils.BasicThalwegGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new BasicThalwegGame());
    }

    @Test
    public void refuse() {
        assertThrows(IllegalArgumentException.class, () -> Directory.of(null));
        assertThrows(IllegalArgumentException.class, () -> Directory.of(""));
    }

    @Test
    public void removeTrailingSlash() {
        Directory directory = Directory.of("hello/");
        assertNotNull(directory);
        assertEquals("hello", directory.get());
    }

}