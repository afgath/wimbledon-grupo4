package wimbledon.vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wimbledon.delegado.IDelegadoDeNegocio;

@ViewScoped
@ManagedBean(name = "distribuirJugadores")
public class DistribuirJugadores {
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	private static final Logger log = LoggerFactory.getLogger(DistribuirJugadores.class);
	
	public String distribuirJugadores() {
		return "";
	}
}
