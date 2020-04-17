package br.com.midway.beersapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MashTemp {
    private BoilVolume temp;
    private Long duration;
}
