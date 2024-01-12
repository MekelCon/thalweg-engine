package fr.thalweg.engine.component.trigger;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class AutoTriggerComponent implements Component {
    @Builder.Default
    public Array<Component> todos = new Array<>();
}
