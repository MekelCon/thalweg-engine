package fr.thalweg.engine.system.rendering;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DebugInfoRenderingSystem extends EntitySystem {
    private final GLProfiler profiler;
    private final Stage stage;
    private final Label gameInfo;
    private final Label mouseInfo;
    private final Label glProfileInfo;

    private final Label memInfo;
    private Viewport worldViewPort;


    public DebugInfoRenderingSystem(Viewport viewport) {
        super(3);
        this.stage = new Stage(viewport);

        this.profiler = new GLProfiler(Gdx.graphics);
        profiler.enable();

        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont font = new BitmapFont();
        font.getData().scale(0.5f);
        label1Style.font = font;
        label1Style.fontColor = Color.GRAY;
        this.gameInfo = new Label("", label1Style);
        this.mouseInfo = new Label("", label1Style);
        this.glProfileInfo = new Label("", label1Style);
        this.memInfo = new Label("", label1Style);


        VerticalGroup labelGroup = new VerticalGroup();
        labelGroup.columnAlign(Align.left);
        labelGroup.setFillParent(true);
        labelGroup.columnLeft();
        labelGroup.addActor(gameInfo);
        labelGroup.addActor(mouseInfo);
        labelGroup.addActor(glProfileInfo);
        labelGroup.addActor(memInfo);
        stage.addActor(labelGroup);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.worldViewPort = engine.getSystem(WorldRenderingSystem.class).viewport;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        gameInfo.setText("FPS : " + Gdx.graphics.getFramesPerSecond());

        Vector2 mouseWorldXY = worldViewPort.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        mouseInfo.setText(
                "Mouse : world (" + (int) mouseWorldXY.x + "," + (int) mouseWorldXY.y + ")"
                        + " screen (" + Gdx.input.getX() + "," + Gdx.input.getY() + ")");

        glProfileInfo.setText(
                "Texture binding : " + profiler.getTextureBindings()
                        + "\nDraw calls :" + profiler.getDrawCalls());
        profiler.reset();

        memInfo.setText(
                "Heap : " + Gdx.app.getJavaHeap()
                        + "\nNat. Heap :" + Gdx.app.getNativeHeap());

        stage.act(deltaTime);
        stage.draw();
    }
}