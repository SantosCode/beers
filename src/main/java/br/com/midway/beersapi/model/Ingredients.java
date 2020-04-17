package br.com.midway.beersapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Ingredients {
    private List<Malt> malt;
    private List<Hop> hops;
    private String yeast;
}
