package wimbledon.modelo;
// Generated 15/11/2018 09:04:19 PM by Hibernate Tools 4.3.5.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Drawintegrantes generated by hbm2java
 */
@Entity
@Table(name = "drawintegrantes", schema = "public")
public class Drawintegrantes implements java.io.Serializable {

	private long iddrawintegrantes;
	private Draw draw;
	private Equipo equipo;
	private Jugador jugador;
	private Integer puntos;
	private BigDecimal premio;

	public Drawintegrantes() {
	}

	public Drawintegrantes(long iddrawintegrantes) {
		this.iddrawintegrantes = iddrawintegrantes;
	}

	public Drawintegrantes(long iddrawintegrantes, Draw draw, Equipo equipo, Jugador jugador, Integer puntos,
			BigDecimal premio) {
		this.iddrawintegrantes = iddrawintegrantes;
		this.draw = draw;
		this.equipo = equipo;
		this.jugador = jugador;
		this.puntos = puntos;
		this.premio = premio;
	}

	@Id

	@Column(name = "iddrawintegrantes", unique = true, nullable = false)
	public long getIddrawintegrantes() {
		return this.iddrawintegrantes;
	}

	public void setIddrawintegrantes(long iddrawintegrantes) {
		this.iddrawintegrantes = iddrawintegrantes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "draw")
	public Draw getDraw() {
		return this.draw;
	}

	public void setDraw(Draw draw) {
		this.draw = draw;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipo")
	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jugador")
	public Jugador getJugador() {
		return this.jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Column(name = "puntos")
	public Integer getPuntos() {
		return this.puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	@Column(name = "premio", precision = 131089, scale = 0)
	public BigDecimal getPremio() {
		return this.premio;
	}

	public void setPremio(BigDecimal premio) {
		this.premio = premio;
	}

}
