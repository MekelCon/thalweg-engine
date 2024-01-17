package fr.thalweg.engine.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.github.tommyettinger.textra.TypingLabel;
import fr.thalweg.engine.infra.FontManager;
import fr.thalweg.engine.model.Directory;


public class MouseLabelSystem extends EntitySystem {

    public final TypingLabel mouseLabel;
    public final FontManager fontManager;

    public MouseLabelSystem(Directory root) {
        this.fontManager = new FontManager(root);
        this.mouseLabel = new TypingLabel("", fontManager.getFont(FontManager.DEFAULT));
        this.mouseLabel.setDefaultToken("{FAST}[BLACKEN][#CCCCCC]");
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 worldXY = mouseLabel.getStage().getViewport()
                .unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        mouseLabel.setPosition(worldXY.x, worldXY.y);
    }
}
