package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.ThalwegGame;
import fr.thalweg.engine.infra.Reader;

public class LoadTaskComp extends TaskComp {

    public String path;
    public TaskComp _todo;
    public Entity _executor;

    @Override
    public void reset() {
        super.reset();
        path = null;
    }

    @Override
    public void build() {
        super.build();
        _todo = ThalwegGame.INSTANCE.getEcsEngine().createTaskComponent(Reader.getInstance().read(
                Gdx.files.internal(ThalwegGame.INSTANCE.getRoot().getSubFolder(path)),
                TaskComp.class));
    }
}
