package fr.thalweg.engine.system;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.math.Vector3;
import fr.thalweg.engine.infra.data.WorldData;
import fr.thalweg.engine.utils.BasicThalwegGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CameraSystemTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new BasicThalwegGame());
    }

    @Test
    public void lookAtEntireWorld() {
        WorldData worldData = new WorldData()
                .width(10)
                .height(10);
        CameraSystem cameraSystem = new CameraSystem(worldData);
        assertEquals(worldData.getWidth().floatValue(), cameraSystem.getCamera().viewportWidth);
        assertEquals(worldData.getHeight().floatValue(), cameraSystem.getCamera().viewportHeight);
    }

    @Test
    public void canMove() {
        WorldData worldSchema = new WorldData()
                .width(10)
                .height(10);
        CameraSystem cameraSystem = new CameraSystem(worldSchema);
        Vector3 beforeMove = cameraSystem.getCamera().position.cpy();
        cameraSystem.getCamera().translate(10, 0);
        assertEquals(beforeMove.x + 10, cameraSystem.getCamera().position.x);
        assertEquals(beforeMove.y, cameraSystem.getCamera().position.y);
    }

}