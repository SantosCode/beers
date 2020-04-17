package br.com.midway.beersapi.service;

import br.com.midway.beersapi.dto.BeerDTO;
import br.com.midway.beersapi.model.Beer;

import java.util.List;
import java.util.Optional;

public interface BeerService {

    Optional<List<BeerDTO>> getAll();
    Optional<BeerDTO> getOne(Long id);
    Optional<BeerDTO> updateDescription(Long id, String description);
}
