package fr.thalweg.engine.entity;

import com.badlogic.ashley.core.Engine;
import fr.thalweg.engine.component.ZIndexComponent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntityComparatorTest {

    static EntityComparator entityComparator = new EntityComparator();
    static ActorEntity e1;
    static ActorEntity e2;
    static ActorEntity e3;

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
        assertTrue(entityComparator.compare(e1, e2) > 0);
        assertTrue(entityComparator.compare(e2, e1) < 0);
        assertEquals(0, entityComparator.compare(e1, e3));
    }
}