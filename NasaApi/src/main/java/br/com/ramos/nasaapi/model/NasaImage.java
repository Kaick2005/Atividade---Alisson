package br.com.ramos.nasaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class NasaImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000)
    private String explanation;

    private String url;

    private String date;

    // GETTERS E SETTERS

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}