package br.com.ramos.nasaapi.repository;

import br.com.ramos.nasaapi.model.NasaImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NasaImageRepository extends JpaRepository<NasaImage, Long> {
}
