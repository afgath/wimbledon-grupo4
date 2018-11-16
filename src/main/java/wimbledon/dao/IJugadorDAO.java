package wimbledon.dao;

import java.util.List;

import wimbledon.modelo.Jugador;

public interface IJugadorDAO {

	public void crear(Jugador entity);
	
	public void actualizar(Jugador entity);
	
	public void eliminar(Jugador entity);

	public Jugador consultarPorId(long idEntity);
	
	public List<Jugador> consultarTodos();
}
