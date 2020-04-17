package br.com.midway.beersapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter @Setter
@Document
public class Beer {
    @Id
    private String _id;
    @Indexed(unique = true)
    private Long id;
    private String name;
    private String tagline;
    private String firstBrewed;
    private String description;
    private String imageUrl;
    private Double abv;
    private Long ibu;
    private Long targetFg;
    private Long targetOg;
    private Long ebc;
    private Long srm;
    private Double ph;
    private Long attenuationLevel;
    private BoilVolume volume;
    private BoilVolume boilVolume;
    private Method method;
    private Ingredients ingredients;
    private List<String> foodPairing;
    private String brewersTips;
    private String contributedBy;
}
