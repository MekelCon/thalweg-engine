package fr.thalweg.engine.system;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.math.Vector3;
import fr.thalweg.engine.infra.schema.World;
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
        World world = new World();
        world.setWidth(10);
        world.setHeight(10);
        CameraSystem cameraSystem = new CameraSystem(world);
        assertEquals(world.getWidth(), cameraSystem.getCamera().viewportWidth);
        assertEquals(world.getHeight(), cameraSystem.getCamera().viewportHeight);
    }

    @Test
    public void canMove() {
        World world = new World();
        world.setWidth(10);
        world.setHeight(10);
        CameraSystem cameraSystem = new CameraSystem(world);
        Vector3 beforeMove = cameraSystem.getCamera().position.cpy();
        cameraSystem.getCamera().translate(10, 0);
        assertEquals(beforeMove.x + 10, cameraSystem.getCamera().position.x);
        assertEquals(beforeMove.y, cameraSystem.getCamera().position.y);
    }

}