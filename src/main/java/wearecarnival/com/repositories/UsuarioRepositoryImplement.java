package wearecarnival.com.repositories;

import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import wearecarnival.com.models.Usuario;

@Repository
public class UsuarioRepositoryImplement extends AbstractRepository<Usuario, Long> implements UsuarioRepository {
    @Override
    public Usuario findByName(String nome) {
        try {
            return getEntityManager().createQuery("select user FROM Usuario user WHERE user.nome = '" + nome +
                    "'", Usuario.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Usuario findByEmail(String email) {
        try {
            return getEntityManager().createQuery("SELECT em FROM Usuario em WHERE em.email = '" + email +
                    "'", Usuario.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
