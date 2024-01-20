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
 * ColorData
 */
@JsonPropertyOrder({
        ColorData.JSON_PROPERTY_R,
        ColorData.JSON_PROPERTY_G,
        ColorData.JSON_PROPERTY_B,
        ColorData.JSON_PROPERTY_A
})
@JsonTypeName("Color")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ColorData {
    public static final String JSON_PROPERTY_R = "r";
    public static final String JSON_PROPERTY_G = "g";
    public static final String JSON_PROPERTY_B = "b";
    public static final String JSON_PROPERTY_A = "a";
    public float r = 1f;
    public float g = 1f;
    public float b = 1f;
    public float a = 1f;

    public ColorData() {
    }

    public ColorData r(float r) {

        this.r = r;
        return this;
    }

    /**
     * Get r
     *
     * @return r
     **/
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_R)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public float getR() {
        return r;
    }


    @JsonProperty(JSON_PROPERTY_R)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setR(float r) {
        this.r = r;
    }


    public ColorData g(float g) {

        this.g = g;
        return this;
    }

    /**
     * Get g
     *
     * @return g
     **/
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_G)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public float getG() {
        return g;
    }


    @JsonProperty(JSON_PROPERTY_G)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setG(float g) {
        this.g = g;
    }


    public ColorData b(float b) {

        this.b = b;
        return this;
    }

    /**
     * Get b
     *
     * @return b
     **/
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_B)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public float getB() {
        return b;
    }


    @JsonProperty(JSON_PROPERTY_B)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setB(float b) {
        this.b = b;
    }


    public ColorData a(float a) {

        this.a = a;
        return this;
    }

    /**
     * Get a
     *
     * @return a
     **/
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_A)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public float getA() {
        return a;
    }


    @JsonProperty(JSON_PROPERTY_A)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setA(float a) {
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ColorData color = (ColorData) o;
        return Objects.equals(this.r, color.r) &&
                Objects.equals(this.g, color.g) &&
                Objects.equals(this.b, color.b) &&
                Objects.equals(this.a, color.a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b, a);
    }

    @Override
    public String toString() {
        String sb = "class ColorData {\n" +
                "    r: " + toIndentedString(r) + "\n" +
                "    g: " + toIndentedString(g) + "\n" +
                "    b: " + toIndentedString(b) + "\n" +
                "    a: " + toIndentedString(a) + "\n" +
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

    public ColorData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        ColorData clone(ColorData source);


    }

}

