package br.com.midway.beersapi.resource.response;

import br.com.midway.beersapi.dto.BeerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
public class BeerResponse {
    @Getter @Setter
    @JsonProperty("beers")
    @ApiModelProperty(name = "beers", notes = "Response com a lista de cervejas")
    List<BeerDTO> response;
}
