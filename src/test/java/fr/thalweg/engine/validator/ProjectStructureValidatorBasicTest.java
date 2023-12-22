package fr.thalweg.engine.validator;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import fr.thalweg.engine.utils.BasicThalwegEngineGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ProjectStructureValidatorBasicTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new BasicThalwegEngineGame());
    }

    @Test
    void throwOnInvalid() {
        assertDoesNotThrow(ProjectStructureValidator::validThalwegEngineGameStructure);
    }
}