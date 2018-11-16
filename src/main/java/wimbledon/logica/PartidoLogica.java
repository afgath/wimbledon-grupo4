package wimbledon.logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wimbledon.dao.IDrawDAO;
import wimbledon.dao.IEquipoDAO;
import wimbledon.dao.IJugadorDAO;
import wimbledon.dao.IPartidoDAO;
import wimbledon.dao.IReservaCanchaDAO;
import wimbledon.modelo.Draw;
import wimbledon.modelo.Equipo;
import wimbledon.modelo.Jugador;
import wimbledon.modelo.Partido;
import wimbledon.modelo.Reservacancha;
import wimbledon.modelo.Tipodraw;

@Service("partidoLogica")
@Scope("singleton")
public class PartidoLogica implements IPartidoLogica {

	@Autowired
	private IPartidoDAO partidoDAO;
	
	@Autowired
	private IJugadorDAO jugadorDAO;
	
	@Autowired
	private IEquipoDAO equipoDAO;
	
	@Autowired
	private IDrawDAO drawDAO; 
	
	@Autowired
	private IReservaCanchaDAO reservaDAO; 
	
	@Override
	@Transactional(readOnly=true)
	public List<Partido> consultarPartidosSinCancha(long draw){
		List<Partido> partidos = partidoDAO.consultarPartidosSinCancha(draw);
		
		partidos.forEach(partido->{
			
			Draw draws = drawDAO.consultarPorId(partido.getDraw().getIddraw());
			Tipodraw tipoDraw = draws.getTipodraw();
			
			draws.setTipodraw(tipoDraw);
			
			partido.setDraw(draws);
			
			
			
			if(partido.getJugadorByJugador1() != null) {
				
				Jugador jugador1 = jugadorDAO.consultarPorId(partido.getJugadorByJugador1().getIdjugador());
				
				partido.setJugadorByJugador1(jugador1);
			}
			if(partido.getJugadorByJugador2() != null) {
				
				Jugador jugador2 = jugadorDAO.consultarPorId(partido.getJugadorByJugador2().getIdjugador());
				
				partido.setJugadorByJugador2(jugador2);
			}
			if(partido.getEquipoByEquipo1() != null) {
				
				Equipo equipo = equipoDAO.consultarPorId(partido.getEquipoByEquipo1().getIdequipo());
				Jugador jugador1 = jugadorDAO.consultarPorId(equipo.getJugadorByJugador1().getIdjugador());
				Jugador jugador2 = jugadorDAO.consultarPorId(equipo.getJugadorByJugador2().getIdjugador());
				
				equipo.setJugadorByJugador1(jugador1);
				equipo.setJugadorByJugador2(jugador2);
				
				partido.setEquipoByEquipo1(equipo);
				
			}
			if(partido.getEquipoByEquipo2() != null) {
				Equipo equipo = equipoDAO.consultarPorId(partido.getEquipoByEquipo2().getIdequipo());
				Jugador jugador1 = jugadorDAO.consultarPorId(equipo.getJugadorByJugador1().getIdjugador());
				Jugador jugador2 = jugadorDAO.consultarPorId(equipo.getJugadorByJugador2().getIdjugador());
				
				equipo.setJugadorByJugador1(jugador1);
				equipo.setJugadorByJugador2(jugador2);
				
				partido.setEquipoByEquipo2(equipo);
			}
			
			
		});
		return partidos;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Partido consultarPartidosPorId(long partido) {
		
		Partido rPartido = partidoDAO.consultarPorId(partido);
		
		rPartido.getJugadorByJugador1().getNombre();
		rPartido.getJugadorByJugador1().getApellido();
		
		rPartido.getJugadorByJugador2().getNombre();
		rPartido.getJugadorByJugador2().getApellido();
		
		return rPartido;
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void asignarcancha(Partido partido) throws ParseException {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(partido.getFecha()); // Configuramos la fecha que se recibe
		calendar.add(Calendar.HOUR, -3);  // numero de días a añadir, o restar en caso de días<0
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(partido.getFecha()); // Configuramos la fecha que se recibe
		calendar1.add(Calendar.HOUR, 4);  // numero de días a añadir, o restar en caso de días<01
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String fi = sdf.format(calendar.getTime());
		String ff = sdf.format(calendar1.getTime());
		
		Reservacancha reserva = new Reservacancha();
		
		reserva.setCancha(partido.getCancha());
		reserva.setFechafin(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ff));
		reserva.setFechainicio(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fi));
		reserva.setPartido(partido);
		
		partidoDAO.actualizar(partido);
		
		reservaDAO.crear(reserva);
	}
}
