package net.howhit.filmproject.view;

import com.coti.tools.Esdia;
import net.howhit.filmproject.controller.Controller;
import java.util.ArrayList;

public class FilmsView {
	
	private Controller controller;
	private ViewConst viewConst;
	private View view;
	
	public void runFilmsMenu() {
		view.clearConsole();
		String option;
		filmsLoop: while (true) {
			for (String message : viewConst.getFilmsMenuMesssage()) {
				System.out.println(message);
			}
			option = Esdia.readString("Introduce una opcion valida: ", viewConst.getFilmsOptions()).toUpperCase();
			switch (option) {
				case "1" -> addNewFilm();
				case "2" -> removeFilm();
				case "3" -> modifyFilm();
				case "4" -> viewFilmData();
				case "V" -> {
					System.out.println("Has salido del menu de películas correctamente.");
					break filmsLoop;
				}
			}
		}
	}
	
		
	private void addNewFilm() {
		view.clearConsole();
		System.out.println("INTRODUCIR NUEVA PELÍCULA");
		System.out.print("");
		String title = Esdia.readString("Introduce el título de la película: ");
		while (controller.getFilmC().filmsDbContains(title)) {
			System.out.println("La película '"+title+"' ya se encuentra en la base de datos.");
			title = Esdia.readString("Introduce de nuevo el título de la película: ");
		}
		int year = Esdia.readInt("Introduzca el año del estreno de la película: ");
		int duration = Esdia.readInt("Introduzca la duración de la película (en minutos): ");
		String country = Esdia.readString("Introduce el país en el que se grabo la película: ");
		
		ArrayList<String> directors = new ArrayList<>();
		String director;
		boolean moreValues = true;
		while (moreValues) {
			director = Esdia.readString("Introduce el nombre del director: ");
			boolean containsDirector = directors.stream().anyMatch(director::equalsIgnoreCase);
			if (!containsDirector) {
				directors.add(director);
			} else {
				System.out.println("Este director ya esta en la lista.");
			}
			moreValues = Esdia.yesOrNo("¿Quieres introducir un director más?");
		}
		
		String screenWriter = Esdia.readString("Introduce el nombre del guionista: ");
		String musicProducer = Esdia.readString("Introduce el nombre de la persona responsable de la música: ");
		
		ArrayList<String> actors = new ArrayList<>();
		String actor;
		moreValues = true;
		while (moreValues) {
			actor = Esdia.readString("Introduce el nombre de un actor: ");
			boolean containsActor = actors.stream().anyMatch(actor::equalsIgnoreCase);
			if (!containsActor) {
				actors.add(actor);
			} else {
				System.out.println("Este actor ya esta en la lista.");
			}
			moreValues = Esdia.yesOrNo("¿Quieres introducir un actor más?");
		}
		String producer = Esdia.readString("Introduce la productora de la película: ");
		String genre = Esdia.readString("Introduce el genero de la película: ", viewConst.getGenreList());
		String sinopsis = Esdia.readString("Introduce la sinopsis de la película: ");
		controller.getFilmC().putNewFilm(title, year, duration, country, directors, screenWriter, musicProducer, actors, producer, genre, sinopsis);
		System.out.println("");
		System.out.println("La película "+title+" se ha guardado en la base de datos correctamente.");
	}
	private void removeFilm() {
		if (controller.getFilmC().isFilmsDbEmpty()) {
			System.out.println("La base de datos de películas esta vacia. No puedes eliminar ninguna película.");
			return;
		}
		view.clearConsole();
		System.out.println("INTRODUCIR LA PELÍCULA PARA ELIMINAR");
		System.out.print("");
		String title = Esdia.readString("Introduce el título de la película a eliminar: ");
		while (!controller.getFilmC().filmsDbContains(title)) {
			System.out.println("La película '"+title+"' no se encuentra en la base de datos.");
			title = Esdia.readString("Introduce el título de la película a eliminar: ");
		}
		controller.getFilmC().removeFilm(title);
		System.out.println("La película '"+title+"' ha sido eliminada de la base de datos.");
		System.out.println("");
	}
	private void modifyFilm() {
		if (controller.getFilmC().isFilmsDbEmpty()) {
			System.out.println("La base de datos de películas esta vacia. No puedes modificar ninguna película.");
			return;
		}
		view.clearConsole();
		System.out.println("INTRODUCIR LA PELÍCULA PARA MODIFICAR");
		System.out.print("");
		String title = Esdia.readString("Introduce el título de la película a modificar: ");
		while (!controller.getFilmC().filmsDbContains(title)) {
			System.out.println("La película '"+title+"' no se encuentra en la base de datos.");
			title = Esdia.readString("Introduce el título de la película a eliminar: ");
		}
		view.clearConsole();
		String option;
		System.out.println("MODIFICAR LA PELÍCULA '"+title.toUpperCase()+"'");
		filmsModifyLoop: while (true) {
			for (String message : viewConst.getModifyFilmsMenu()) {
				System.out.println(message);
			}
			option = Esdia.readString("Introduce una opcion valida: ", viewConst.getModifyFilmsOptions()).toUpperCase();
			switch (option) {
				case "1" -> {
					int year = Esdia.readInt("Introduzca el nuevo año de estreno: ", 0, 3000);
					controller.getFilmC().updateYear(title, year);
					System.out.println("Se ha actualizado el año de estreno de la película '"+title+"'.");
					System.out.println("");
				}
				case "2" -> {
					int duration = Esdia.readInt("Introduzca la duración de la película (en minutos): ", 1, 10000);
					controller.getFilmC().updateDuration(title, duration);
					System.out.println("Se ha actualizado el la duración de la película '"+title+"'.");
					System.out.println("");
				}
				case "3" -> {
					String country = Esdia.readString("Introduce el país en el que se grabo la película: ");
					controller.getFilmC().updateCountry(title, country);
					System.out.println("Se ha actualizado el país de la película '"+title+"'.");
					System.out.println("");
				}
				case "4" -> {
					String screenWritter = Esdia.readString("Introduce el guionista de la película: ");
					controller.getFilmC().updateScreenWritter(title, screenWritter);
					System.out.println("Se ha actualizado el guionista de la película '"+title+"'.");
					System.out.println("");
				}
				case "5" -> {
					String music = Esdia.readString("Introduce el responsable de la música de la película: ");
					controller.getFilmC().updateMusic(title, music);
					System.out.println("Se ha actualizado el responsable de la música de la película '"+title+"'.");
					System.out.println("");
				}
				case "6" -> {
					String music = Esdia.readString("Introduce la productora de la película: ");
					controller.getFilmC().updateProducer(title, music);
					System.out.println("Se ha actualizado la productora de la película '"+title+"'.");
					System.out.println("");
				}
				case "7" -> {
					String genre = Esdia.readString("Introduce el genero de la película: ", viewConst.getGenreList());
					controller.getFilmC().updateGenre(title, genre);
					System.out.println("Se ha actualizado la genero de la película '"+title+"'.");
					System.out.println("");
				}
				case "8" -> {
					String sinopsis = Esdia.readString("Introduce la sinopsis de la película: ");
					controller.getFilmC().updateSinopsis(title, sinopsis);
					System.out.println("Se ha actualizado la sinopsis de la película '"+title+"'.");
					System.out.println("");
				}
				case "V" -> {
					System.out.println("Has salido del menu de modificar película correctamente.");
					break filmsModifyLoop;
				}
			}
		}
	}
	private void viewFilmData() {
		view.clearConsole();
		if (controller.getFilmC().isFilmsDbEmpty()) {
			System.out.println("La base de datos de películas esta vacia. No puedes ver la info de ninguna película.");
			return;
		}
		System.out.println("CONSULTA DE INFORMACION DE PELÍCULA");
		System.out.print("");
		String title = Esdia.readString("Introduce el título de la película a consultar: ");
		while (!controller.getFilmC().filmsDbContains(title)) {
			System.out.println("La película '"+title+"' no se encuentra en la base de datos.");
			title = Esdia.readString("Introduce el título de la película a eliminar: ");
		}
		view.clearConsole();
		System.out.println("CONSULTA DE LA PELÍCULA '"+title.toUpperCase()+"'");
		String[] filmData = controller.getFilmC().getFilmData(title).split("#");
		System.out.println("");
		System.out.println("Título de la película: "+filmData[0]);
		System.out.println("Año del estreno: "+filmData[1]);
		System.out.println("Duración de la película (en minutos): "+filmData[2]);
		System.out.println("Pais de realizacion: "+filmData[3]);
		
		System.out.println("Directores de la película:");
		String[] directors = filmData[4].split(";");
		for (String s : directors) {
			System.out.println("     - "+s);
		}
		System.out.println("Guioinsta de la película: "+filmData[5]);
		System.out.println("Encargado de la música: "+filmData[6]);
		
		System.out.println("Actores de la película:");
		String[] actors = filmData[7].split(";");
		for (String s : actors) {
			System.out.println("     - "+s);
		}
		System.out.println("Productora de la película: "+filmData[8]);
		System.out.println("Genero de la película: "+filmData[9]);
		System.out.println("Sinopsis: "+filmData[10]);
		System.out.println("");
		view.pressEnterToContinue();
	}
	
	public void injectData(Controller controller, View view, ViewConst viewConst) {
		this.controller = controller;
		this.viewConst = viewConst;
		this.view = view;
	}
	
}
