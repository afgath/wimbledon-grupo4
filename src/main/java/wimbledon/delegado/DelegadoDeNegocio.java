package wimbledon.delegado;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wimbledon.dao.IPartidoDAO;
import wimbledon.logica.ICanchaLogica;
import wimbledon.logica.IDrawLogica;
import wimbledon.logica.IPartidoLogica;
import wimbledon.modelo.Cancha;
import wimbledon.modelo.Draw;
import wimbledon.modelo.Partido;
import wimbledon.vista.SeleccionarDraw;

@Component("delegadoDeNegocio")
@Scope("singleton")
public class DelegadoDeNegocio implements IDelegadoDeNegocio {
	
	@Autowired
	private IDrawLogica drawLogica;
	
	@Autowired
	private IPartidoLogica partidoLogica;
	
	@Autowired
	private ICanchaLogica canchaLogica;

	private static final Logger log = LoggerFactory.getLogger(DelegadoDeNegocio.class);
	
	//CU25
	public List<Draw> consultarDraw(long torneo) throws Exception{
		log.info("qwerty");
		return drawLogica.consultarDraw(torneo);
	}
	
	//cu8
	public List<Partido> consultarPartidosSinCancha(long draw){
		return partidoLogica.consultarPartidosSinCancha(draw);
	}
	
	public Partido consultarPartidosPorId(long partido) {
		return partidoLogica.consultarPartidosPorId(partido);
	}
	
	public List<Cancha> consultarCanchasDisponibles(Date fecha){
		return canchaLogica.consultarCanchasDisponibles(fecha);
	}
	
	public Cancha consultarCanchaPorId(long cancha) {
		return canchaLogica.getCanchaById(cancha);
	}
	
	public void asignarcancha(Partido partido) throws ParseException {
		partidoLogica.asignarcancha(partido);
	}
}
