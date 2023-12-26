package fr.thalweg.engine.model;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import fr.thalweg.engine.utils.BasicThalwegGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssetTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new BasicThalwegGame());
    }

    @Test
    public void ok() {
        assertDoesNotThrow(() -> Asset.of(AssetType.scene(), "scene/1.yaml"));
    }

    @Test
    public void notExistShouldPass() {
        assertDoesNotThrow(() -> Asset.of(AssetType.scene(), "scene/not-exist.yaml"));
    }

    @Test
    public void notStartWith() {
        assertThrows(IllegalArgumentException.class,
                () -> Asset.of(AssetType.scene(), "toto/hello.png"));
    }

    @Test
    public void refuseNull() {
        assertThrows(IllegalArgumentException.class,
                () -> Asset.of(null, "toto/hello.png"));
        assertThrows(IllegalArgumentException.class,
                () -> Asset.of(AssetType.scene(), null));
    }

    @Test
    public void refuseEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> Asset.of(AssetType.scene(), ""));
    }
}