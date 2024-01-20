package fr.thalweg.engine.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import fr.thalweg.engine.utils.BasicThalwegGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssetTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new BasicThalwegGame());
    }

    @Test
    public void ok() {
        assertDoesNotThrow(() -> Asset.of(
                BasicThalwegGame.ROOT_DIRECTORY,
                AssetType.screen(),
                "screen/1.json"));
    }

    @Test
    public void notExistShouldPass() {
        assertDoesNotThrow(() -> Asset.of(
                BasicThalwegGame.ROOT_DIRECTORY,
                AssetType.screen(),
                "screen/not-exist.yaml"));
    }

    @Test
    public void notStartWith() {
        assertThrows(IllegalArgumentException.class,
                () -> Asset.of(
                        BasicThalwegGame.ROOT_DIRECTORY,
                        AssetType.screen(),
                        "toto/hello.png"));
    }

    @Test
    public void refuseNull() {
        assertThrows(IllegalArgumentException.class,
                () -> Asset.of(
                        null,
                        AssetType.screen(),
                        "toto/hello.png"));
        assertThrows(IllegalArgumentException.class,
                () -> Asset.of(
                        BasicThalwegGame.ROOT_DIRECTORY,
                        null,
                        "toto/hello.png"));
        assertThrows(IllegalArgumentException.class,
                () -> Asset.of(
                        BasicThalwegGame.ROOT_DIRECTORY,
                        AssetType.screen(),
                        null));
    }

    @Test
    public void refuseEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> Asset.of(
                        BasicThalwegGame.ROOT_DIRECTORY,
                        AssetType.screen(),
                        ""));
    }

    @Test
    public void getFileHandle() {
        Asset asset = Asset.of(
                BasicThalwegGame.ROOT_DIRECTORY,
                AssetType.screen(),
                "screen/1.json");
        assertEquals(Gdx.files.internal(
                        BasicThalwegGame.ROOT + "/screen/1.json"),
                asset.getFileHandle());
    }
}