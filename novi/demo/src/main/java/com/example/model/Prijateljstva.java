package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.enumeracije.StatusZahteva;



@Entity
@Table(name="prijateljstva")
public class Prijateljstva {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="id", unique=true, nullable=false)
	 private Long id;
	 
	 @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	 private Gost posiljalac;
	 
	 @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	 private Gost primalac;
	 
	 @Column(name="status", unique=false, nullable=false)
	 private StatusZahteva status;

	 Prijateljstva(){}
	 
	 

	public Prijateljstva(Gost posiljalac, Gost primalac, StatusZahteva status) {
		super();
		this.posiljalac = posiljalac;
		this.primalac = primalac;
		this.status = status;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Gost getPosiljalac() {
		return posiljalac;
	}

	public void setPosiljalac(Gost posiljalac) {
		this.posiljalac = posiljalac;
	}

	public Gost getPrimalac() {
		return primalac;
	}

	public void setPrimalac(Gost primalac) {
		this.primalac = primalac;
	}

	public StatusZahteva getStatus() {
		return status;
	}

	public void setStatus(StatusZahteva status) {
		this.status = status;
	}
	 
	 
}
