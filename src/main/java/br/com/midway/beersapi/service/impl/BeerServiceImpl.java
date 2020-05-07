package br.com.midway.beersapi.service.impl;

import br.com.midway.beersapi.dto.BeerDTO;
import br.com.midway.beersapi.model.Beer;
import br.com.midway.beersapi.repository.BeerRepository;
import br.com.midway.beersapi.service.BeerService;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository repository;
    private final ModelMapper mapper = new ModelMapper();
    private final Type typeDto = new TypeToken<List<BeerDTO>>() {}.getType();

    @Autowired
    public BeerServiceImpl(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<BeerDTO>> getAll() {
        val beerList = repository.findAll();
        return Optional.of(mapper.map(beerList, typeDto));
    }

    @Override
    public Optional<List<BeerDTO>> getOne(Long id) {
        val beer = repository.findBeerById(id);
        if (beer.isPresent()) {
            val beerList = Collections.singletonList(beer.get());
            return Optional.of(mapper.map(beerList, typeDto));
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<BeerDTO>> updateDescription(Long id, String description,
            HttpServletRequest request) {
        if (description != null && id != null) {
            val beer = repository.findBeerById(id);
            if (beer.isPresent()) {
                beer.get().setDescription(description);
                beer.get().setIp(request.getRemoteAddr());
                beer.get().setVersion(beer.get().getVersion() + 1);
                repository.save(beer.get());
                val beerRecuperado = repository.findBeerById(id);
                val beerList = Collections.singletonList(beerRecuperado.get());
                return Optional.of(mapper.map(beerList, typeDto));
            }
        }
        return Optional.empty();
    }
}
