package wimbledon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import wimbledon.modelo.Partido;

@Repository
@Scope("singleton")
public class PartidoDAO implements IPartidoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void crear(Partido entity) {
		entityManager.persist(entity);
	}

	@Override
	public void actualizar(Partido entity) {
		entityManager.merge(entity);
	}

	@Override
	public void eliminar(Partido entity) {
		entityManager.remove(entity);
	}

	@Override
	public Partido consultarPorId(long idEntity) {
		return entityManager.find(Partido.class, idEntity);
	}

	@Override
	public List<Partido> consultarPartidosSinCancha(long draw) {
		String jpql = "";
		if(draw == 0) {
			jpql = "SELECT partido FROM Partido partido WHERE partido.cancha=1 OR partido.cancha is null";
		}else {
			jpql = "SELECT partido FROM Partido partido WHERE partido.cancha=1 OR partido.cancha is null AND partido.draw="+draw;
		}
		
		return entityManager.createQuery(jpql).getResultList();
	}

}
