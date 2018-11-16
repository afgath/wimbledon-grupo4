package wimbledon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import wimbledon.modelo.Ronda;

@Repository
@Scope("singleton")
public class RondaDAO implements IRondaDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void crear(Ronda entity) {
		entityManager.persist(entity);
	}

	@Override
	public void actualizar(Ronda entity) {
		entityManager.merge(entity);
	}

	@Override
	public void eliminar(Ronda entity) {
		entityManager.remove(entity);
	}

	@Override
	public Ronda consultarPorId(long idEntity) {
		return entityManager.find(Ronda.class, idEntity);
	}

	@Override
	public List<Ronda> consultarTodos() {
		String jpql = "SELECT Ronda FROM Ronda Ronda";
		return entityManager.createQuery(jpql).getResultList();
	}

}
