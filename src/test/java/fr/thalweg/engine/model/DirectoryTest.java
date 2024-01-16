package fr.thalweg.engine.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryTest {

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