package br.com.ramos.nasaapi.controller;

import br.com.ramos.nasaapi.model.NasaImage;
import br.com.ramos.nasaapi.service.NasaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class NasaController {

    private final NasaService service;

    public NasaController(NasaService service) {
        this.service = service;
    }

    @PostMapping("/fetch")
    public ResponseEntity<NasaImage> fetch() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.fetchAndSave());
    }

    @GetMapping
    public List<NasaImage> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public NasaImage get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public NasaImage update(@PathVariable Long id, @RequestBody NasaImage img) {
        return service.update(id, img);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}