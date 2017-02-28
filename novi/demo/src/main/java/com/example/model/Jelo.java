package com.example.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="jela")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Jelo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6888982385440601177L;

	public enum vrstaJela {
		SALATA, KUVANO_JELO, PECENO_JELO
	};
	
	@ManyToOne (cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Restoran restoran;
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch= FetchType.LAZY)
	private Kuvar kuvar;
	
	@Column(name="vrsta_jela", unique=false, nullable=false)
	private vrstaJela vrstaJela;
	@Column(name = "naziv", unique = true, nullable = false)
	private String naziv;
	@Column(name = "opis", unique = false, nullable = true)
	private String opis;
	@Column(name = "cena", unique = false, nullable = false)
	private double cena;
	@Column(name = "ocena", unique = false, nullable = true)
	private double ocena;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_jelo", unique = true, nullable = false)
	private Long id;
	
	public Jelo() {}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public vrstaJela getVrstaJela() {
		return vrstaJela;
	}

	public void setVrstaJela(vrstaJela vrstaJela) {
		this.vrstaJela = vrstaJela;
	}

	public Kuvar getKuvar() {
		return kuvar;
	}

	public void setKuvar(Kuvar kuvar) {
		this.kuvar = kuvar;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}
	
}
