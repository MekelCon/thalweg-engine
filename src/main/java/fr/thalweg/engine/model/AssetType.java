package fr.thalweg.engine.model;

import fr.thalweg.engine.ThalwegEngineGame;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AssetType {

    private final static Map<ASSET_NAME, AssetType> allByName =
            Map.of(ASSET_NAME.SCENE, new AssetType("scene"));

    private String folderName;

    public static List<AssetType> allType() {
        return allByName
                .values()
                .stream()
                .toList();
    }

    public String getGameFolder() {
        return ThalwegEngineGame.get().getRoot().getSubFolder(folderName);
    }

    private enum ASSET_NAME {
        SCENE
    }
}
