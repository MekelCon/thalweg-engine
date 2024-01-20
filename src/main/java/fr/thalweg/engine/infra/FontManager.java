package fr.thalweg.engine.infra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;
import com.github.tommyettinger.textra.Font;
import com.github.tommyettinger.textra.TypingConfig;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.system.rendering.TextRenderingSystem;
import fr.thalweg.gen.engine.model.CustomVarData;
import fr.thalweg.gen.engine.model.FontConfigData;
import fr.thalweg.gen.engine.model.FontConfigsData;


public class FontManager {

    public static final String DEFAULT = "default";
    public final Font font;
    private final Directory root;

    public FontManager(Directory root) {
        this.root = root;
        var fontConfigsData = Reader.getInstance().read(Gdx.files.internal(root.getSubFolder("font/font-config.yaml")), FontConfigsData.class);
        this.font = loadFontFamily(fontConfigsData);
        this.loadGlobalVars(fontConfigsData);
    }

    private Font loadFontFamily(FontConfigsData fontConfigsData) {
        var family = new Array<Font>();
        if (fontConfigsData.getFamily() != null) {
            for (FontConfigData fontConfigData : fontConfigsData.getFamily()) {
                FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(root.getSubFolder(fontConfigData.getSource())));
                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.borderWidth = fontConfigData.getBorderSize();
                parameter.size = (int) (fontConfigData.getSize() * Gdx.graphics.getDensity());
                var fontToAdd = new Font(
                        generator.generateFont(parameter),
                        fontConfigData.getxAdjust(),
                        fontConfigData.getyAdjust(),
                        fontConfigData.getWidthAdjust(),
                        fontConfigData.getHeightAdjust());
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

    private void loadGlobalVars(FontConfigsData fontConfigsData) {
        if (fontConfigsData.getVars() != null) {
            if (fontConfigsData.getVars().getExisting() != null) {
                if (fontConfigsData.getVars().getExisting().getMouseLabelDefaultToken() != null) {
                    TypingConfig.GLOBAL_VARS.put(
                            TextRenderingSystem.MOUSE_LABEL_DEFAULT_TOKEN_VAR_NAME,
                            fontConfigsData.getVars().getExisting().getMouseLabelDefaultToken());
                }
            }
            if (fontConfigsData.getVars().getCustom() != null) {
                for (CustomVarData var : fontConfigsData.getVars().getCustom()) {
                    TypingConfig.GLOBAL_VARS.put(var.getName(), var.getValue());
                }
            }
        }
    }
}
