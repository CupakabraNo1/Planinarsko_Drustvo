package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Komentar database table.
 * 
 */
@Entity
@NamedQuery(name="Komentar.findAll", query="SELECT k FROM Komentar k")
public class Komentar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKomentar;

	private String tekst;

	//bi-directional many-to-one association to Poseta
	@ManyToOne
	@JoinColumn(name="Poseta_idPoseta")
	private Poseta poseta;

	public Komentar() {
	}

	public int getIdKomentar() {
		return this.idKomentar;
	}

	public void setIdKomentar(int idKomentar) {
		this.idKomentar = idKomentar;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Poseta getPoseta() {
		return this.poseta;
	}

	public void setPoseta(Poseta poseta) {
		this.poseta = poseta;
	}

}