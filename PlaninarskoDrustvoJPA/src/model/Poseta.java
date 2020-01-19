package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Poseta database table.
 * 
 */
@Entity
@NamedQuery(name="Poseta.findAll", query="SELECT p FROM Poseta p")
public class Poseta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPoseta;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="poseta")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Rezervacija
	@ManyToOne
	@JoinColumn(name="Rezervacija_idRezervacija")
	private Rezervacija rezervacija;

	//bi-directional many-to-one association to Termin_znamenitost
	@ManyToOne
	@JoinColumn(name="Termin_idTermin")
	private Termin_znamenitost terminZnamenitost;

	public Poseta() {
	}

	public int getIdPoseta() {
		return this.idPoseta;
	}

	public void setIdPoseta(int idPoseta) {
		this.idPoseta = idPoseta;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setPoseta(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setPoseta(null);

		return komentar;
	}

	public Rezervacija getRezervacija() {
		return this.rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	public Termin_znamenitost getTerminZnamenitost() {
		return this.terminZnamenitost;
	}

	public void setTerminZnamenitost(Termin_znamenitost terminZnamenitost) {
		this.terminZnamenitost = terminZnamenitost;
	}

}