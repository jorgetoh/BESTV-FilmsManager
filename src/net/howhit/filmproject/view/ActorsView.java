package net.howhit.filmproject.view;

import com.coti.tools.Esdia;
import com.coti.tools.OpMat;
import net.howhit.filmproject.controller.Controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ActorsView {

	private Controller controller;
	private ViewConst viewConst;
	private View view;
	
	public void runActorsMenu() throws Exception {
		view.clearConsole();
		String option;
		filmsLoop: while (true) {
			for (String message : viewConst.getActorsMenuMesssage()) {
				System.out.println(message);
			}
			option = Esdia.readString("Introduce una opcion válida: ", viewConst.getActorsOptions()).toUpperCase();
			switch (option) {
				case "1" -> addNewActor();
				case "2" -> removeActor();
				case "3" -> modifyActor();
				case "4" -> showActorFilms();
				case "V" -> {
					System.out.println("Has salido del menu de actores correctamente.");
					break filmsLoop;
				}
			}
		}
	}
	
	private void addNewActor() {
		view.clearConsole();
		System.out.println("INTRODUCIR NUEVO ACTOR");
		System.out.print("");
		String name = Esdia.readString("Introduce el nombre del actor: ");
		while (controller.getActorC().actorsDbContains(name)) {
			System.out.println("El actor '"+name+"' ya se encuentra en la base de datos.");
			name = Esdia.readString("Introduce de nuevo el nombre del actor: ");
		}
		
		String date = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		boolean validFormat = false;
		while (validFormat == false) {
			date = Esdia.readString("Introduce la fecha de nacimiento (Formato: yyyy-MM-dd): ");
			try {
				format.parse(date);
				validFormat = true;
			} catch (ParseException e) {
				System.out.println("El formato de la fecha introducida no es válido. (Formato: yyyy-MM-dd)");
				validFormat = false;
			}
		}
		String country = Esdia.readString("Introduce la nacionalidad del actor: ");
		int debut = Esdia.readInt("Introduce el año de debut del actor: ");
		
		ArrayList<String> films = new ArrayList<>();
		String film;
		boolean moreValues = true;
		while (moreValues) {
			film = Esdia.readString("Introduce el nombre de una pelicula: ");
			boolean containsFilm = films.stream().anyMatch(film::equalsIgnoreCase);
			if (!containsFilm) {
				films.add(film);
			} else {
				System.out.println("Esta película ya esta en la lista.");
			}
			moreValues = Esdia.yesOrNo("Quieres introducir una pelicula mas?");
		}
		controller.getActorC().putNewActor(name, date, country, debut, films);
		System.out.println("");
		System.out.println("El actor "+name+" se ha guardado en la base de datos correctamente.");
		System.out.println("");
	}
	
	private void modifyActor() {
		view.clearConsole();
		if (controller.getActorC().isActorsDbEmpty()) {
			System.out.println("La base de datos de actores esta vacía. No puedes modificar ningun actor.");
			return;
		}
		System.out.println("INTRODUCIR EL ACTOR A MODIFICAR");
		System.out.print("");
		String name = Esdia.readString("Introduce el nombre del actor a modificar: ");
		while (!controller.getActorC().actorsDbContains(name)) {
			System.out.println("El actor '"+name+"' no se encuentra en la base de datos.");
			name = Esdia.readString("Introduce de nuevo el nombre del actor a modificar: ");
		}
		view.clearConsole();
		String option;
		actorModifyLoop: while (true) {
			System.out.println("MODIFICAR EL ACTOR '"+name.toUpperCase()+"'");
			for (String message : viewConst.getModifyActorsMenu()) {
				System.out.println(message);
			}
			option = Esdia.readString("Introduce una opcion válida: ", viewConst.getModifyActorsOptions()).toUpperCase();
			switch (option) {
				case "1" -> {
					String date = "";
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					format.setLenient(false);
					boolean validFormat = false;
					while (validFormat == false) {
						date = Esdia.readString("Introduce la fecha de nacimiento (Formato: yyyy-MM-dd): ");
						try {
							format.parse(date);
							validFormat = true;
						} catch (ParseException e) {
							System.out.println("El formato de la fecha introducida no es válido. (Formato: yyyy-MM-dd)");
							validFormat = false;
						}
					}
					controller.getActorC().updateDate(name, date);
					System.out.println("Se ha actualizado la fecha de nacimiento a '"+date+"'.");
					System.out.println("");
				}
				case "2" -> {
					String country = Esdia.readString("Introduce el pais de nacimiento del director: ");
					controller.getActorC().updateCountry(name, country);
					System.out.println("Se ha actualizado el pais de nacimiento a '"+country+"'.");
					System.out.println("");
				}
				case "3" -> {
					int debut = Esdia.readInt("Introduce el año de debut: ");
					controller.getActorC().updateDebut(name, debut);
					System.out.println("Se ha actualizado el año de debut del actor a '"+debut+"'.");
					System.out.println("");
				}
				case "V" -> {
					System.out.println("Has salido del menu de modificar actores correctamente.");
					break actorModifyLoop;
				}
			}
		}
	}
	
	private void removeActor() {
		view.clearConsole();
		if (controller.getActorC().isActorsDbEmpty()) {
			System.out.println("La base de datos de actores esta vacía. No puedes eliminar ningun actor.");
			return;
		}
		System.out.println("INTRODUCIR EL NOMBRE DEL ACTOR A ELIMINAR");
		System.out.print("");
		String name = Esdia.readString("Introduce el nombre del actor a eliminar: ");
		while (!controller.getActorC().actorsDbContains(name)) {
			System.out.println("El actor '"+name+"' no se encuentra en la base de datos.");
			name = Esdia.readString("Introduce de nuevo el nombre del actor a eliminar: ");
		}
		controller.getActorC().removeActor(name);
		System.out.println("");
		System.out.println("Se ha solicitado la eliminación del actor de la base de datos...");
		if (controller.getActorC().actorsDbContains(name)) {
			System.out.println("No se ha podido eliminar a '"+name+"' porque tiene películas en la base de datos.");
		} else {
			System.out.println("El actor ha sido eliminado de la base de datos correctamente.");
		}
		System.out.println("");
	}
	
	private void showActorFilms() throws Exception {
		view.clearConsole();
		if (controller.getActorC().isActorsDbEmpty()) {
			System.out.println("La base de datos de actores esta vacía. No puede mostrar ningun actor.");
			return;
		}
		System.out.println("LISTA DE PELICULAS DEL ACTOR");
		System.out.print("");
		String name = Esdia.readString("Introduce el nombre del actor: ");
		while (!controller.getActorC().actorsDbContains(name)) {
			System.out.println("El actor '"+name+"' no se encuentra en la base de datos.");
			name = Esdia.readString("Introduce de nuevo el nombre del actor: ");
		}
		System.out.println("");
		System.out.println("Solo se muestran las películas que se encuentran en la base de datos de películas.");
		System.out.println("");
		OpMat.printToScreen3(controller.getActorC().getFilmsTable(name));
		System.out.println("");
	}
	
	public void injectData(Controller controller, View view, ViewConst viewConst) {
		this.controller = controller;
		this.viewConst = viewConst;
		this.view = view;
	}
	
}
