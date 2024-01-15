package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import lombok.Builder;

@Builder
public class RenderMouseLabelComponent implements Component {
    public String label;
}
