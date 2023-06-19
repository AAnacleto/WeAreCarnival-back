package wearecarnival.com.repositories;

import wearecarnival.com.models.Eventos;

import java.util.List;
import java.util.UUID;

public interface EventosRepository {

    Eventos save(Eventos eventos);

    Eventos update(Eventos eventos);

    void delete(UUID id);

    Eventos findById(UUID id);

    List<Eventos> findByDay(String dayOfWeek);

    List<Eventos> findByFavorite(boolean valor);

    List<Eventos> findByEventName(String nome);

    Eventos findByName(String nome);
    List<Eventos> findByCity(String nomeCidade);

    List<Eventos> findByCategory(String categoria);

    List<Eventos> procurarEventosPorDiaECidade(Integer diaInt, String cidade);
    List<Eventos> findAll();
}
