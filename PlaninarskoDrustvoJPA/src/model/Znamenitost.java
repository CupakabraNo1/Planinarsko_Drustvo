package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Znamenitost database table.
 * 
 */
@Entity
@NamedQuery(name="Znamenitost.findAll", query="SELECT z FROM Znamenitost z")
public class Znamenitost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idZnamenitost;

	private String opis;

	private String tip;

	//bi-directional many-to-one association to Slika
	@OneToMany(mappedBy="znamenitost")
	private List<Slika> slikas;

	//bi-directional many-to-one association to Termin_znamenitost
	@OneToMany(mappedBy="znamenitost")
	private List<Termin_znamenitost> terminZnamenitosts;

	//bi-directional many-to-one association to Planinarska_staza
	@ManyToOne
	@JoinColumn(name="Planinarska_staza_idStaza")
	private Planinarska_staza planinarskaStaza;

	public Znamenitost() {
	}

	public int getIdZnamenitost() {
		return this.idZnamenitost;
	}

	public void setIdZnamenitost(int idZnamenitost) {
		this.idZnamenitost = idZnamenitost;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public List<Slika> getSlikas() {
		return this.slikas;
	}

	public void setSlikas(List<Slika> slikas) {
		this.slikas = slikas;
	}

	public Slika addSlika(Slika slika) {
		getSlikas().add(slika);
		slika.setZnamenitost(this);

		return slika;
	}

	public Slika removeSlika(Slika slika) {
		getSlikas().remove(slika);
		slika.setZnamenitost(null);

		return slika;
	}

	public List<Termin_znamenitost> getTerminZnamenitosts() {
		return this.terminZnamenitosts;
	}

	public void setTerminZnamenitosts(List<Termin_znamenitost> terminZnamenitosts) {
		this.terminZnamenitosts = terminZnamenitosts;
	}

	public Termin_znamenitost addTerminZnamenitost(Termin_znamenitost terminZnamenitost) {
		getTerminZnamenitosts().add(terminZnamenitost);
		terminZnamenitost.setZnamenitost(this);

		return terminZnamenitost;
	}

	public Termin_znamenitost removeTerminZnamenitost(Termin_znamenitost terminZnamenitost) {
		getTerminZnamenitosts().remove(terminZnamenitost);
		terminZnamenitost.setZnamenitost(null);

		return terminZnamenitost;
	}

	public Planinarska_staza getPlaninarskaStaza() {
		return this.planinarskaStaza;
	}

	public void setPlaninarskaStaza(Planinarska_staza planinarskaStaza) {
		this.planinarskaStaza = planinarskaStaza;
	}

}