package fr.thalweg.engine.infra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.utils.GdxRuntimeException;
import fr.thalweg.engine.infra.data.ThalwegGameConfigurationData;
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
        ThalwegGameConfigurationData configYaml = Reader.getInstance().read(
                Gdx.files.classpath("json-yaml/configuration.yaml"),
                ThalwegGameConfigurationData.class);
        assertNotNull(configYaml);
    }

    @Test
    public void canReadJsonConfiguration() {
        ThalwegGameConfigurationData configYaml = Reader.getInstance().read(
                Gdx.files.classpath("json-yaml/configuration.json"),
                ThalwegGameConfigurationData.class);
        assertNotNull(configYaml);
    }

    @Test
    public void cantReadUnknownConfiguration() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Reader.getInstance().read(
                        Gdx.files.classpath("json-yaml/configuration.unknown"),
                        ThalwegGameConfigurationData.class));
    }

    @Test
    public void fileNotExistNotHandled() {
        assertThrows(
                GdxRuntimeException.class,
                () -> Reader.getInstance().read(
                        Gdx.files.classpath("json-yaml/not-exist.json"),
                        ThalwegGameConfigurationData.class));
    }

    @Test
    public void readJsonEquivalentToReadYaml() {
        ThalwegGameConfigurationData configYaml = Reader.getInstance().read(
                Gdx.files.classpath("json-yaml/configuration.yaml"),
                ThalwegGameConfigurationData.class);
        ThalwegGameConfigurationData configJson = Reader.getInstance().read(
                Gdx.files.classpath("json-yaml/configuration.json"),
                ThalwegGameConfigurationData.class);
        assertEquals(configYaml, configJson);
        configYaml.getLwjgl3ApplicationConfig().setTitle("changed");
        assertNotEquals(configYaml, configJson);
    }


}