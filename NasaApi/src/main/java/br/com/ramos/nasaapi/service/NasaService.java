package br.com.ramos.nasaapi.service;

import br.com.ramos.nasaapi.client.NasaClient;
import br.com.ramos.nasaapi.model.NasaImage;
import br.com.ramos.nasaapi.repository.NasaImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NasaService {

    private final NasaImageRepository repository;
    private final NasaClient client;

    public NasaService(NasaImageRepository repository, NasaClient client) {
        this.repository = repository;
        this.client = client;
    }

    public NasaImage fetchAndSave() {

        NasaImage img = client.fetchApod();

        return repository.save(img);
    }

    public List<NasaImage> list() {
        return repository.findAll();
    }

    public NasaImage get(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public NasaImage update(Long id, NasaImage data) {
        NasaImage img = get(id);
        img.setTitle(data.getTitle());
        img.setExplanation(data.getExplanation());
        img.setUrl(data.getUrl());
        img.setDate(data.getDate());
        return repository.save(img);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}