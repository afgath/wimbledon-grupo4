package wimbledon.logica;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import wimbledon.dao.ICanchaDAO;
import wimbledon.modelo.Cancha;

@Service("canchaLogica")
@Scope("singleton")
public class CanchaLogica implements ICanchaLogica {
	
	@Autowired
	private ICanchaDAO canchaDAO;

	public List<Cancha> consultarCanchasDisponibles(Date fecha){
		return canchaDAO.consultarCanchasDisponibles(fecha);
	}
	
	@Override
	public Cancha getCanchaById(long cancha) {
		return canchaDAO.consultarPorId(cancha);
	}
}
