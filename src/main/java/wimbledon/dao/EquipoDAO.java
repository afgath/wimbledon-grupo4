package wimbledon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import wimbledon.modelo.Equipo;

@Repository
@Scope("singleton")
public class EquipoDAO implements IEquipoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void crear(Equipo entity) {
		entityManager.persist(entity);
	}

	@Override
	public void actualizar(Equipo entity) {
		entityManager.merge(entity);
	}

	@Override
	public void eliminar(Equipo entity) {
		entityManager.remove(entity);
	}

	@Override
	public Equipo consultarPorId(long idEntity) {
		return entityManager.find(Equipo.class, idEntity);
	}

	@Override
	public List<Equipo> consultarTodos() {
		String jpql = "SELECT Equipo FROM Equipo Equipo";
		return entityManager.createQuery(jpql).getResultList();
	}

}
