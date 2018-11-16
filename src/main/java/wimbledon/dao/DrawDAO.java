package wimbledon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import wimbledon.modelo.Draw;

@Repository
@Scope("singleton")
public class DrawDAO implements IDrawDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void crear(Draw entity) {
		entityManager.persist(entity);
	}

	@Override
	public void actualizar(Draw entity) {
		entityManager.merge(entity);
	}

	@Override
	public void eliminar(Draw entity) {
		entityManager.remove(entity);
	}

	@Override
	public Draw consultarPorId(long idEntity) {
		return entityManager.find(Draw.class, idEntity);
	}

	@Override
	public List<Draw> consultarTodos(long torneo) {
		String jpql = "SELECT draw FROM Draw draw WHERE draw.torneo="+torneo;
		return entityManager.createQuery(jpql).getResultList();
	}

}
