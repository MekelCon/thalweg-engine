package fr.thalweg.engine.infra.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class OverTimeTaskData extends TaskData {
    public static final String JSON_PROPERTY_DURATION = "duration";
    public static final String JSON_PROPERTY_INTERPOLATION = "interpolation";
    public static final String JSON_PROPERTY_DELAY = "delay";
    public float duration = 0f;
    public InterpolationData interpolation = InterpolationData.LINEAR;
    public float delay = 0f;

    public OverTimeTaskData() {
        super();
    }

    public OverTimeTaskData duration(float duration) {

        this.duration = duration;
        return this;
    }

    /**
     * The duration in seconds
     *
     * @return duration
     **/
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_DURATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public float getDuration() {
        return duration;
    }


    @JsonProperty(JSON_PROPERTY_DURATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDuration(float duration) {
        this.duration = duration;
    }


    public OverTimeTaskData interpolation(InterpolationData interpolation) {

        this.interpolation = interpolation;
        return this;
    }

    /**
     * Get interpolation
     *
     * @return interpolation
     **/
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_INTERPOLATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public InterpolationData getInterpolation() {
        return interpolation;
    }


    @JsonProperty(JSON_PROPERTY_INTERPOLATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setInterpolation(InterpolationData interpolation) {
        this.interpolation = interpolation;
    }


    public OverTimeTaskData delay(float delay) {

        this.delay = delay;
        return this;
    }

    /**
     * The time to wait before starting the action
     *
     * @return delay
     **/
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_DELAY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public float getDelay() {
        return delay;
    }


    @JsonProperty(JSON_PROPERTY_DELAY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDelay(float delay) {
        this.delay = delay;
    }

    public OverTimeTaskData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        OverTimeTaskData clone(OverTimeTaskData source);


    }

}

