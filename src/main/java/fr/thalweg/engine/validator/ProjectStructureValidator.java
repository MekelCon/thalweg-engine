package fr.thalweg.engine.validator;

import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.model.AssetType;

public class ProjectStructureValidator {

    public static void throwDedicatedException(String message) {
        throw new InvalidThalwegGameStructureException(message);
    }

    public static void validThalwegGameStructure() {
        checkAssets();
    }

    private static void checkAssets() {
        for (AssetType assetType : AssetType.allType()) {
            if (!Gdx.files.internal(assetType.getGameFolder()).exists()) {
                throwDedicatedException("The directory doest not exist : "
                        + assetType.getGameFolder());
            }
        }
    }
}
