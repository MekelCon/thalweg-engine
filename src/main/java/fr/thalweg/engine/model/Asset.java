package fr.thalweg.engine.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import fr.thalweg.engine.ThalwegGame;
import fr.thalweg.engine.validator.ValidationUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Asset {

    public final AssetType type;
    public final String path;

    public static Asset of(AssetType type, String path) {
        Asset.assertValid(type, path);
        return new Asset(type, path);
    }

    private static void assertValid(AssetType type, String path) {
        ValidationUtils.get()
                .notNull(type);
        ValidationUtils.get()
                .notNull(type)
                .notEmpty(path);
        if (!path.startsWith(type.getFolderName())) {
            ValidationUtils.throwDedicatedException("Incorrect asset path");
        }
    }

    public FileHandle getFileHandle() {
        return Gdx.files.internal(ThalwegGame.get().getRoot().getSubFolder(path));
    }
}
