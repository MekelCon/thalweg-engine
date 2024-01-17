package fr.thalweg.engine.infra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;
import com.github.tommyettinger.textra.Font;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.gen.engine.model.FontConfigData;
import fr.thalweg.gen.engine.model.FontConfigsData;

public class FontManager {

    public static final String DEFAULT = "default";
    public final Font font;
    private final Directory root;

    public FontManager(Directory root) {
        this.root = root;
        this.font = loadFontFamily();
    }

    private Font loadFontFamily() {
        var fontConfigsData = Reader.getInstance().read(Gdx.files.internal(root.getSubFolder("font/font-config.yaml")), FontConfigsData.class);
        var family = new Array<Font>();
        if (fontConfigsData.getConfigs() != null) {
            for (FontConfigData fontConfigData : fontConfigsData.getConfigs()) {
                FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(root.getSubFolder(fontConfigData.getSource())));
                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.size = (int) (fontConfigData.getSize() * Gdx.graphics.getDensity());
                var fontToAdd = new Font(generator.generateFont(parameter));
                fontToAdd.setName(fontConfigData.getName());
                Gdx.app.log("LOAD", "Font added, name : " + fontToAdd.getName());
                if (DEFAULT.equals(fontConfigData.getName())) {
                    family.insert(0, fontToAdd);
                } else {
                    family.add(fontToAdd);
                }
                generator.dispose();
            }
        }
        if (!family.isEmpty()) {
            Font result = family.get(0);
            Font[] fonts = family.toArray(Font.class);
            result.setFamily(new Font.FontFamily(fonts));
            return result;
        }
        return new Font();
    }
}
