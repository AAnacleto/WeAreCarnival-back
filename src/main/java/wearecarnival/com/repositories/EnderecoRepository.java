package wearecarnival.com.repositories;

import wearecarnival.com.models.Endereco;

import java.util.List;
import java.util.UUID;

public interface EnderecoRepository {

    Endereco save(Endereco endereco);

    Endereco update(Endereco endereco);

    void delete(UUID id);

    Endereco findById(UUID id);

    List<Endereco> findAll();

    Endereco findByName(String nomeRua);

}
