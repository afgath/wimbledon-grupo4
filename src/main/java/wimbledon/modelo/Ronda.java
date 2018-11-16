package wimbledon.modelo;
// Generated 15/11/2018 09:04:19 PM by Hibernate Tools 4.3.5.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Ronda generated by hbm2java
 */
@Entity
@Table(name = "ronda", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = { "numeroronda",
		"draw" }))
public class Ronda implements java.io.Serializable {

	private long idronda;
	private Draw draw;
	private int numeroronda;
	private BigDecimal premiodinero;
	private int premiopuntos;
	private Set<Partido> partidos = new HashSet<Partido>(0);

	public Ronda() {
	}

	public Ronda(long idronda, int numeroronda, BigDecimal premiodinero, int premiopuntos) {
		this.idronda = idronda;
		this.numeroronda = numeroronda;
		this.premiodinero = premiodinero;
		this.premiopuntos = premiopuntos;
	}

	public Ronda(long idronda, Draw draw, int numeroronda, BigDecimal premiodinero, int premiopuntos,
			Set<Partido> partidos) {
		this.idronda = idronda;
		this.draw = draw;
		this.numeroronda = numeroronda;
		this.premiodinero = premiodinero;
		this.premiopuntos = premiopuntos;
		this.partidos = partidos;
	}

	@Id

	@Column(name = "idronda", unique = true, nullable = false)
	public long getIdronda() {
		return this.idronda;
	}

	public void setIdronda(long idronda) {
		this.idronda = idronda;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "draw")
	public Draw getDraw() {
		return this.draw;
	}

	public void setDraw(Draw draw) {
		this.draw = draw;
	}

	@Column(name = "numeroronda", nullable = false)
	public int getNumeroronda() {
		return this.numeroronda;
	}

	public void setNumeroronda(int numeroronda) {
		this.numeroronda = numeroronda;
	}

	@Column(name = "premiodinero", nullable = false, precision = 131089, scale = 0)
	public BigDecimal getPremiodinero() {
		return this.premiodinero;
	}

	public void setPremiodinero(BigDecimal premiodinero) {
		this.premiodinero = premiodinero;
	}

	@Column(name = "premiopuntos", nullable = false)
	public int getPremiopuntos() {
		return this.premiopuntos;
	}

	public void setPremiopuntos(int premiopuntos) {
		this.premiopuntos = premiopuntos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ronda")
	public Set<Partido> getPartidos() {
		return this.partidos;
	}

	public void setPartidos(Set<Partido> partidos) {
		this.partidos = partidos;
	}

}
