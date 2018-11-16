package wimbledon.logica;

import java.text.ParseException;
import java.util.List;

import wimbledon.modelo.Partido;

public interface IPartidoLogica {
	
	public List<Partido> consultarPartidosSinCancha(long draw);
	
	public Partido consultarPartidosPorId(long partido);
	
	public void asignarcancha(Partido partido) throws ParseException;
}
