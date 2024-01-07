package fr.thalweg.engine.transformer.toECS;

import fr.thalweg.engine.component.PolygonComponent;
import fr.thalweg.engine.component.ZIndexComponent;
import fr.thalweg.engine.component.trigger.MouseTriggerComponent;
import fr.thalweg.engine.entity.ActorEntity;
import fr.thalweg.engine.infra.schema.PositionSchema;
import fr.thalweg.engine.infra.schema.ScaleSchema;
import fr.thalweg.engine.infra.schema.trigger.TriggerSchema;
import fr.thalweg.engine.infra.schema.trigger.TriggerTypeEnum;
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
                ThalwegActorSchema.builder()
                        .vertices(List.of(1f, 1f, 1f, 2f, 2f, 2f))
                        .positionSchema(PositionSchema.builder()
                                .x(1f)
                                .y(1f)
                                .build())
                        .scaleSchema(ScaleSchema.builder()
                                .x(2.5f)
                                .y(0)
                                .build())
                        .build());
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
                ThalwegActorSchema.builder()
                        .triggers(List.of(TriggerSchema.builder()
                                .type(TriggerTypeEnum.MOUSE_ENTER)
                                .build()
                        ))
                        .build());
        assertNotNull(e.getComponent(MouseTriggerComponent.class));
    }

    @Test
    void havingPositionMeanHavingZIndex() {
        ActorEntity e = assertDoesNotThrow(
                () -> ToEntity.from(
                        null,
                        ThalwegActorSchema.builder()
                                .positionSchema(PositionSchema.builder()
                                        .x(1f)
                                        .y(1f)
                                        .build())
                                .build()));
        assertNotNull(e.getComponent(ZIndexComponent.class));
    }
}