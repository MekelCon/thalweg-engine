package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import lombok.Builder;

@Builder
public class TextComponent implements Component {
    public int x;
    public int y;
    public boolean onMouse;
    public String text;
}
