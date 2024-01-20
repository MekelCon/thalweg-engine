package fr.thalweg.engine.infra.data;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class ThalwegGameConfigurationData {

    public Boolean debug = false;
    public GdxConfigurationData gdx;
    public Lwjgl3ApplicationConfigData lwjgl3ApplicationConfig;
    public String startScreen;
    public WorldData world;

    public ThalwegGameConfigurationData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        ThalwegGameConfigurationData clone(ThalwegGameConfigurationData source);

        default GdxConfigurationData mapGdxConfigurationData(GdxConfigurationData source) {
            return GdxConfigurationData.Cloner.INSTANCE.clone(source);
        }

        default Lwjgl3ApplicationConfigData mapLwjgl3ApplicationConfigData(Lwjgl3ApplicationConfigData source) {
            return Lwjgl3ApplicationConfigData.Cloner.INSTANCE.clone(source);
        }

        default WorldData mapWorldData(WorldData source) {
            return WorldData.Cloner.INSTANCE.clone(source);
        }
    }
}

