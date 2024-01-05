package fr.thalweg.engine.component;

import com.badlogic.gdx.math.Polygon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolygonComponentTest {

    @Test
    void refuseNull() {
        assertThrows(
                NullPointerException.class,
                () -> PolygonComponent.builder().build());
    }

    @Test
    void builder() {
        PolygonComponent polygonComponent = assertDoesNotThrow(
                () -> PolygonComponent.builder()
                        .polygon(new Polygon(new float[]{1f, 2f, 3f, 4f, 5f, 6f}))
                        .build());
        assertEquals(1, polygonComponent.polygon.getVertices()[0]);
        assertEquals(2, polygonComponent.polygon.getVertices()[1]);
        assertEquals(3, polygonComponent.polygon.getVertices()[2]);
        assertEquals(4, polygonComponent.polygon.getVertices()[3]);
        assertEquals(5, polygonComponent.polygon.getVertices()[4]);
        assertEquals(6, polygonComponent.polygon.getVertices()[5]);
    }
}