package br.com.ramos.nasaapi.client;

import br.com.ramos.nasaapi.model.NasaImage;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NasaClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String URL =
            "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";

    public NasaImage fetchApod() {
        return restTemplate.getForObject(URL, NasaImage.class);
    }
}