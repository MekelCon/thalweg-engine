package fr.thalweg.engine.system.rendering;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.component.RenderMouseLabelComponent;
import fr.thalweg.engine.infra.FontManager;
import fr.thalweg.engine.model.Directory;


public class TextRenderingSystem extends IteratingSystem {

    private final FontManager fontManager;
    private final Stage textStage;

    private final ComponentMapper<RenderMouseLabelComponent> rm;

    private final Label mouseLabel;

    public TextRenderingSystem(Directory root, Viewport viewport) {
        // Render text after rendering world
        super(Family.all(RenderMouseLabelComponent.class).get(), 2);
        this.fontManager = new FontManager(root);
        var label1Style = new Label.LabelStyle();
        label1Style.font = fontManager.getFont(FontManager.DEFAULT);
        label1Style.fontColor = Color.LIGHT_GRAY;
        this.mouseLabel = new Label("", label1Style);
        this.textStage = new Stage(viewport);
        this.textStage.addActor(mouseLabel);
        this.rm = ComponentMapper.getFor(RenderMouseLabelComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        textStage.act(deltaTime);
        Vector2 worldXY = textStage.getViewport().unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        mouseLabel.setPosition(worldXY.x, worldXY.y);
        textStage.getViewport().apply();
        textStage.draw();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var setMouseLabelTaskComponent = rm.get(entity);
        mouseLabel.setText(setMouseLabelTaskComponent.label);
        if (setMouseLabelTaskComponent.fontName != null) {
            Label.LabelStyle currentStyle = mouseLabel.getStyle();
            currentStyle.font = fontManager.getFont(setMouseLabelTaskComponent.fontName);
            mouseLabel.setStyle(currentStyle);
        }
        entity.removeAll();
        getEngine().removeEntity(entity);
    }
}