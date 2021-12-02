package net.howhit.filmproject.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Actor implements Serializable {
	
	private final String name;
	private String bornDate;
	private String nationality;
	private int debutYear;
	private final ArrayList<String> films;
	
	public Actor(String name, String bornDate, String nationality, int debutYear, ArrayList<String> films) {
		this.name = name;
		this.bornDate = bornDate;
		this.nationality = nationality;
		this.debutYear = debutYear;
		this.films = films;
	}
	
	public String getName() {
		return this.name;
	}
	public String getBornDate() {
		return this.bornDate;
	}
	public String getNationality() {
		return this.nationality;
	}
	public int getDebutYear() {
		return this.debutYear;
	}
	public ArrayList<String> getFilms() {
		return this.films;
	}

	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setDebut(int debutYear) {
		this.debutYear = debutYear;
	}
}
