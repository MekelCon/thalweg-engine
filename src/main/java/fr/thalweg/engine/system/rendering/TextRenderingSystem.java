package fr.thalweg.engine.system.rendering;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.component.TextComponent;

public class TextRenderingSystem extends IteratingSystem {

    private final PooledEngine ecsEngine;

    private final Stage textStage;
    private final ComponentMapper<TextComponent> textComponentMapper;
    private final Label mouseLabel;

    public TextRenderingSystem(PooledEngine ecsEngine, Viewport viewport) {
        super(Family.all(TextComponent.class).get());
        this.ecsEngine = ecsEngine;

        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont();
        label1Style.font = myFont;
        label1Style.fontColor = Color.LIGHT_GRAY;

        this.mouseLabel = new Label("", label1Style);
        this.textStage = new Stage(viewport);
        textStage.addActor(mouseLabel);
        this.textComponentMapper = ComponentMapper.getFor(TextComponent.class);
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
        TextComponent textComponent = textComponentMapper.get(entity);
        if (textComponent.onMouse) {
            mouseLabel.setText(textComponent.text);
        }
        ecsEngine.removeEntity(entity);
    }
}