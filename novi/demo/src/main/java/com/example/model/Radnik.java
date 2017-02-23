package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.enumeracije.VrsteKorisnika;


@Entity
@Table(name="radnici")
public class Radnik extends Korisnik implements Serializable{
 
 /**
  * 
  */
 private static final long serialVersionUID = 7909432708390532786L;

  
  @Column(name="ulogovanPrviPut", unique=false, nullable=false)
  private boolean ulogovanPrviPut=true;

  
  @Column(name="datumRodjenja", unique=false, nullable=false)
  private String datumRodjenja;
  
  @Column(name="konfekcijskiBroj", unique=false, nullable=false)
  private String konfekcijskiBroj;
  
  @Column(name="velicinaObuce", unique=false, nullable=false)
  private int velicinaObuce;
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id_radnik", unique=true, nullable=false)
  private Long id;
  
  public Radnik(){}
  

 
 public Radnik(String ime, String prezime, String email, String korisnickoIme, String sifra,
  VrsteKorisnika vrstaKorisnika) {
 super(ime, prezime, email, korisnickoIme, sifra, vrstaKorisnika);
 // TODO Auto-generated constructor stub
}



public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

public String getDatumRodjenja() {
 return datumRodjenja;
}

public void setDatumRodjenja(String datumRodjenja) {
 this.datumRodjenja = datumRodjenja;
}

public String getKonfekcijskiBroj() {
 return konfekcijskiBroj;
}

public void setKonfekcijskiBroj(String konfekcijskiBroj) {
 this.konfekcijskiBroj = konfekcijskiBroj;
}

public int getVelicinaObuce() {
 return velicinaObuce;
}

public void setVelicinaObuce(int velicinaObuce) {
 this.velicinaObuce = velicinaObuce;
}

public boolean isUlogovanPrviPut() {
 return ulogovanPrviPut;
}

public void setUlogovanPrviPut(boolean ulogovanPrviPut) {
 this.ulogovanPrviPut = ulogovanPrviPut;
}

 
  
}