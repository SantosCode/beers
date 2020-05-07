package br.com.midway.beersapi.resource;

import br.com.midway.beersapi.dto.BeerDTO;
import br.com.midway.beersapi.resource.exceptionHandler.BeerExceptionHandler;
import br.com.midway.beersapi.resource.request.RequestDescription;
import br.com.midway.beersapi.resource.response.BeerResponse;
import br.com.midway.beersapi.service.BeerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.http.ResponseEntity.noContent;

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
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna lista de cervejas"),
            @ApiResponse(code = 400, response = BeerExceptionHandler.Erro.class, message = "Bad Request!") })
    @GetMapping("/listar")
    public ResponseEntity<BeerResponse> getAll() {
        val beerList = service.getAll();
        return getBeerResponseResponseEntity(beerList);
    }

    @ApiOperation(value = "Buscar cerveja pelo id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a cervejas pesquisada"),
            @ApiResponse(code = 400, response = BeerExceptionHandler.Erro.class, message = "Bad Request!") })
    @GetMapping("/buscar-id/{id}")
    public ResponseEntity<BeerResponse> getOne(@PathVariable Long id) {
        val beerList = service.getOne(id);
        return getBeerResponseResponseEntity(beerList);
    }

    @ApiOperation(value = "Atualizar descrição de uma cerveja")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a cerveja alterada"),
            @ApiResponse(code = 400, response = BeerExceptionHandler.Erro.class, message = "Bad Request!") })
    @PatchMapping("/atualizar-desc/{id}")
    public ResponseEntity<BeerResponse> updateDesc(
            @RequestBody @Validated RequestDescription requestBody, @PathVariable Long id) {
        val beerList = service.updateDescription(id, requestBody.getDescription(), request);
        return getBeerResponseResponseEntity(beerList);
    }

    private ResponseEntity<BeerResponse> getBeerResponseResponseEntity(
            Optional<List<BeerDTO>> beerList) {
        if (beerList.isPresent()) {
            val response = Optional.of(new BeerResponse(beerList.get()));
            return response.map(ResponseEntity::ok).get();
        }
        return noContent().build();
    }
}
