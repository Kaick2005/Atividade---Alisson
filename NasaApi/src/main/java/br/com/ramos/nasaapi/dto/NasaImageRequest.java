package br.com.ramos.nasaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada para criação e atualização de imagens NASA.
 * Contém validações de Bean Validation.
 */
public class NasaImageRequest {

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 3, max = 200, message = "Título deve ter entre 3 e 200 caracteres")
    private String title;

    @Size(max = 5000, message = "Explicação não pode ultrapassar 5000 caracteres")
    private String explanation;

    @NotBlank(message = "URL é obrigatória")
    private String url;

    @NotBlank(message = "Data é obrigatória")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Data deve estar no formato yyyy-MM-dd")
    private String date;

    private String mediaType;

    public NasaImageRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }
}