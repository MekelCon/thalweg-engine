package fr.thalweg.engine.infra.data;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class Lwjgl3ApplicationConfigData {

    public String title = "My Thalweg Game";
    public WidthAndHeightData windowed;
    public Boolean useVSync = true;
    public int foregroundFPS = 60;

    public Lwjgl3ApplicationConfigData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        Lwjgl3ApplicationConfigData clone(Lwjgl3ApplicationConfigData source);

        default WidthAndHeightData mapWidthAndHeightData(WidthAndHeightData source) {
            return WidthAndHeightData.Cloner.INSTANCE.clone(source);
        }
    }
}

