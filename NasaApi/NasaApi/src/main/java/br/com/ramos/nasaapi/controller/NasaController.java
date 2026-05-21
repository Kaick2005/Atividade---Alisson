package br.com.ramos.nasaapi.controller;

import br.com.ramos.nasaapi.dto.ApiResponse;
import br.com.ramos.nasaapi.dto.NasaImageRequest;
import br.com.ramos.nasaapi.dto.NasaImageResponse;
import br.com.ramos.nasaapi.service.NasaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/images")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Imagens NASA", description = "CRUD de imagens do APOD")
public class NasaController {

    private final NasaService service;

    public NasaController(NasaService service) {
        this.service = service;
    }

    @Operation(summary = "Buscar imagem do dia na NASA e salvar")
    @PostMapping("/fetch")
    public ResponseEntity<ApiResponse<NasaImageResponse>> fetch() {
        NasaImageResponse saved = service.fetchAndSave();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Imagem salva com sucesso", saved));
    }

    @Operation(summary = "Listar imagens com paginação, filtro e ordenação")
    @GetMapping
    public ResponseEntity<ApiResponse<Page<NasaImageResponse>>> list(
            @RequestParam(required = false) String title,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<NasaImageResponse> page = service.list(title, pageable);
        ApiResponse.PageMeta meta = new ApiResponse.PageMeta(
                page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
        return ResponseEntity.ok(ApiResponse.ok(page, meta));
    }

    @Operation(summary = "Buscar imagem por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NasaImageResponse>> get(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.get(id)));
    }

    @Operation(summary = "Atualizar imagem completa (admin)")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<NasaImageResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody NasaImageRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Imagem atualizada", service.update(id, request)));
    }

    @Operation(summary = "Atualizar imagem parcialmente (admin)")
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<NasaImageResponse>> patch(
            @PathVariable Long id,
            @RequestBody NasaImageRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Imagem atualizada", service.patch(id, request)));
    }

    @Operation(summary = "Remover imagem (admin)")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
