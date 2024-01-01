package fr.thalweg.engine.validator;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import fr.thalweg.engine.utils.BasicThalwegGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ProjectStructureValidatorBasicTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new BasicThalwegGame(), new HeadlessApplicationConfiguration());
    }

    @Test
    void throwOnInvalid() {
        assertDoesNotThrow(() -> ProjectStructureValidator.validThalwegGameStructure(BasicThalwegGame.ROOT_DIRECTORY));
    }
}