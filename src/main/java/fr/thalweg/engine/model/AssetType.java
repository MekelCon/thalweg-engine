package fr.thalweg.engine.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AssetType {

    private final static Map<ASSET_NAME, AssetType> allByName =
            Map.of(ASSET_NAME.SCREEN, new AssetType("screen"));

    private final String folderName;

    public static AssetType screen() {
        return allByName.get(ASSET_NAME.SCREEN);
    }

    public static List<AssetType> allType() {
        return allByName
                .values()
                .stream()
                .toList();
    }

    public String getGameFolder(Directory root) {
        return root.getSubFolder(folderName);
    }

    private enum ASSET_NAME {
        SCREEN
    }
}
