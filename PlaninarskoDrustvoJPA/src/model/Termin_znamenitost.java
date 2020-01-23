package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Termin_znamenitost database table.
 * 
 */
@Entity
@NamedQuery(name="Termin_znamenitost.findAll", query="SELECT t FROM Termin_znamenitost t")
public class Termin_znamenitost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTermin;

	@Temporal(TemporalType.TIMESTAMP)
	private Date kraj;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pocetak;
	
	private boolean neophodan;
	
	//bi-directional many-to-one association to Poseta
	@OneToMany(mappedBy="terminZnamenitost")
	private List<Poseta> posetas;
	
	//bi-directional many-to-one association to Znamenitost
	@ManyToOne
	@JoinColumn(name="Znamenitost_idZnamenitost")
	private Znamenitost znamenitost;

	public Termin_znamenitost() {
	}

	public int getIdTermin() {
		return this.idTermin;
	}

	public void setIdTermin(int idTermin) {
		this.idTermin = idTermin;
	}

	public Date getKraj() {
		return this.kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

	public Date getPocetak() {
		return this.pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	
	public boolean isNeophodan() {
		return neophodan;
	}

	public void setNeophodan(boolean neophodan) {
		this.neophodan = neophodan;
	}

	public List<Poseta> getPosetas() {
		return this.posetas;
	}

	public void setPosetas(List<Poseta> posetas) {
		this.posetas = posetas;
	}

	public Poseta addPoseta(Poseta poseta) {
		getPosetas().add(poseta);
		poseta.setTerminZnamenitost(this);

		return poseta;
	}

	public Poseta removePoseta(Poseta poseta) {
		getPosetas().remove(poseta);
		poseta.setTerminZnamenitost(null);

		return poseta;
	}

	public Znamenitost getZnamenitost() {
		return this.znamenitost;
	}

	public void setZnamenitost(Znamenitost znamenitost) {
		this.znamenitost = znamenitost;
	}

}