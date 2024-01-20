/*
 * Not used API
 * Just here to generate model of the thalweg engine
 *
 * The version of the OpenAPI document: 2024.01.01
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.thalweg.engine.infra.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

/**
 * VarsExistingData
 */
@JsonPropertyOrder({
        VarsExistingData.JSON_PROPERTY_MOUSE_LABEL_DEFAULT_TOKEN
})
@JsonTypeName("Vars_existing")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class VarsExistingData {
    public static final String JSON_PROPERTY_MOUSE_LABEL_DEFAULT_TOKEN = "mouseLabelDefaultToken";
    public String mouseLabelDefaultToken;

    public VarsExistingData() {
    }

    public VarsExistingData mouseLabelDefaultToken(String mouseLabelDefaultToken) {

        this.mouseLabelDefaultToken = mouseLabelDefaultToken;
        return this;
    }

    /**
     * Get mouseLabelDefaultToken
     *
     * @return mouseLabelDefaultToken
     **/
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_MOUSE_LABEL_DEFAULT_TOKEN)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public String getMouseLabelDefaultToken() {
        return mouseLabelDefaultToken;
    }


    @JsonProperty(JSON_PROPERTY_MOUSE_LABEL_DEFAULT_TOKEN)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setMouseLabelDefaultToken(String mouseLabelDefaultToken) {
        this.mouseLabelDefaultToken = mouseLabelDefaultToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VarsExistingData varsExisting = (VarsExistingData) o;
        return Objects.equals(this.mouseLabelDefaultToken, varsExisting.mouseLabelDefaultToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mouseLabelDefaultToken);
    }

    @Override
    public String toString() {
        String sb = "class VarsExistingData {\n" +
                "    mouseLabelDefaultToken: " + toIndentedString(mouseLabelDefaultToken) + "\n" +
                "}";
        return sb;
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public VarsExistingData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        VarsExistingData clone(VarsExistingData source);


    }

}

