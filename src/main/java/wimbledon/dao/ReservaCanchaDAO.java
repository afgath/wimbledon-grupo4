package wimbledon.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import wimbledon.modelo.Reservacancha;

@Repository
@Scope("singleton")
public class ReservaCanchaDAO implements IReservaCanchaDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void crear(Reservacancha entity) {
		entityManager.persist(entity);
		
	}

}
