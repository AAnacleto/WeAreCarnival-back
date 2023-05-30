package wearecarnival.com.repositories;

import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import wearecarnival.com.models.Eventos;


import java.util.List;

@Repository
public class EventosRepositoryImplements extends AbstractRepository<Eventos, Long> implements EventosRepository {

    @Override
    public List<Eventos> findByDay(int dayOfWeek) {
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
    public Eventos findByName(String name) {
        try {
            return getEntityManager().createQuery("select e FROM Eventos e WHERE e.nome = '" + name +
                    "'", Eventos.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Eventos> findByCity(String cidade) {
        try {
            return getEntityManager().createQuery(
                    "SELECT e FROM Eventos e JOIN e.endereco end WHERE end.cidade = :cidade", Eventos.class)
                    .setParameter("cidade", cidade)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
