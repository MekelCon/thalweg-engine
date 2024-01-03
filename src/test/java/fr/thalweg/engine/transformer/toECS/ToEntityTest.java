package fr.thalweg.engine.transformer.toECS;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import fr.thalweg.engine.component.TransformComponent;
import fr.thalweg.engine.entity.ActorEntity;
import fr.thalweg.engine.gen.Position;
import fr.thalweg.engine.gen.Scale;
import fr.thalweg.engine.gen.ThalwegActorSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ToEntityTest {

    Engine ecsEngine = new Engine();

    @Test
    void nullNotHandled() {
        assertThrows(
                NullPointerException.class,
                () -> ToEntity.from(null, null));
    }

    @Test
    void useInput() {
        ActorEntity e = ToEntity.from(
                null,
                new ThalwegActorSchema()
                        .withPosition(new Position()
                                .withX(1f)
                                .withY(1f)
                                .withZ(1f))
                        .withScale(new Scale()
                                .withX(2.5f)
                                .withY(0)));
        ecsEngine.addEntity(e);
        assertEquals(new Vector3(1f, 1f, 1f), e.getComponent(TransformComponent.class).pos);
        assertEquals(new Vector2(2.5f, 0f), e.getComponent(TransformComponent.class).scale);
    }

}