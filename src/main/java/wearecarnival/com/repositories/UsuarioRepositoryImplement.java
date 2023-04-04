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

}
