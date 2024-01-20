package fr.thalweg.engine.infra.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class PlayTransitionTaskData extends OverTimeTaskData {
    public static final String JSON_PROPERTY_TRANSITION = "transition";
    public String transition;

    public PlayTransitionTaskData() {
        super();
    }

    public PlayTransitionTaskData transition(String transition) {

        this.transition = transition;
        return this;
    }

    /**
     * Name of the transition
     *
     * @return transition
     **/
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_TRANSITION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public String getTransition() {
        return transition;
    }


    @JsonProperty(JSON_PROPERTY_TRANSITION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTransition(String transition) {
        this.transition = transition;
    }

    public PlayTransitionTaskData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        PlayTransitionTaskData clone(PlayTransitionTaskData source);


    }

}

