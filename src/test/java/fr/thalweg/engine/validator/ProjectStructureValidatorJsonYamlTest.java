package fr.thalweg.engine.validator;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import fr.thalweg.engine.utils.JsonYamlThalwegEngineGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectStructureValidatorJsonYamlTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new JsonYamlThalwegEngineGame());
    }

    @Test
    void throwOnInvalid() {
        assertThrows(
                InvalidThalwegEngineGameStructureException.class,
                ProjectStructureValidator::validThalwegEngineGameStructure);
    }
}