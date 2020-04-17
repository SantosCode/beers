package br.com.midway.beersapi.resource.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestDescription {
    @ApiModelProperty(name = "description", notes = "descrição da cerveja", required = true, position = 2)
    private String description;
}
