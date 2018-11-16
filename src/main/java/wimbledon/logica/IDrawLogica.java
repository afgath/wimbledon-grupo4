package wimbledon.logica;

import java.util.List;

import wimbledon.modelo.Draw;

public interface IDrawLogica {
	
	public List<Draw> consultarDraw(long torneo) throws Exception;
}
