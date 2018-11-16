package wimbledon.dao;

import java.util.List;

import wimbledon.modelo.Draw;

public interface IDrawDAO {

	public void crear(Draw entity);
	
	public void actualizar(Draw entity);
	
	public void eliminar(Draw entity);

	public Draw consultarPorId(long idEntity);
	
	public List<Draw> consultarTodos(long torneo);
}
