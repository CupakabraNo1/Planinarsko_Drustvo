package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Izvestaj database table.
 * 
 */
@Entity
@NamedQuery(name="Izvestaj.findAll", query="SELECT i FROM Izvestaj i")
public class Izvestaj implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idIzvestaj;

	private String tekst;

	//bi-directional many-to-one association to Rezervacija
	@ManyToOne
	@JoinColumn(name="Rezervacija_idRezervacija")
	private Rezervacija rezervacija;

	//bi-directional many-to-one association to Slika
	@OneToMany(mappedBy="izvestaj")
	private List<Slika> slikas;

	public Izvestaj() {
	}

	public int getIdIzvestaj() {
		return this.idIzvestaj;
	}

	public void setIdIzvestaj(int idIzvestaj) {
		this.idIzvestaj = idIzvestaj;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Rezervacija getRezervacija() {
		return this.rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	public List<Slika> getSlikas() {
		return this.slikas;
	}

	public void setSlikas(List<Slika> slikas) {
		this.slikas = slikas;
	}

	public Slika addSlika(Slika slika) {
		getSlikas().add(slika);
		slika.setIzvestaj(this);

		return slika;
	}

	public Slika removeSlika(Slika slika) {
		getSlikas().remove(slika);
		slika.setIzvestaj(null);

		return slika;
	}

}