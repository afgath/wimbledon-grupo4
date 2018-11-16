package wimbledon.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wimbledon.dao.IDrawDAO;
import wimbledon.modelo.Draw;

@Service("drawLogica")
@Scope("singleton")
public class DrawLogica implements IDrawLogica {
	
	@Autowired
	private IDrawDAO drawDao;

	@Override
	@Transactional(readOnly=true)
	public List<Draw> consultarDraw(long torneo) throws Exception{
		if(torneo == 0) {
			throw new Exception("Escoga un torneo valido");
		}
		
		List<Draw> draws = drawDao.consultarTodos(torneo);
		
		if(draws == null) {
			throw new Exception("No existen draws para el torneo seleccionado");
		}
		
		draws.forEach(draw-> {
			draw.getTipodraw().getNombre();
		}
		);
		
		return draws;
	}
}
