package wimbledon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import wimbledon.modelo.Torneo;

@Repository
@Scope("singleton")
public class TorneoDAO implements ITorneoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void crear(Torneo entity) {
		entityManager.persist(entity);
	}

	@Override
	public void actualizar(Torneo entity) {
		entityManager.merge(entity);
	}

	@Override
	public void eliminar(Torneo entity) {
		entityManager.remove(entity);
	}

	@Override
	public Torneo consultarPorId(long idEntity) {
		return entityManager.find(Torneo.class, idEntity);
	}

	@Override
	public List<Torneo> consultarTodos() {
		String jpql = "SELECT Torneo FROM Torneo Torneo";
		return entityManager.createQuery(jpql).getResultList();
	}

}
