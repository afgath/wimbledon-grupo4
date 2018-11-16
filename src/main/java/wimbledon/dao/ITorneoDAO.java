package wimbledon.dao;

import java.util.List;

import wimbledon.modelo.Torneo;

public interface ITorneoDAO {

public void crear(Torneo entity);
	
	public void actualizar(Torneo entity);
	
	public void eliminar(Torneo entity);

	public Torneo consultarPorId(long idEntity);
	
	public List<Torneo> consultarTodos();
}
