package wearecarnival.com.repositories;

import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import wearecarnival.com.models.Endereco;

@Repository
public class EnderecoRepositoryImplements extends AbstractRepository<Endereco, Long> implements EnderecoRepository{


    @Override
    public Endereco findByName(String nomeRua) {
        try {
            return getEntityManager().createQuery("select user FROM Endereco user WHERE user.nomeRua = '" + nomeRua +
                    "'", Endereco.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
