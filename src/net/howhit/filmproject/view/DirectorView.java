package net.howhit.filmproject.view;

import com.coti.tools.Esdia;
import net.howhit.filmproject.controller.Controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DirectorView {

	private Controller controller;
	private ViewConst viewConst;
	private View view;
	
	public void runDirectorMenu() {
		view.clearConsole();
		String option;
		filmsLoop: while (true) {
			for (String message : viewConst.getDirectorMenuMesssage()) {
				System.out.println(message);
			}
			option = Esdia.readString("Introduce una opcion válida: ", viewConst.getDirectorOptions()).toUpperCase();
			switch (option) {
				case "1" -> addNewDirector();
				case "2" -> removeDirector();
				case "3" -> modifyDirector();
				case "V" -> {
					System.out.println("Has salido del menu de directores correctamente.");
					break filmsLoop;
				}
			}
		}
	}
	
	private void addNewDirector() {
		view.clearConsole();
		System.out.println("INTRODUCIR NUEVO DIRECTOR");
		System.out.print("");
		String name = Esdia.readString("Introduce el nombre del director: ");
		while (controller.getDirectorC().directorDbContains(name)) {
			System.out.println("El director '"+name+"' ya se encuentra en la base de datos.");
			name = Esdia.readString("Introduce de nuevo el nombre del director: ");
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
		String country = Esdia.readString("Introduce la nacionalidad del director: ");
		String occupation = Esdia.readString("Introduce la ocupacion del director: ");
		
		ArrayList<String> films = new ArrayList<>();
		String film;
		boolean moreValues = true;
		while (moreValues) {
			film = Esdia.readString("Introduce el nombre de la película: ");
			boolean containsFilm = films.stream().anyMatch(film::equalsIgnoreCase);
			if (!containsFilm) {
				films.add(film);
			} else {
				System.out.println("Esta película ya esta en la lista.");
			}
			films.add(film);
			moreValues = Esdia.yesOrNo("¿Quieres introducir una película mas?");
		}
		controller.getDirectorC().putNewDirector(name, date, country, occupation, films);
		System.out.println("");
		System.out.println("El director "+name+" se ha guardado en la base de datos correctamente.");
		System.out.println("");
	}
	
	private void modifyDirector() {
		view.clearConsole();
		if (controller.getDirectorC().isDirectorDbEmpty()) {
			System.out.println("La base de datos de directores esta vacía. No puedes modificar ningun director.");
			return;
		}
		System.out.println("INTRODUCIR EL DIRECTOR A MODIFICAR");
		System.out.print("");
		String name = Esdia.readString("Introduce el nombre del director a modificar: ");
		while (!controller.getDirectorC().directorDbContains(name)) {
			System.out.println("El director '"+name+"' no se encuentra en la base de datos.");
			name = Esdia.readString("Introduce de nuevo el nombre del director a modificar: ");
		}
		view.clearConsole();
		String option;
		directorModifyLoop: while (true) {
			System.out.println("MODIFICAR EL DIRECTOR '"+name.toUpperCase()+"'");
			for (String message : viewConst.getModifyDirectorMenu()) {
				System.out.println(message);
			}
			option = Esdia.readString("Introduce una opcion válida: ", viewConst.getModifyDirectorOptions()).toUpperCase();
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
					controller.getDirectorC().updateDate(name, date);
					System.out.println("Se ha actualizado la fecha de nacimiento a '"+date+"'.");
					System.out.println("");
				}
				case "2" -> {
					String country = Esdia.readString("Introduce el pais de nacimiento del director: ");
					controller.getDirectorC().updateCountry(name, country);
					System.out.println("Se ha actualizado el pais de nacimiento a '"+country+"'.");
					System.out.println("");
				}
				case "3" -> {
					String country = Esdia.readString("Introduce el pais en el que se grabo la película: ");
					controller.getDirectorC().updateOccupation(name, country);
					System.out.println("Se ha actualizado la ocupacion del director a '"+country+"'.");
					System.out.println("");
				}
				case "V" -> {
					System.out.println("Has salido del menu de modificar directores correctamente.");
					break directorModifyLoop;
				}
			}
		}
	}
	
	private void removeDirector() {
		view.clearConsole();
		if (controller.getDirectorC().isDirectorDbEmpty()) {
			System.out.println("La base de datos de directores esta vacía. No puedes eliminar ningun director.");
			return;
		}
		System.out.println("INTRODUCIR EL NOMBRE DEL DIRECTOR A ELIMINAR");
		System.out.println("");
		String name = Esdia.readString("Introduce el nombre del director a eliminar: ");
		while (!controller.getDirectorC().directorDbContains(name)) {
			System.out.println("El director '"+name+"' no se encuentra en la base de datos.");
			name = Esdia.readString("Introduce de nuevo el nombre del director a eliminar: ");
		}
		controller.getDirectorC().removeDirector(name);
		System.out.println("");
		System.out.println("Se ha solicitado la eliminación del director...");
		if (controller.getDirectorC().directorDbContains(name)) {
			System.out.println("No se ha podido eliminar a '"+name+"' porque tiene películas en la base de datos.");
		} else {
			System.out.println("El director ha sido eliminado de la base de datos correctamente.");
		}
		System.out.println("");
	}
	
	public void injectData(Controller controller, View view, ViewConst viewConst) {
		this.controller = controller;
		this.viewConst = viewConst;
		this.view = view;
	}
	
}
