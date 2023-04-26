package wearecarnival.com.services;

import wearecarnival.com.models.Eventos;

import java.util.List;
import java.util.UUID;

public interface EventosService {

    Eventos save(Eventos eventos);

    Eventos update(Eventos eventos);

    void delete(UUID id);

    Eventos findById(UUID id);

    List<Eventos> findByDay(int dayOfWeek);

    List<Eventos> findByFavorite(boolean valor);

    List<Eventos> findAll();
}
