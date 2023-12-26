package fr.thalweg.engine.infra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.utils.GdxRuntimeException;
import fr.thalweg.engine.gen.GameConfigurationSchema;
import fr.thalweg.engine.utils.JsonYamlThalwegGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new JsonYamlThalwegGame());
    }

    @Test
    public void canReadYamlConfiguration() {
        GameConfigurationSchema configYaml = Reader.getInstance().read(
                Gdx.files.classpath("json-yaml/configuration.yaml"),
                GameConfigurationSchema.class);
        assertNotNull(configYaml);
    }

    @Test
    public void canReadJsonConfiguration() {
        GameConfigurationSchema configYaml = Reader.getInstance().read(
                Gdx.files.classpath("json-yaml/configuration.json"),
                GameConfigurationSchema.class);
        assertNotNull(configYaml);
    }

    @Test
    public void cantReadUnknownConfiguration() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Reader.getInstance().read(
                        Gdx.files.classpath("json-yaml/configuration.unknown"),
                        GameConfigurationSchema.class));
    }

    @Test
    public void fileNotExistNotHandled() {
        assertThrows(
                GdxRuntimeException.class,
                () -> Reader.getInstance().read(
                        Gdx.files.classpath("json-yaml/not-exist.json"),
                        GameConfigurationSchema.class));
    }

    @Test
    public void readJsonEquivalentToReadYaml() {
        GameConfigurationSchema configYaml = Reader.getInstance().read(
                Gdx.files.classpath("json-yaml/configuration.yaml"),
                GameConfigurationSchema.class);
        GameConfigurationSchema configJson = Reader.getInstance().read(
                Gdx.files.classpath("json-yaml/configuration.json"),
                GameConfigurationSchema.class);
        assertEquals(configYaml, configJson);
        configYaml.getLwjgl3ApplicationConfiguration().setTitle("changed");
        assertNotEquals(configYaml, configJson);
    }


}