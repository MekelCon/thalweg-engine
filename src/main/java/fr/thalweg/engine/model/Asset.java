package fr.thalweg.engine.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import fr.thalweg.engine.validator.ValidationUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Asset {

    public final Directory root;
    public final AssetType type;
    public final String path;

    public static Asset of(Directory root, AssetType type, String path) {
        Asset.assertValid(root, type, path);
        return new Asset(root, type, path);
    }

    private static void assertValid(Directory root, AssetType type, String path) {
        ValidationUtils.get()
                .notNull(root);
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
        return Gdx.files.internal(root.getSubFolder(path));
    }
}
