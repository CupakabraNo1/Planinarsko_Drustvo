package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Planinarska_staza database table.
 * 
 */
@Entity
@NamedQuery(name="Planinarska_staza.findAll", query="SELECT p FROM Planinarska_staza p")
public class Planinarska_staza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStaza;

	@Lob
	private byte[] mapa;

	private String naziv;

	private String opis;

	private int tezina;

	//bi-directional many-to-one association to Planina
	@ManyToOne
	@JoinColumn(name="Planina_idPlanina")
	private Planina planina;

	//bi-directional many-to-one association to Znamenitost
	@OneToMany(mappedBy="planinarskaStaza")
	private List<Znamenitost> znamenitosts;

	public Planinarska_staza() {
	}

	public int getIdStaza() {
		return this.idStaza;
	}

	public void setIdStaza(int idStaza) {
		this.idStaza = idStaza;
	}

	public byte[] getMapa() {
		return this.mapa;
	}

	public void setMapa(byte[] mapa) {
		this.mapa = mapa;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getTezina() {
		return this.tezina;
	}

	public void setTezina(int tezina) {
		this.tezina = tezina;
	}

	public Planina getPlanina() {
		return this.planina;
	}

	public void setPlanina(Planina planina) {
		this.planina = planina;
	}

	public List<Znamenitost> getZnamenitosts() {
		return this.znamenitosts;
	}

	public void setZnamenitosts(List<Znamenitost> znamenitosts) {
		this.znamenitosts = znamenitosts;
	}

	public Znamenitost addZnamenitost(Znamenitost znamenitost) {
		getZnamenitosts().add(znamenitost);
		znamenitost.setPlaninarskaStaza(this);

		return znamenitost;
	}

	public Znamenitost removeZnamenitost(Znamenitost znamenitost) {
		getZnamenitosts().remove(znamenitost);
		znamenitost.setPlaninarskaStaza(null);

		return znamenitost;
	}

}