package fr.thalweg.engine.validator;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import fr.thalweg.engine.utils.JsonYamlThalwegGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectStructureValidatorJsonYamlTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new JsonYamlThalwegGame());
    }

    @Test
    void throwOnInvalid() {
        assertThrows(
                InvalidThalwegGameStructureException.class,
                ProjectStructureValidator::validThalwegGameStructure);
    }
}