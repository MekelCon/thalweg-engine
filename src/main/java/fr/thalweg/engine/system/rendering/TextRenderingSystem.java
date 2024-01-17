package fr.thalweg.engine.system.rendering;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.system.MouseLabelSystem;

public class TextRenderingSystem extends EntitySystem {

    private final Stage textStage;

    public TextRenderingSystem(Viewport viewport) {
        // Render text over rendering world
        super(2);
        this.textStage = new Stage(viewport);
    }

    @Override
    public void addedToEngine(Engine engine) {
        var mouseLabelSystem = engine.getSystem(MouseLabelSystem.class);
        textStage.addActor(mouseLabelSystem.mouseLabel);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        textStage.act(deltaTime);
        textStage.getViewport().apply();
        textStage.draw();
    }
}