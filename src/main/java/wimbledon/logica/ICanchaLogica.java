package wimbledon.logica;

import java.util.Date;
import java.util.List;

import wimbledon.modelo.Cancha;

public interface ICanchaLogica {
	
	public List<Cancha> consultarCanchasDisponibles(Date fecha);
	public Cancha getCanchaById(long cancha);
}
