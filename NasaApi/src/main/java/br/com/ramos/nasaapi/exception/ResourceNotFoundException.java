package br.com.ramos.nasaapi.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " não encontrado com id: " + id);
    }
}