package wearecarnival.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wearecarnival.com.models.Endereco;
import wearecarnival.com.models.Eventos;
import wearecarnival.com.repositories.EnderecoRepository;
import wearecarnival.com.repositories.EventosRepository;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class EventosServiceImplements implements EventosService {

    @Autowired
    private EventosRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Eventos save(Eventos eventos){
        Endereco endereco = eventos.getEndereco();
        eventos.setEndereco(enderecoRepository.save(endereco));
        return repository.save(eventos);
    }

    @Override
    public Eventos update(UUID id, Eventos eventos) {
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
    public List<Eventos>findByDay(String dayOfWeek) {
        return repository.findByDay(dayOfWeek);
    }

    @Override
    public List<Eventos> findByFavorite(boolean valor) {
        return repository.findByFavorite(valor);
    }

    @Override
    public List<Eventos> findByEventName(String nome) { return repository.findByEventName(nome); }
    @Override
    public Eventos findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Eventos> findByCategory(String categoria) {return repository.findByCategory(categoria); }
    @Override
    public List<Eventos> findByCity(String nomeCidade) {return repository.findByCity(nomeCidade);}
    public List<Eventos> procurarEventosPorDiaECidade(Integer diaInt, String cidade) { return repository.procurarEventosPorDiaECidade(diaInt, cidade); }
    @Override
    public List<Eventos> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Eventos> buscarDiaInt(Integer diaInt) {return repository.buscarDiaInt(diaInt);}
}
