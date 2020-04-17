package br.com.midway.beersapi.dto;

import br.com.midway.beersapi.model.BoilVolume;
import br.com.midway.beersapi.model.Ingredients;
import br.com.midway.beersapi.model.Method;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class BeerDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("tagline")
    private String tagline;
    @JsonProperty("first_brewed")
    private String firstBrewed;
    @JsonProperty("description")
    private String description;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("abv")
    private Double abv;
    @JsonProperty("ibu")
    private Long ibu;
    @JsonProperty("target_fg")
    private Long targetFg;
    @JsonProperty("target_og")
    private Long targetOg;
    @JsonProperty("ebc")
    private Long ebc;
    @JsonProperty("srm")
    private Long srm;
    @JsonProperty("ph")
    private Double ph;
    @JsonProperty("attenuation_level")
    private Long attenuationLevel;
    @JsonProperty("volume")
    private BoilVolume volume;
    @JsonProperty("boil_volume")
    private BoilVolume boilVolume;
    @JsonProperty("method")
    private Method method;
    @JsonProperty("ingredients")
    private Ingredients ingredients;
    @JsonProperty("food_pairing")
    private List<String> foodPairing;
    @JsonProperty("brewers_tips")
    private String brewersTips;
    @JsonProperty("contributed_by")
    private String contributedBy;
    private Long version;
    private String ip;
}
