package fr.thalweg.engine.infra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ObjectMap;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.gen.engine.model.FontConfigData;
import fr.thalweg.gen.engine.model.FontConfigsData;

public class FontManager {

    private final Directory root;

    ObjectMap<String, BitmapFont> fontsByName;

    public FontManager(Directory root) {
        this.root = root;
        this.fontsByName = loadFonts();
    }

    private ObjectMap<String, BitmapFont> loadFonts() {
        var fontConfigsData = Reader.getInstance().read(Gdx.files.internal(root.getSubFolder("font/font-config.yaml")), FontConfigsData.class);
        if (fontConfigsData.getConfigs() != null) {
            var result = new ObjectMap<String, BitmapFont>(fontConfigsData.getConfigs().size());
            for (FontConfigData fontConfigData : fontConfigsData.getConfigs()) {
                FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(root.getSubFolder(fontConfigData.getSource())));
                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.size = (int) (fontConfigData.getSize() * Gdx.graphics.getDensity());
                parameter.borderWidth = fontConfigData.getBorderSize();
                result.put(fontConfigData.getName(), generator.generateFont(parameter));
                generator.dispose();
            }
            return result;
        } else {
            return new ObjectMap<>();
        }
    }

    public BitmapFont getFont(String name) {
        return this.fontsByName.get(name);
    }
}
