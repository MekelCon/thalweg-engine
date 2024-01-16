package fr.thalweg.engine.transformer.toECS;

import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.PolygonComponent;
import fr.thalweg.engine.component.ZIndexComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;
import fr.thalweg.gen.engine.model.*;
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
        Entity e = ToEntity.from(
                null,
                new ThalwegActorData()
                        .vertices(List.of(1f, 1f, 1f, 2f, 2f, 2f))
                        .position(new PositionData()
                                .x(1f)
                                .y(1f))
                        .scale(new ScaleData()
                                .x(2.5f)
                                .y(0f)));
        assertEquals(1f, e.getComponent(PolygonComponent.class).polygon.getX());
        assertEquals(1f, e.getComponent(PolygonComponent.class).polygon.getY());
        assertEquals(2.5f, e.getComponent(PolygonComponent.class).polygon.getScaleX());
        assertEquals(0, e.getComponent(PolygonComponent.class).polygon.getScaleY());
        assertNotNull(e.getComponent(ZIndexComponent.class));
    }

    @Test
    void withEmptyTrigger() {
        Entity e = ToEntity.from(
                null,
                new ThalwegActorData()
                        .triggers(List.of(new TriggerData()
                                .type(TriggerTypeEnumData.MOUSEENTER))));
        assertNull(e.getComponent(MouseTriggerComponent.class));
    }

    @Test
    void havingPositionMeanHavingZIndex() {
        Entity e = assertDoesNotThrow(
                () -> ToEntity.from(
                        null,
                        new ThalwegActorData()
                                .position(new PositionData()
                                        .x(1f)
                                        .y(1f))));
        assertNotNull(e.getComponent(ZIndexComponent.class));
    }
}