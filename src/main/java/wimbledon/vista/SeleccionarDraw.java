package wimbledon.vista;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wimbledon.delegado.IDelegadoDeNegocio;
import wimbledon.modelo.Draw;
import wimbledon.utilidades.FacesUtils;

@ViewScoped
@ManagedBean(name = "seleccionarDraw")
public class SeleccionarDraw {

	private long torneo = 1;

	private List<Draw> losDraws;

	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	private static final Logger log = LoggerFactory.getLogger(SeleccionarDraw.class);

	public boolean contains(String text) throws Exception {

		getLosDraws();
		for (Draw draw : this.losDraws) {
			if (draw.getTipodraw().getNombre().toLowerCase().equals(text.toLowerCase())) {
				return true;
			}
		}
		return false;

	}

	public String redireccionar(String opcion) throws Exception {
		log.info("redireccionar action");
		try {
			FacesUtils.putinSession("draw", opcion);
		} catch (Exception e) {
			log.info(e.getMessage());
			return "";
		}
		return "distribuirJugadores";
	}
	
	public List<Draw> getLosDraws() throws Exception {
		if (this.losDraws == null) {
			this.losDraws = delegadoDeNegocio.consultarDraw(this.torneo);
			log.info(losDraws.toString());
			;
		}
		return losDraws;
	}

	public void setLosDraws(List<Draw> losDraws) {
		this.losDraws = losDraws;
	}

	public long getTorneo() {
		return torneo;
	}

	public void setTorneo(long torneo) {
		this.torneo = torneo;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
}
