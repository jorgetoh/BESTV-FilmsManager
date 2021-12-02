package net.howhit.filmproject.controller;

import net.howhit.filmproject.model.Model;
import java.io.IOException;
import java.util.ArrayList;

public class FilmController {
	
	private Model model;

	public void exportHtml() throws IOException {
		model.exportFilmsToHtml();
	}
	
	public void updateYear(String film, int year) {
		model.updateFilmYear(film, year);
	}
	public void updateDuration(String film, int duration) {
		model.updateFilmDuration(film, duration);
	}
	public void updateCountry(String film, String country) {
		model.updateFilmCountry(film, country);
	}
	public void updateScreenWritter(String film, String screenWritter) {
		model.updateFilmCountry(film, screenWritter);
	}
	public void updateMusic(String film, String music) {
		model.updateFilmMusic(film, music);
	}
	public void updateProducer(String film, String producer) {
		model.updateFilmProducer(film, producer);
	}
	public void updateGenre(String film, String genre) {
		model.updateFilmGenre(film, genre);
	}
	public void updateSinopsis(String film, String sinopsis) {
		model.updateFilmSinopsis(film, sinopsis);
	}

	public String getFilmData(String film) {
		return model.getFilmData(film);
	}
	
	public void putNewFilm(String title, int year, int duration, String country,
		ArrayList<String> direction, String screenWriter,
		String music, ArrayList<String> actors, String producer,
		String genre, String sinopsis) {
		model.putNewFilm(title, year, duration, country, direction, screenWriter, music, actors, producer, genre, sinopsis);
	}
	public void removeFilm(String film) {
		model.removeFilm(film);
	}
	
	public boolean filmsDbContains(String title) {
		return model.filmsDbContains(title);
	}
	public boolean isFilmsDbEmpty() {
		return model.isFilmsDbEmpty();
	}
	
	public void injectModel(Model model) {
		this.model = model;
	}
	
}
