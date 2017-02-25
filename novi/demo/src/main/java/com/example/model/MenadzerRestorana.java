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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.enumeracije.VrsteKorisnika;

@Entity
@Table(name = "menadzeriRestorana")
public class MenadzerRestorana extends Korisnik implements Serializable {

 /**
  * 
  */
 private static final long serialVersionUID = -44778047641296369L;

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "menadzerRestorana_id", unique = true, nullable = false)
 private Long id;
 
 @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
 private Restoran restoran;


 public MenadzerRestorana(){
  
 }
 
 public MenadzerRestorana(String ime, String prezime, String email, String korisnickoIme, String sifra,
   VrsteKorisnika vrstaKorisnika) {
  super(ime, prezime, email, korisnickoIme, sifra, vrstaKorisnika);
  // TODO Auto-generated constructor stub
 }

 public Restoran getRestoran() {
  return restoran;
 }

 public void setRestoran(Restoran restoran) {
  this.restoran = restoran;
 }



 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }


 

}