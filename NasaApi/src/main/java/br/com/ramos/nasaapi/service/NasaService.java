package br.com.ramos.nasaapi.service;

import br.com.ramos.nasaapi.client.NasaClient;
import br.com.ramos.nasaapi.dto.NasaImageRequest;
import br.com.ramos.nasaapi.dto.NasaImageResponse;
import br.com.ramos.nasaapi.exception.ResourceNotFoundException;
import br.com.ramos.nasaapi.model.NasaImage;
import br.com.ramos.nasaapi.repository.NasaImageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NasaService {

    private final NasaImageRepository repository;
    private final NasaClient client;

    public NasaService(NasaImageRepository repository, NasaClient client) {
        this.repository = repository;
        this.client = client;
    }

    public NasaImageResponse fetchAndSave() {
        NasaImage img = client.fetchApod();
        return NasaImageResponse.from(repository.save(img));
    }

    public Page<NasaImageResponse> list(String title, Pageable pageable) {
        if (title != null && !title.isBlank()) {
            return repository.findByTitleContainingIgnoreCase(title, pageable)
                    .map(NasaImageResponse::from);
        }
        return repository.findAll(pageable).map(NasaImageResponse::from);
    }

    public NasaImageResponse get(Long id) {
        NasaImage img = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NasaImage", id));
        return NasaImageResponse.from(img);
    }

    public NasaImageResponse update(Long id, NasaImageRequest request) {
        NasaImage img = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NasaImage", id));
        img.setTitle(request.getTitle());
        img.setExplanation(request.getExplanation());
        img.setUrl(request.getUrl());
        img.setDate(request.getDate());
        img.setMediaType(request.getMediaType());
        return NasaImageResponse.from(repository.save(img));
    }

    public NasaImageResponse patch(Long id, NasaImageRequest request) {
        NasaImage img = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NasaImage", id));
        if (request.getTitle()       != null) img.setTitle(request.getTitle());
        if (request.getExplanation() != null) img.setExplanation(request.getExplanation());
        if (request.getUrl()         != null) img.setUrl(request.getUrl());
        if (request.getDate()        != null) img.setDate(request.getDate());
        if (request.getMediaType()   != null) img.setMediaType(request.getMediaType());
        return NasaImageResponse.from(repository.save(img));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("NasaImage", id);
        }
        repository.deleteById(id);
    }
}
