package wimbledon.delegado;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import wimbledon.modelo.Cancha;
import wimbledon.modelo.Draw;
import wimbledon.modelo.Partido;

public interface IDelegadoDeNegocio {

	//CU25
	public List<Draw> consultarDraw(long torneo) throws Exception;
	
	//CU8
	public List<Partido> consultarPartidosSinCancha(long draw);
	public Partido consultarPartidosPorId(long partido);
	public List<Cancha> consultarCanchasDisponibles(Date fecha);
	public Cancha consultarCanchaPorId(long cancha);
	public void asignarcancha(Partido partido) throws ParseException;
}
