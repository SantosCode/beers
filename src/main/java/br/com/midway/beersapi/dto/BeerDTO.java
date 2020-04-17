package br.com.midway.beersapi.dto;

import br.com.midway.beersapi.model.BoilVolume;
import br.com.midway.beersapi.model.Ingredients;
import br.com.midway.beersapi.model.Method;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class BeerDTO {
    @ApiModelProperty(name = "id", position = 1)
    private Long id;
    @ApiModelProperty(name = "name", position = 2)
    private String name;
    @ApiModelProperty(name = "tagline", position = 4)
    private String tagline;
    @ApiModelProperty(name = "first_brewed", position = 5)
    private String firstBrewed;
    @ApiModelProperty(name = "description", position = 6)
    private String description;
    @ApiModelProperty(name = "image_url", position = 7)
    private String imageUrl;
    @ApiModelProperty(name = "abv", position = 8)
    private Double abv;
    @ApiModelProperty(name = "ibu", position = 9)
    private Long ibu;
    @ApiModelProperty(name = "target_fg", position = 10)
    private Long targetFg;
    @ApiModelProperty(name = "target_og", position = 11)
    private Long targetOg;
    @ApiModelProperty(name = "ebc", position = 12)
    private Long ebc;
    @ApiModelProperty(name = "srm", position = 13)
    private Long srm;
    @ApiModelProperty(name = "ph", position = 14)
    private Double ph;
    @ApiModelProperty(name = "attenuation_level", position = 15)
    private Long attenuationLevel;
    @ApiModelProperty(name = "volume", position = 16)
    private BoilVolume volume;
    @ApiModelProperty(name = "boil_volume", position = 17)
    private BoilVolume boilVolume;
    @ApiModelProperty(name = "method", position = 18)
    private Method method;
    @ApiModelProperty(name = "ingredients", position = 19)
    private Ingredients ingredients;
    @ApiModelProperty(name = "food_pairing", position = 20)
    private List<String> foodPairing;
    @ApiModelProperty(name = "brewers_tips", position = 21)
    private String brewersTips;
    @ApiModelProperty(name = "contributed_by", position = 22)
    private String contributedBy;
    @ApiModelProperty(name = "version", position = 13)
    private Long version;
    @ApiModelProperty(name = "ip", position = 14)
    private String ip;
}
