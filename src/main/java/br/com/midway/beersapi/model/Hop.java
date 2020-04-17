package br.com.midway.beersapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Hop {
    private String name;
    private BoilVolume amount;
    private String add;
    private String attribute;
}
