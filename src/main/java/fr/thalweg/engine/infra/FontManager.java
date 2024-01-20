package fr.thalweg.engine.infra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;
import com.github.tommyettinger.textra.Font;
import com.github.tommyettinger.textra.TypingConfig;
import fr.thalweg.engine.infra.data.CustomVarData;
import fr.thalweg.engine.infra.data.FontConfigData;
import fr.thalweg.engine.infra.data.FontConfigsData;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.system.rendering.TextRenderingSystem;


public class FontManager {

    public static final String DEFAULT = "default";
    public final Font font;
    private final Directory root;

    public FontManager(Directory root) {
        this.root = root;
        var fontConfigsData = Reader.getInstance().read(Gdx.files.internal(root.getSubFolder("font/font-config.json")), FontConfigsData.class);
        this.font = loadFontFamily(fontConfigsData);
        this.loadGlobalVars(fontConfigsData);
    }

    private Font loadFontFamily(FontConfigsData fontConfigsData) {
        var family = new Array<Font>();
        if (fontConfigsData.family != null) {
            for (FontConfigData fontConfigData : fontConfigsData.family) {
                FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(root.getSubFolder(fontConfigData.source)));
                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.borderWidth = fontConfigData.borderSize;
                parameter.size = (int) (fontConfigData.size * Gdx.graphics.getDensity());
                var fontToAdd = new Font(
                        generator.generateFont(parameter),
                        fontConfigData.xAdjust,
                        fontConfigData.yAdjust,
                        fontConfigData.widthAdjust,
                        fontConfigData.heightAdjust);
                fontToAdd.setName(fontConfigData.name);
                Gdx.app.log("LOAD", "Font added, name : " + fontToAdd.getName());
                if (DEFAULT.equals(fontConfigData.name)) {
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

    private void loadGlobalVars(FontConfigsData fontConfigsData) {
        if (fontConfigsData.vars != null) {
            if (fontConfigsData.vars.existing != null) {
                if (fontConfigsData.vars.existing.mouseLabelDefaultToken != null) {
                    TypingConfig.GLOBAL_VARS.put(
                            TextRenderingSystem.MOUSE_LABEL_DEFAULT_TOKEN_VAR_NAME,
                            fontConfigsData.vars.existing.mouseLabelDefaultToken);
                }
            }
            if (fontConfigsData.vars.custom != null) {
                for (CustomVarData var : fontConfigsData.vars.custom) {
                    TypingConfig.GLOBAL_VARS.put(var.name, var.value);
                }
            }
        }
    }
}
