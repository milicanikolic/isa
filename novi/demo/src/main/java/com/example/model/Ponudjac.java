package com.example.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.enumeracije.VrsteKorisnika;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ponudjaci")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ponudjac extends Korisnik implements Serializable{

 /**
  * 
  */
 private static final long serialVersionUID = 1049554017387817963L;
 
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="id_radnik", unique=true, nullable=false)
 private Long id;
 
 @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
 private MenadzerRestorana menadzerRestorana;
 
 @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ponudjac")
 private List<PonudaPonudjac> ponudePonudjac;
 
 @Column(name="ulogovanPrviPut", unique=false, nullable=false)
 private boolean ulogovanPrviPut=true;
 
 public Ponudjac(){}

 public Ponudjac(String ime, String prezime, String email, String korisnickoIme, String sifra,
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

 public MenadzerRestorana getMenadzerRestorana() {
  return menadzerRestorana;
 }

 public void setMenadzerRestorana(MenadzerRestorana menadzerRestorana) {
  this.menadzerRestorana = menadzerRestorana;
 }

 public boolean isUlogovanPrviPut() {
  return ulogovanPrviPut;
 }

 public void setUlogovanPrviPut(boolean ulogovanPrviPut) {
  this.ulogovanPrviPut = ulogovanPrviPut;
 }

 public List<PonudaPonudjac> getPonudePonudjac() {
  return ponudePonudjac;
 }

 public void setPonudePonudjac(List<PonudaPonudjac> ponudePonudjac) {
  this.ponudePonudjac = ponudePonudjac;
 }
 
 
 
 

}