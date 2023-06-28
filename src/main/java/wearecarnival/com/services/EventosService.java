package wearecarnival.com.services;

import wearecarnival.com.models.Eventos;

import java.util.List;
import java.util.UUID;

public interface EventosService {

    Eventos save(Eventos eventos);

    Eventos update(UUID id, Eventos eventos);

    void delete(UUID id);

    Eventos findById(UUID id);

    List<Eventos> findByDay(String dayOfWeek);

    List<Eventos> findByFavorite(boolean valor);
    List<Eventos> findByEventName(String nome);
    List<Eventos> findByCategory(String categoria);
    Eventos findByName(String name);

    List<Eventos> findByCity(String nomeCidade);
    List<Eventos> procurarEventosPorDiaECidade(Integer diaInt, String cidade);
    List<Eventos> findAll();

    List<Eventos> buscarDiaInt(Integer diaInt);
}
