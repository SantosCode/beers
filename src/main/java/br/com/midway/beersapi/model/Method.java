package br.com.midway.beersapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Method {
    private List<MashTemp> mashTemp;
    private Fermentation fermentation;
    private Object twist;
}
