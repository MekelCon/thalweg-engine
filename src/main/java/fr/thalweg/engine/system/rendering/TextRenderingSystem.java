package fr.thalweg.engine.system.rendering;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.textra.TypingLabel;
import fr.thalweg.engine.infra.FontManager;
import fr.thalweg.engine.model.Directory;

public class TextRenderingSystem extends EntitySystem {

    public static final String INITIAL_LABEL_TOKEN = "{FAST}[BLACKEN][#CCCCCC][@]";
    public final TypingLabel mouseLabel;
    public final FontManager fontManager;
    private final Stage textStage;

    public TextRenderingSystem(Directory root, Viewport viewport) {
        // Render text over rendering world
        super(2);
        this.fontManager = new FontManager(root);
        this.mouseLabel = new TypingLabel("", fontManager.font);
        this.mouseLabel.setDefaultToken(INITIAL_LABEL_TOKEN);
        this.textStage = new Stage(viewport);
        this.textStage.addActor(mouseLabel);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        textStage.getViewport().apply();
        textStage.act(deltaTime);
        Vector2 worldXY = mouseLabel.getStage().getViewport()
                .unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        mouseLabel.setPosition(worldXY.x, worldXY.y);
        textStage.draw();
    }
}