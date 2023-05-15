package wearecarnival.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import wearecarnival.com.models.Eventos;
import wearecarnival.com.repositories.EventosRepository;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EventosServiceImplements implements EventosService {

    @Autowired
    private EventosRepository repository;

    public Eventos save(Eventos eventos){

        return repository.save(eventos);
    }

    @Override
    public Eventos update(Eventos eventos) {
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
    public List<Eventos> findAll() {
        return repository.findAll();
    }
}
