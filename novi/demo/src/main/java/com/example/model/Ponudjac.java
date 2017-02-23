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
@Table(name="ponudjaci")
public class Ponudjac extends Korisnik implements Serializable{

 /**
  * 
  */
 private static final long serialVersionUID = 1049554017387817963L;
 
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="id_radnik", unique=true, nullable=false)
 private Long id;
 
 Ponudjac(){}

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
 
 
 
 

}