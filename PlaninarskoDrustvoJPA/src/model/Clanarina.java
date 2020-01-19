package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Clanarina database table.
 * 
 */
@Entity
@NamedQuery(name="Clanarina.findAll", query="SELECT c FROM Clanarina c")
public class Clanarina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idClanarina;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="do")
	private Date do_;

	@Temporal(TemporalType.TIMESTAMP)
	private Date od;

	//bi-directional one-to-one association to Korisnik
	@OneToOne(mappedBy="clanarina")
	private Korisnik korisnik;

	public Clanarina() {
	}

	public int getIdClanarina() {
		return this.idClanarina;
	}

	public void setIdClanarina(int idClanarina) {
		this.idClanarina = idClanarina;
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

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}