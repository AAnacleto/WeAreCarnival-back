package wearecarnival.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import wearecarnival.com.models.Endereco;
import wearecarnival.com.models.Eventos;
import wearecarnival.com.models.Imagem;
import wearecarnival.com.repositories.EnderecoRepository;
import wearecarnival.com.repositories.EventosRepository;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wearecarnival.com.repositories.ImagemRepository;


@Service
@Transactional
public class EventosServiceImplements implements EventosService {

    @Autowired
    private EventosRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ImagemRepository imagemRepository;


    public Eventos save(Eventos eventos){
        Imagem imagem = eventos.getImagem();
        Endereco endereco = eventos.getEndereco();
        eventos.setEndereco(enderecoRepository.save(endereco));
        eventos.setImagem(imagemRepository.save(imagem));
        return repository.save(eventos);
    }

    @Override
    public Eventos update(Eventos eventos) {
        Endereco endereco = eventos.getEndereco();
        eventos.setEndereco(enderecoRepository.update(endereco));
        return repository.update(eventos);
    }

    @Override
    public void delete(UUID id) {
       repository.delete(id);
    }

    @Override
    public Eventos findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Eventos>findByDay(int dayOfWeek) {
        return repository.findByDay(dayOfWeek);
    }

    @Override
    public List<Eventos> findByFavorite(boolean valor) {
        return repository.findByFavorite(valor);
    }

    @Override
    public Eventos findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Eventos> findByCity(String nomeCidade) {return repository.findByCity(nomeCidade);}

    @Override
    public List<Eventos> findAll() {
        return repository.findAll();
    }
}
