package wimbledon.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceContext;

import wimbledon.modelo.Cancha;

public interface ICanchaDAO {

	public void crear(Cancha entity);
	
	public void actualizar(Cancha entity);
	
	public void eliminar(Cancha entity);

	public Cancha consultarPorId(long idEntity);
	
	public List<Cancha> consultarTodos();

	public List<Cancha> consultarCanchasDisponibles(Date fecha);

}
