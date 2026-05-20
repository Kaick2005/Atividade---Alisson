package br.com.ramos.nasaapi.dto;

import br.com.ramos.nasaapi.model.NasaImage;
import java.time.LocalDateTime;

/**
 * DTO de saída para imagens NASA.
 * Controla exatamente o que é exposto na API.
 */
public class NasaImageResponse {

    private Long id;
    private String title;
    private String explanation;
    private String url;
    private String date;
    private String mediaType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static NasaImageResponse from(NasaImage img) {
        NasaImageResponse r = new NasaImageResponse();
        r.id = img.getId();
        r.title = img.getTitle();
        r.explanation = img.getExplanation();
        r.url = img.getUrl();
        r.date = img.getDate();
        r.mediaType = img.getMediaType();
        r.createdAt = img.getCreatedAt();
        r.updatedAt = img.getUpdatedAt();
        return r;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getExplanation() { return explanation; }
    public String getUrl() { return url; }
    public String getDate() { return date; }
    public String getMediaType() { return mediaType; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}