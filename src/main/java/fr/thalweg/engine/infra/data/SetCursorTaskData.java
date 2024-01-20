package fr.thalweg.engine.infra.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class SetCursorTaskData extends TaskData {
    public static final String JSON_PROPERTY_CURSOR = "cursor";
    public static final String JSON_PROPERTY_X_HOTSPOT = "xHotspot";
    public static final String JSON_PROPERTY_Y_HOTSPOT = "yHotspot";
    public String cursor;
    public int xHotspot = 0;
    public int yHotspot = 0;

    public SetCursorTaskData() {
        super();
    }

    public SetCursorTaskData cursor(String cursor) {

        this.cursor = cursor;
        return this;
    }

    /**
     * Get cursor
     *
     * @return cursor
     **/
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_CURSOR)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public String getCursor() {
        return cursor;
    }


    @JsonProperty(JSON_PROPERTY_CURSOR)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setCursor(String cursor) {
        this.cursor = cursor;
    }


    public SetCursorTaskData xHotspot(int xHotspot) {

        this.xHotspot = xHotspot;
        return this;
    }

    /**
     * Position on the X-Axis of the active pixel
     *
     * @return xHotspot
     **/
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_X_HOTSPOT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public int getxHotspot() {
        return xHotspot;
    }


    @JsonProperty(JSON_PROPERTY_X_HOTSPOT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setxHotspot(int xHotspot) {
        this.xHotspot = xHotspot;
    }


    public SetCursorTaskData yHotspot(int yHotspot) {

        this.yHotspot = yHotspot;
        return this;
    }

    /**
     * Position on the Y-Axis of the active pixel
     *
     * @return yHotspot
     **/
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_Y_HOTSPOT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public int getyHotspot() {
        return yHotspot;
    }


    @JsonProperty(JSON_PROPERTY_Y_HOTSPOT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setyHotspot(int yHotspot) {
        this.yHotspot = yHotspot;
    }

    public SetCursorTaskData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        SetCursorTaskData clone(SetCursorTaskData source);
    }

}

