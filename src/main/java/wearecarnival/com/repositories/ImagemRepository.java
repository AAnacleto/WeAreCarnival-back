package wearecarnival.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wearecarnival.com.models.Imagem;

import java.util.Optional;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    Optional<Imagem> findByName(String fileName);
}
