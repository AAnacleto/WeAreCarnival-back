package wearecarnival.com.repositories;

import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import wearecarnival.com.models.Eventos;


import java.util.List;

@Repository
public class EventosRepositoryImplements extends AbstractRepository<Eventos, Long> implements EventosRepository {

    @Override
    public List<Eventos> findByDay(String dayOfWeek) {
        try {
            return getEntityManager()
                    .createQuery("SELECT e FROM Eventos e WHERE e.data = :dayOfWeek", Eventos.class)
                    .setParameter("dayOfWeek", dayOfWeek)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List<Eventos> findByFavorite(boolean valor) {
        try {
            return getEntityManager()
                    .createQuery("SELECT e FROM Eventos e WHERE e.favoritos = :valor", Eventos.class)
                    .setParameter("valor", valor)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    @Override
    public List<Eventos> findByEventName(String nome) {
        try {
            return getEntityManager().createQuery("select e FROM Eventos e WHERE e.nome LIKE CONCAT('%', :nome, '%')", Eventos.class)
                    .setParameter("nome", nome)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Eventos findByName(String nome) {
        try {
            return getEntityManager().createQuery("select e FROM Eventos e WHERE e.nome = '" + nome +
                    "'", Eventos.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Eventos> findByCity(String cidade) {
        try {
            return getEntityManager()
                    .createQuery("SELECT e FROM Eventos e JOIN e.endereco end WHERE end.cidade = :cidade", Eventos.class)
                    .setParameter("cidade", cidade)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Eventos> findByCategory(String categoria) {
        try {
            return getEntityManager().createQuery("SELECT e FROM Eventos e WHERE e.categoria = :categoria", Eventos.class)
                    .setParameter("category", categoria)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    @Override
    public List<Eventos> procurarEventosPorDiaECidade(String dia, String cidade) {
        String jpql = "SELECT e FROM Eventos e WHERE e.diaSemana = :dia AND e.endereco.cidade = :cidade";
        return getEntityManager().createQuery(jpql, Eventos.class)
                .setParameter("dia", dia)
                .setParameter("cidade", cidade)
                .getResultList();
    }
}

