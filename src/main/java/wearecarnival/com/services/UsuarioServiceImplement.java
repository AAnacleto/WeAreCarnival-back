package wearecarnival.com.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wearecarnival.com.models.Usuario;
import wearecarnival.com.repositories.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImplement implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioRepository.update(usuario);
    }

    @Override
    public void delete(UUID id) {
        usuarioRepository.delete(id);
    }

    @Override
    public Usuario findById(UUID id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findByName(String nome) {
        return usuarioRepository.findByName(nome);
    }
}
