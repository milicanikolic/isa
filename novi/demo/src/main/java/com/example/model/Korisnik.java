package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


import com.example.enumeracije.VrsteKorisnika;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="korisnici")
@Inheritance(strategy=InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Korisnik implements Serializable{

 
 @Column(name="ime", unique=false, nullable=false)
  private String ime;
  @Column(name="prezime", unique=false, nullable=false)
  private String prezime;
  @Column(name="email", unique=true, nullable=false)
  private String email;
  @Column(name="korisnickoIme", unique=true, nullable=false)
  private String korisnickoIme;
  @Column(name="sifra", unique=false, nullable=false)
  private String sifra;
  @Column(name="vrstaKorisnika", unique=false, nullable=true)
  private VrsteKorisnika vrstaKorisnika;
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id", unique=true, nullable=false)
  private Long id;
  
  Korisnik(){}
 

 public Korisnik(String ime, String prezime, String email, String korisnickoIme, String sifra,
   VrsteKorisnika vrstaKorisnika) {
  super();
  this.ime = ime;
  this.prezime = prezime;
  this.email = email;
  this.korisnickoIme = korisnickoIme;
  this.sifra = sifra;
  this.vrstaKorisnika = vrstaKorisnika;
 }







 public VrsteKorisnika getVrstaKorisnika() {
  return vrstaKorisnika;
 }

 public void setVrstaKorisnika(VrsteKorisnika vrstaKorisnika) {
  this.vrstaKorisnika = vrstaKorisnika;
 }

 public String getIme() {
  return ime;
 }

 public void setIme(String ime) {
  this.ime = ime;
 }

 public String getPrezime() {
  return prezime;
 }

 public void setPrezime(String prezime) {
  this.prezime = prezime;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getKorisnickoIme() {
  return korisnickoIme;
 }

 public void setKorisnickoIme(String korisnickoIme) {
  this.korisnickoIme = korisnickoIme;
 }

 public String getSifra() {
  return sifra;
 }

 public void setSifra(String sifra) {
  this.sifra = sifra;
 }


 public Long getId() {
  return id;
 }


 public void setId(Long id) {
  this.id = id;
 } 
 
 
}