package wimbledon.modelo;
// Generated 15/11/2018 09:04:19 PM by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tipodraw generated by hbm2java
 */
@Entity
@Table(name = "tipodraw", schema = "public")
public class Tipodraw implements java.io.Serializable {

	private long idtipo;
	private String nombre;
	private Set<Draw> draws = new HashSet<Draw>(0);

	public Tipodraw() {
	}

	public Tipodraw(long idtipo, String nombre) {
		this.idtipo = idtipo;
		this.nombre = nombre;
	}

	public Tipodraw(long idtipo, String nombre, Set<Draw> draws) {
		this.idtipo = idtipo;
		this.nombre = nombre;
		this.draws = draws;
	}

	@Id

	@Column(name = "idtipo", unique = true, nullable = false)
	public long getIdtipo() {
		return this.idtipo;
	}

	public void setIdtipo(long idtipo) {
		this.idtipo = idtipo;
	}

	@Column(name = "nombre", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipodraw")
	public Set<Draw> getDraws() {
		return this.draws;
	}

	public void setDraws(Set<Draw> draws) {
		this.draws = draws;
	}

}