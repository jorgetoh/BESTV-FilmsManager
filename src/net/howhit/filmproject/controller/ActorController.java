package net.howhit.filmproject.controller;

import net.howhit.filmproject.model.Model;
import java.util.ArrayList;

public class ActorController {
	
	private Model model;
	
	public String[][] getFilmsTable(String name) {
		return model.actorsGetFilmsTable(name);
	}
	
	public void updateDate(String name, String date) {
		model.updateActorDate(name, date);
	}
	public void updateCountry(String name, String country) {
		model.updateActorCountry(name, country);
	}
	public void updateDebut(String name, int debut) {
		model.updateActorDebut(name, debut);
	}
	public boolean isActorsDbEmpty() {
		return model.isActorsDbEmpty();
	}
	
	public void removeActor(String name) {
		model.removeActor(name);
	}
	
	public void putNewActor(String name, String date, String country, int debut, ArrayList<String> films) {
		model.putNewActor(name, date, country, debut, films);
	}
	
	public boolean actorsDbContains(String actor) {
		return model.actorsDbContains(actor);
	}
	
	public void injectData(Model model) {
		this.model = model;
	}
	
}
