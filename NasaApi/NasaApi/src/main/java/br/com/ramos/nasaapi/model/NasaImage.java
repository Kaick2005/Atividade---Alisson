package br.com.ramos.nasaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "nasa_images")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NasaImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String title;

    @Column(length = 5000)
    private String explanation;

    private String url;

    private String date;

    private String mediaType;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Constructors
    public NasaImage() {}

    public NasaImage(String title, String explanation, String url, String date, String mediaType) {
        this.title = title;
        this.explanation = explanation;
        this.url = url;
        this.date = date;
        this.mediaType = mediaType;
    }

    // Getters e Setters
    public Long getId() { return id; }

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

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}