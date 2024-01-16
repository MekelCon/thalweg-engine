package fr.thalweg.engine.infra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ObjectMap;
import com.github.tommyettinger.textra.Font;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.gen.engine.model.FontConfigData;
import fr.thalweg.gen.engine.model.FontConfigsData;

public class FontManager {

    public static final String DEFAULT = "default";

    private final Directory root;

    ObjectMap<String, Font> fontsByName;

    public FontManager(Directory root) {
        this.root = root;
        this.fontsByName = loadFonts();
    }

    private ObjectMap<String, Font> loadFonts() {
        var fontConfigsData = Reader.getInstance().read(Gdx.files.internal(root.getSubFolder("font/font-config.yaml")), FontConfigsData.class);
        if (fontConfigsData.getConfigs() != null) {
            var result = new ObjectMap<String, Font>(fontConfigsData.getConfigs().size());
            for (FontConfigData fontConfigData : fontConfigsData.getConfigs()) {
                FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(root.getSubFolder(fontConfigData.getSource())));
                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.size = (int) (fontConfigData.getSize() * Gdx.graphics.getDensity());
                result.put(fontConfigData.getName(), new Font(
                        generator.generateFont(parameter)));
                generator.dispose();
            }
            if (!result.containsKey(DEFAULT)) {
                result.put(DEFAULT, loadDefaultFont());
            }
            return result;
        } else {
            var result = new ObjectMap<String, Font>(1);
            result.put(DEFAULT, loadDefaultFont());
            return result;
        }
    }

    private Font loadDefaultFont() {
        Gdx.app.log(FontManager.class.getSimpleName(), "No default font defined, using fallback");
        return new Font();
    }

    public Font getFont(String name) {
        return this.fontsByName.get(name);
    }
}
