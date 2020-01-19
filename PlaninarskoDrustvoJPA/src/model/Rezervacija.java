package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Rezervacija database table.
 * 
 */
@Entity
@NamedQuery(name="Rezervacija.findAll", query="SELECT r FROM Rezervacija r")
public class Rezervacija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRezervacija;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="do")
	private Date do_;

	@Temporal(TemporalType.TIMESTAMP)
	private Date od;

	//bi-directional many-to-one association to Izvestaj
	@OneToMany(mappedBy="rezervacija")
	private List<Izvestaj> izvestajs;

	//bi-directional many-to-one association to Poseta
	@OneToMany(mappedBy="rezervacija")
	private List<Poseta> posetas;

	//bi-directional many-to-one association to Dom
	@ManyToOne
	@JoinColumn(name="Dom_idDom")
	private Dom dom;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="Korisnik_idKorisnik")
	private Korisnik korisnik;

	public Rezervacija() {
	}

	public int getIdRezervacija() {
		return this.idRezervacija;
	}

	public void setIdRezervacija(int idRezervacija) {
		this.idRezervacija = idRezervacija;
	}

	public Date getDo_() {
		return this.do_;
	}

	public void setDo_(Date do_) {
		this.do_ = do_;
	}

	public Date getOd() {
		return this.od;
	}

	public void setOd(Date od) {
		this.od = od;
	}

	public List<Izvestaj> getIzvestajs() {
		return this.izvestajs;
	}

	public void setIzvestajs(List<Izvestaj> izvestajs) {
		this.izvestajs = izvestajs;
	}

	public Izvestaj addIzvestaj(Izvestaj izvestaj) {
		getIzvestajs().add(izvestaj);
		izvestaj.setRezervacija(this);

		return izvestaj;
	}

	public Izvestaj removeIzvestaj(Izvestaj izvestaj) {
		getIzvestajs().remove(izvestaj);
		izvestaj.setRezervacija(null);

		return izvestaj;
	}

	public List<Poseta> getPosetas() {
		return this.posetas;
	}

	public void setPosetas(List<Poseta> posetas) {
		this.posetas = posetas;
	}

	public Poseta addPoseta(Poseta poseta) {
		getPosetas().add(poseta);
		poseta.setRezervacija(this);

		return poseta;
	}

	public Poseta removePoseta(Poseta poseta) {
		getPosetas().remove(poseta);
		poseta.setRezervacija(null);

		return poseta;
	}

	public Dom getDom() {
		return this.dom;
	}

	public void setDom(Dom dom) {
		this.dom = dom;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}