package wimbledon.dao;

import java.util.List;

import wimbledon.modelo.Ronda;

public interface IRondaDAO {

	public void crear(Ronda entity);
	
	public void actualizar(Ronda entity);
	
	public void eliminar(Ronda entity);

	public Ronda consultarPorId(long idEntity);
	
	public List<Ronda> consultarTodos();
}
