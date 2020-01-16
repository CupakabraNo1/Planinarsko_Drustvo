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

	@Column(name="Rezervacija_idRezervacija")
	private int rezervacija_idRezervacija;

	@Column(name="Termin_idTermin")
	private int termin_idTermin;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="poseta")
	private List<Komentar> komentars;

	public Poseta() {
	}

	public int getIdPoseta() {
		return this.idPoseta;
	}

	public void setIdPoseta(int idPoseta) {
		this.idPoseta = idPoseta;
	}

	public int getRezervacija_idRezervacija() {
		return this.rezervacija_idRezervacija;
	}

	public void setRezervacija_idRezervacija(int rezervacija_idRezervacija) {
		this.rezervacija_idRezervacija = rezervacija_idRezervacija;
	}

	public int getTermin_idTermin() {
		return this.termin_idTermin;
	}

	public void setTermin_idTermin(int termin_idTermin) {
		this.termin_idTermin = termin_idTermin;
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

}