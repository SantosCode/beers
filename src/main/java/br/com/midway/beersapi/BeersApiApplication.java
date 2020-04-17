package br.com.midway.beersapi;

import br.com.midway.beersapi.model.Beer;
import br.com.midway.beersapi.repository.BeerRepository;
import br.com.midway.beersapi.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = BeerRepository.class)
public class BeersApiApplication implements CommandLineRunner {

    @Autowired private BeerRepository repository;
    @Autowired private RestClientService restClientService;


    public static void main(String[] args) {
        SpringApplication.run(BeersApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        List<Beer> beers = restClientService.getBeers();
        repository.saveAll(beers);
    }
}
