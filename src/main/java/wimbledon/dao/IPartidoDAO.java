package wimbledon.dao;

import java.util.List;

import wimbledon.modelo.Partido;

public interface IPartidoDAO {

	public void crear(Partido entity);
	
	public void actualizar(Partido entity);
	
	public void eliminar(Partido entity);

	public Partido consultarPorId(long idEntity);
	
	public List<Partido> consultarPartidosSinCancha(long draw);
}
