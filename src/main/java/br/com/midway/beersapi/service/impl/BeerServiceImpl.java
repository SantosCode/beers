package br.com.midway.beersapi.service.impl;

import br.com.midway.beersapi.dto.BeerDTO;
import br.com.midway.beersapi.model.Beer;
import br.com.midway.beersapi.repository.BeerRepository;
import br.com.midway.beersapi.service.BeerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class BeerServiceImpl implements BeerService {

    private BeerRepository repository;
    private ModelMapper mapper = new ModelMapper();

    @Autowired
    public BeerServiceImpl(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<BeerDTO>> getAll() {
        List<Beer> beerList = repository.findAll();
        Type typeList = new TypeToken<List<BeerDTO>>(){}.getType();
        Optional<List<BeerDTO>> beers = Optional.of(mapper.map(beerList, typeList));
        return beers.isPresent() ? beers : Optional.empty();
    }

    @Override
    public Optional<BeerDTO> getOne(Long id) {
        Optional<Beer> beer = repository.findBeerById(id);
        if (beer.isPresent()) {
            Type typeDto = new TypeToken<Optional<BeerDTO>>(){}.getType();
            return mapper.map(beer, typeDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<BeerDTO> updateDescription(Long id, String description,
            HttpServletRequest request) {
        if (description != null && id != null) {
            Optional<Beer> beer = repository.findBeerById(id);
            if (beer.isPresent()) {
                beer.get().setDescription(description);
                beer.get().setIp(request.getRemoteAddr());
                beer.get().setVersion(beer.get().getVersion() + 1);
                repository.save(beer.get());
                Optional<Beer> beerRecuperado = repository.findBeerById(id);
                Type typeDto = new TypeToken<Optional<BeerDTO>>(){}.getType();
                Optional<BeerDTO> beerDTO = mapper.map(beerRecuperado, typeDto);
                return beerDTO.isPresent() ? beerDTO : Optional.empty();
            }
        }
        return Optional.empty();
    }
}
