package fr.thalweg.engine.validator;

import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.model.AssetType;
import fr.thalweg.engine.model.Directory;

public class ProjectStructureValidator {

    public static void throwDedicatedException(String message) {
        throw new InvalidThalwegGameStructureException(message);
    }

    public static void validThalwegGameStructure(Directory root) {
        checkAssets(root);
    }

    private static void checkAssets(Directory root) {
        for (var assetType : AssetType.allType()) {
            if (!Gdx.files.internal(assetType.getGameFolder(root)).exists()) {
                throwDedicatedException("The directory doest not exist : "
                        + assetType.getGameFolder(root));
            }
        }
    }
}
