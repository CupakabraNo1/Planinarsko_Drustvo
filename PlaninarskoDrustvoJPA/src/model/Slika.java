package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Slika database table.
 * 
 */
@Entity
@NamedQuery(name="Slika.findAll", query="SELECT s FROM Slika s")
public class Slika implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSlika;

	@Lob
	private byte[] slika;

	//bi-directional many-to-one association to Izvestaj
	@ManyToOne
	@JoinColumn(name="Izvestaj_idIzvestaj")
	private Izvestaj izvestaj;

	//bi-directional many-to-one association to Znamenitost
	@ManyToOne
	@JoinColumn(name="Znamenitost_idZnamenitost")
	private Znamenitost znamenitost;

	public Slika() {
	}

	public int getIdSlika() {
		return this.idSlika;
	}

	public void setIdSlika(int idSlika) {
		this.idSlika = idSlika;
	}

	public byte[] getSlika() {
		return this.slika;
	}

	public void setSlika(byte[] slika) {
		this.slika = slika;
	}

	public Izvestaj getIzvestaj() {
		return this.izvestaj;
	}

	public void setIzvestaj(Izvestaj izvestaj) {
		this.izvestaj = izvestaj;
	}

	public Znamenitost getZnamenitost() {
		return this.znamenitost;
	}

	public void setZnamenitost(Znamenitost znamenitost) {
		this.znamenitost = znamenitost;
	}

}