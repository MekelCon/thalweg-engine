package fr.thalweg.engine.model;

import fr.thalweg.engine.ThalwegGame;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AssetType {

    private final static Map<ASSET_NAME, AssetType> allByName =
            Map.of(ASSET_NAME.SCENE, new AssetType("scene"));

    private final String folderName;

    public static AssetType scene() {
        return allByName.get(ASSET_NAME.SCENE);
    }

    public static List<AssetType> allType() {
        return allByName
                .values()
                .stream()
                .toList();
    }

    public String getGameFolder() {
        return ThalwegGame.get().getRoot().getSubFolder(folderName);
    }

    private enum ASSET_NAME {
        SCENE
    }
}
