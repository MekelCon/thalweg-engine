package fr.thalweg.engine.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.ZIndexComponent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntityZIndexComparatorTest {

    static EntityZIndexComparator entityZIndexComparator = new EntityZIndexComparator();
    static Entity e1;
    static Entity e2;
    static Entity e3;

    @BeforeAll
    public static void initECS() {
        Engine ecsEngine = new Engine();
        e1 = new ActorEntity();
        e1.add(
                ZIndexComponent.builder()
                        .build()
        );
        e2 = new ActorEntity();
        e2.add(
                ZIndexComponent.builder()
                        .zIndex(1)
                        .build()
        );
        e3 = new ActorEntity();
        e3.add(
                ZIndexComponent.builder()
                        .zIndex(0)
                        .build()
        );

        ecsEngine.addEntity(e1);
        ecsEngine.addEntity(e2);
        ecsEngine.addEntity(e3);
    }

    @Test
    public void rightOrder() {
        assertTrue(entityZIndexComparator.compare(e1, e2) > 0);
        assertTrue(entityZIndexComparator.compare(e2, e1) < 0);
        assertEquals(0, entityZIndexComparator.compare(e1, e3));
    }
}