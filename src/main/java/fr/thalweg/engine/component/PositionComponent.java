package fr.thalweg.engine.component;


import com.badlogic.ashley.core.Component;
import lombok.Builder;


@Builder
public class PositionComponent implements Component {
    public int x;
    public int y;
}
