package wimbledon.vista;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wimbledon.delegado.IDelegadoDeNegocio;
import wimbledon.modelo.Cancha;
import wimbledon.modelo.Partido;
import wimbledon.utilidades.FacesUtils;

@ViewScoped
@ManagedBean(name = "listadoPartidos")
public class ListadoPartidos {

	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Partido> losPartidos;
	
	private static final Logger log = LoggerFactory.getLogger(ListadoPartidos.class);
	
	private Partido partidoSeleccionado;

	private OutputLabel titulo;
	private OutputLabel id;
	
	private SelectOneMenu somCanchasDiponibles;
	private List<SelectItem> lasCanchasDisponibles;

	public String mostrarDialog(long partido) {
		
		this.partidoSeleccionado = delegadoDeNegocio.consultarPartidosPorId(partido);
		
		if(partidoSeleccionado!=null) {
			log.info("mostrarDialog");
		}
			
		titulo.setValue(partidoSeleccionado.getJugadorByJugador1().getNombre() + 
				" vs " +
				partidoSeleccionado.getJugadorByJugador2().getNombre());
		
		id.setValue(partidoSeleccionado.getIdpartido());
		
		somCanchasDiponibles.setValue( new Long(1));
		
		RequestContext.getCurrentInstance().execute("PF('dlgGestionPartido').show()");
		return "";
	}
	
	public String ActualizarAction() throws ParseException {
		
		log.info("----------------------------------------------------------------------------------------"+id.getValue().toString());
		if(id.getValue().toString()!=null && !id.getValue().toString().equals("0")) {
			log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			Cancha cancha = delegadoDeNegocio.consultarCanchaPorId(Long.parseLong(somCanchasDiponibles.getValue().toString()));
			
			Partido partido = delegadoDeNegocio.consultarPartidosPorId(Long.parseLong(id.getValue().toString()));
			
			partido.setCancha(cancha);
			
			delegadoDeNegocio.asignarcancha(partido);
			
			losPartidos = null;
			
			FacesUtils.addInfoMessage("Se asigno la cancha");
		}
		return "";
	}
	
	public String action_clear() {
		
		partidoSeleccionado = null;
		
		titulo.setValue(null);
		somCanchasDiponibles.resetValue();
		
		losPartidos = null;

		return "";
	}
	
	public String actionCerrarDialog() {
		
		action_clear();
		
		return "";
	}
	
	public void listenerPartidoSeleccionado() {
		Partido partido = delegadoDeNegocio.consultarPartidosPorId(partidoSeleccionado.getIdpartido());
	}
	
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

	public List<Partido> getLosPartidos() {
		if(losPartidos==null) {
			this.losPartidos = delegadoDeNegocio.consultarPartidosSinCancha(0);
		}
		return losPartidos;
	}

	public void setLosPartidos(List<Partido> losPartidos) {
		this.losPartidos = losPartidos;
	}

	public Partido getPartidoSeleccionado() {
		return this.partidoSeleccionado;
	}

	public void setPartidoSeleccionado(Partido partidoSeleccionado) {
		this.partidoSeleccionado = partidoSeleccionado;
	}

	public OutputLabel getTitulo() {
		return titulo;
	}

	public void setTitulo(OutputLabel titulo) {
		this.titulo = titulo;
	}

	public SelectOneMenu getSomCanchasDiponibles() {
		return somCanchasDiponibles;
	}

	public void setSomCanchasDiponibles(SelectOneMenu somCanchasDiponibles) {
		this.somCanchasDiponibles = somCanchasDiponibles;
	}

	public List<SelectItem> getLasCanchasDisponibles() {
		if(lasCanchasDisponibles== null && partidoSeleccionado != null) {
			log.info("getCanchas");
			lasCanchasDisponibles = new ArrayList<SelectItem>();
			List<Cancha> lasCanchas = delegadoDeNegocio.consultarCanchasDisponibles(this.partidoSeleccionado.getFecha());
			for(Cancha cancha:lasCanchas) {
				lasCanchasDisponibles.add(new SelectItem(cancha.getIdcancha(), cancha.getNombre()));
			}
			
		}
		return lasCanchasDisponibles;
	}

	public void setLasCanchasDisponibles(List<SelectItem> lasCanchasDisponibles) {
		this.lasCanchasDisponibles = lasCanchasDisponibles;
	}

	public OutputLabel getId() {
		return id;
	}

	public void setId(OutputLabel id) {
		this.id = id;
	}
	
	
}
