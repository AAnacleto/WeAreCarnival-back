package wearecarnival.com.services;

import wearecarnival.com.models.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {

    Usuario save(Usuario usuario);

    Usuario update(Usuario usuario);

    void delete(UUID id);

    Usuario findById(UUID id);

    List<Usuario> findAll();

    Usuario findByName(String nome);

    Usuario findByEmail(String email);
}
