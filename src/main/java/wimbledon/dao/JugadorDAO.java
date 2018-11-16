package wimbledon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import wimbledon.modelo.Jugador;

@Repository
@Scope("singleton")
public class JugadorDAO implements IJugadorDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void crear(Jugador entity) {
		entityManager.persist(entity);
	}

	@Override
	public void actualizar(Jugador entity) {
		entityManager.merge(entity);
	}

	@Override
	public void eliminar(Jugador entity) {
		entityManager.remove(entity);
	}

	@Override
	public Jugador consultarPorId(long idEntity) {
		return entityManager.find(Jugador.class, idEntity);
	}

	@Override
	public List<Jugador> consultarTodos() {
		String jpql = "SELECT Jugador FROM Jugador Jugador";
		return entityManager.createQuery(jpql).getResultList();
	}
}
