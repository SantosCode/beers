package br.com.midway.beersapi.resource;

import br.com.midway.beersapi.dto.BeerDTO;
import br.com.midway.beersapi.resource.exceptionHandler.BeerExceptionHandler;
import br.com.midway.beersapi.resource.request.RequestDescription;
import br.com.midway.beersapi.resource.response.BeerResponse;
import br.com.midway.beersapi.service.BeerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.http.ResponseEntity.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/beer-api")
public class BeerResource {

    private BeerService service;
    private HttpServletRequest request;


    @Autowired
    public BeerResource(BeerService service, HttpServletRequest request) {
        this.service = service;
        this.request = request;
    }

    @ApiOperation(value = "Listar todas as cervejas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna lista de cervejas"),
            @ApiResponse(code = 400, response = BeerExceptionHandler.Erro.class, message = "Bad Request!")
    })
    @GetMapping("/listar")
    public ResponseEntity<BeerResponse> getAll() {
        Optional<BeerResponse> response = Optional.of(new BeerResponse(service.getAll().get()));
        return response.map(ResponseEntity::ok).orElseGet(() -> noContent().build());
    }

    @ApiOperation(value = "Buscar cerveja pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a cervejas pesquisada"),
            @ApiResponse(code = 400, response = BeerExceptionHandler.Erro.class, message = "Bad Request!")
    })
    @GetMapping("/buscar-id/{id}")
    public ResponseEntity<BeerResponse> getOne(@PathVariable Long id) {
        Optional<BeerDTO>  beerDTO = service.getOne(id);
        List<BeerDTO> beerDTOList = Collections.singletonList(beerDTO.get());
        Optional<BeerResponse> response = Optional.of(new BeerResponse(beerDTOList));
        return response.map(ResponseEntity::ok).orElseGet(() -> noContent().build());
    }

    @ApiOperation(value = "Atualizar descrição de uma cerveja")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a cerveja alterada"),
            @ApiResponse(code = 400, response = BeerExceptionHandler.Erro.class, message = "Bad Request!")
    })
    @PatchMapping("/atualizar-desc")
    public ResponseEntity<BeerResponse> updateDesc(@RequestBody @Validated RequestDescription requestDescription) {
        Optional<BeerDTO> beerDTO = service.updateDescription(requestDescription.getId(),
                requestDescription.getDescription(), request);
        List<BeerDTO> beerDTOList = Collections.singletonList(beerDTO.get());
        Optional<BeerResponse> response = Optional.of(new BeerResponse(beerDTOList));
        return response.map(ResponseEntity::ok).orElseGet(() -> noContent().build());
    }
}
