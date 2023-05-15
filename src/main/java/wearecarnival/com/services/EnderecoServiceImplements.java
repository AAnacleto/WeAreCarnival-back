package wearecarnival.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wearecarnival.com.models.Endereco;
import wearecarnival.com.repositories.EnderecoRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EnderecoServiceImplements implements EnderecoService{

    @Autowired
    private EnderecoRepository repository;

    @Override
    public Endereco save(Endereco endereco) {
        return repository.save(endereco);
    }

    @Override
    public Endereco update(Endereco endereco) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Endereco findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Endereco> findAll() {
        return null;
    }

    @Override
    public Endereco findByName(String nomeRua) {
        return null;
    }
}
