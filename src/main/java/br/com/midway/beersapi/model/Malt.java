package br.com.midway.beersapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Malt {
    private String name;
    private BoilVolume amount;
}
