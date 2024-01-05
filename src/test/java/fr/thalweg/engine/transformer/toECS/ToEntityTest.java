package fr.thalweg.engine.transformer.toECS;

import fr.thalweg.engine.component.PolygonComponent;
import fr.thalweg.engine.component.ZIndexComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;
import fr.thalweg.engine.entity.ActorEntity;
import fr.thalweg.engine.gen.Position;
import fr.thalweg.engine.gen.Scale;
import fr.thalweg.engine.gen.ThalwegActorSchema;
import fr.thalweg.engine.gen.ThalwegTriggerSchema;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToEntityTest {

    @Test
    void nullNotHandled() {
        assertThrows(
                NullPointerException.class,
                () -> ToEntity.from(null, null));
    }

    @Test
    void fromVertices() {
        ActorEntity e = ToEntity.from(
                null,
                new ThalwegActorSchema()
                        .withVertices(List.of(1f, 1f, 1f, 2f, 2f, 2f))
                        .withPosition(new Position()
                                .withX(1f)
                                .withY(1f))
                        .withScale(new Scale()
                                .withX(2.5f)
                                .withY(0)));
        assertEquals(1f, e.getComponent(PolygonComponent.class).polygon.getX());
        assertEquals(1f, e.getComponent(PolygonComponent.class).polygon.getY());
        assertEquals(2.5f, e.getComponent(PolygonComponent.class).polygon.getScaleX());
        assertEquals(0, e.getComponent(PolygonComponent.class).polygon.getScaleY());
        assertNotNull(e.getComponent(ZIndexComponent.class));
    }

    @Test
    void withTrigger() {
        ActorEntity e = ToEntity.from(
                null,
                new ThalwegActorSchema()
                        .withTriggers(List.of(
                                new ThalwegTriggerSchema()
                                        .withType(ThalwegTriggerSchema.Type.MOUSE)
                        )));
        assertNotNull(e.getComponent(MouseTriggerComponent.class));
    }

    @Test
    void havingPositionMeanHavingZIndex() {
        ActorEntity e = assertDoesNotThrow(
                () -> ToEntity.from(
                        null,
                        new ThalwegActorSchema()
                                .withPosition(new Position()
                                        .withX(1f)
                                        .withY(1f))));
        assertNotNull(e.getComponent(ZIndexComponent.class));
    }
}