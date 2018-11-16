package wimbledon.dao;

import java.util.List;

import wimbledon.modelo.Equipo;

public interface IEquipoDAO {
	
	public void crear(Equipo entity);
	
	public void actualizar(Equipo entity);
	
	public void eliminar(Equipo entity);

	public Equipo consultarPorId(long idEntity);
	
	public List<Equipo> consultarTodos();
}
