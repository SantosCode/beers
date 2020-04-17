package br.com.midway.beersapi.repository;

import br.com.midway.beersapi.model.Beer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerRepository extends MongoRepository<Beer, String> {
    Optional<Beer> findBeerById(Long id);
}
