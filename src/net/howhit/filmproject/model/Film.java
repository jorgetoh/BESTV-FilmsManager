package net.howhit.filmproject.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Film implements Serializable, Comparable<Film> {
	
	private final String title;
	private int year;
	private int duration;
	private String country;
	private final ArrayList<String> direction;
	private String screenWriter;
	private String music;
	private final ArrayList<String> actors;
	private String producer;
	private String genre;
	private String synopsis;
	
	public Film(String title, int year, int duration, String country,
		ArrayList<String> direction, String screenWriter,
		String music, ArrayList<String> actors, String producer,
		String genre, String sinopsis) {
		
		this.title = title;
		this.year = year;
		this.duration = duration;
		this.country = country;
		this.direction = direction;
		this.screenWriter = screenWriter;
		this.music = music;
		this.actors = actors;
		this.producer = producer;
		this.genre = genre;
		this.synopsis = sinopsis;
	}
	
	public String dataToString() {
		StringBuilder str = new StringBuilder();
		str.append(title)
			.append("#")
			.append(year)
			.append("#")
			.append(duration)
			.append("#")
			.append(country)
			.append("#");
		for (int i = 0; i<direction.size();i++) {
			str.append(direction.get(i));
			if (i < (direction.size() - 1)) {
				str.append(";");
			}
		}
		str.append("#").append(screenWriter).append("#").append(music).append("#");
		for (int i = 0; i<actors.size();i++) {
			str.append(actors.get(i));
			if (i < (actors.size() - 1)) {
				str.append(";");
			}
		}
		str.append("#").append(producer).append("#").append(genre).append("#").append(synopsis);
		
		return str.toString();
	}

	public String getTitle() {
		return title;
	}
	public int getYear() {
		return year;
	}
	public int getDuration() {
		return duration;
	}
	public String getCountry() {
		return country;
	}
	public ArrayList<String> getDirection() {
		return direction;
	}
	public String getScreenWriter() {
		return screenWriter;
	}
	public String getMusic() {
		return music;
	}
	public ArrayList<String> getActors() {
		return actors;
	}
	public String getProducer() {
		return producer;
	}
	public String getGenre() {
		return genre;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setScreenWriter(String screenWriter) {
		this.screenWriter = screenWriter;
	}
	public void setMusic(String music) {
		this.music = music;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Override
	public int compareTo(Film film) {
		int compareYear = film.getYear();
		
		return this.year - compareYear;
	}

}
