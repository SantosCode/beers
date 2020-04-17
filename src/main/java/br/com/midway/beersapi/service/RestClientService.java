package br.com.midway.beersapi.service;

import br.com.midway.beersapi.dto.BeerDTO;
import br.com.midway.beersapi.model.Beer;
import br.com.midway.beersapi.resource.response.BeerResponse;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class RestClientService {

    private static final String URI = "https://api.punkapi.com/v2";
    private ModelMapper modelMapper = new ModelMapper();

    public List<Beer> getBeers() {

        RestTemplate restTemplate = new RestTemplate(getRequestFactory());
        String path = "/beers";
        ResponseEntity<List<BeerDTO>> response = restTemplate.exchange(URI.concat(path),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<BeerDTO>>(){});
        if (response.getStatusCode().is2xxSuccessful()
                && !response.getBody().isEmpty()) {
            Type typeDocument = new TypeToken<List<Beer>>() {}.getType();
            return modelMapper.map(response.getBody(), typeDocument);
        } else if (response.getStatusCode().is4xxClientError()) {
            //TODO: Criar Exception
            return null;
        }
        return null;
    }

    private HttpComponentsClientHttpRequestFactory getRequestFactory() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        requestFactory.setHttpClient(httpClient);
        return requestFactory;
    }
}
