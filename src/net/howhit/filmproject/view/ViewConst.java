package net.howhit.filmproject.view;

public final class ViewConst {

	private final String[] MAIN_MENU = {
							"$$$$$$$\\  $$$$$$$$\\  $$$$$$\\ $$$$$$$$\\ $$\\    $$\\ ",
							"$$  __$$\\ $$  _____|$$  __$$\\\\__$$  __|$$ |   $$ |",
							"$$ |  $$ |$$ |      $$ /  \\__|  $$ |   $$ |   $$ |",
							"$$$$$$$\\ |$$$$$\\    \\$$$$$$\\    $$ |   \\$$\\  $$  |",
							"$$  __$$\\ $$  __|    \\____$$\\   $$ |    \\$$\\$$  / ",
							"$$ |  $$ |$$ |      $$\\   $$ |  $$ |     \\$$$  /  ",
							"$$$$$$$  |$$$$$$$$\\ \\$$$$$$  |  $$ |      \\$  /   ",
							"\\_______/ \\________| \\______/   \\__|       \\_/    ",
							"",
							" (1) Archivos",
							" (2) Películas",
							" (3) Directores",
							" (4) Actores",
							" (5) Listados",
							" (S) Salir del programa",
							""
						};
	
	private final String[] FILES_MENU = {
							"MENU DE ARCHIVOS",
							"",
							" (1) Exportar directores",
							" (2) Exportar películas",
							" (V) Volver al menu principal",
							""
	};

	private final String[] FILMS_MENU = {
							"MENU DE PELICULAS",
							"",
							" (1) Dar de alta una película",
							" (2) Dar de baja una película",
							" (3) Modificar información de una película",
							" (4) Consultar información de una película",
							" (V) Volver al menu principal",
							""
	};
	private final String[] DIRECTOR_MENU = {
							"MENU DE DIRECTORES",
							"",
							" (1) Dar de alta un director",
							" (2) Dar de baja un director",
							" (3) Modificar información de un director",
							" (V) Volver al menu principal",
							""
	};
	private final String[] ACTORS_MENU = {
							"MENU DE ACTORES",
							"",
							" (1) Dar de alta un actor",
							" (2) Dar de baja un actor",
							" (3) Modificar información de un actor",
							" (4) Ver películas de un actor",
							" (V) Volver al menu principal",
							""
	};
	private final String[] LISTS_MENU = {
							"MENU DE LISTAS",
							"",
							" (1) Mostrar la lista de películas",
							" (2) Mostrar la lista de directores",
							" (3) Mostrar la lista de actores",
							" (V) Volver al menu principal",
							""
	};
	
	private final String[] MODIFY_FILMS_MENU = {
							"",
							" (1) Modificar el año de estreno",
							" (2) Modificar la duración de la película (en minutos)",
							" (3) Modificar el pais en la que se grabo la película",
							" (4) Modificar el guionista de la película",
							" (5) Modificar el responsable de la musica",
							" (6) Modificar la productora de la película",
							" (7) Modificar el género de la película",
							" (8) Modificar la sinopsis de la película",
							" (V) Volver al menu principal",
							""
	};
	private final String[] MODIFY_DIRECTOR_MENU = {
							"",
							" (1) Modificar la fecha de nacimiento",
							" (2) Modificar la nacionalidad del director",
							" (3) Modificar la ocupacion del director",
							" (V) Volver al menu principal",
							""
	};
	private final String[] MODIFY_ACTORS_MENU = {
							"",
							" (1) Modificar la fecha de nacimiento",
							" (2) Modificar la nacionalidad del director",
							" (3) Modificar el año de debut",
							" (V) Volver al menu principal",
							""
	};
	
	private final String[] MAIN_OPTIONS = {"1", "2", "3", "4", "5", "S", "s"};
	private final String[] FILES_OPTIONS = {"1", "2", "V", "v"};
	private final String[] FILMS_OPTIONS = {"1", "2", "3", "4", "V", "v"};
	private final String[] DIRECTOR_OPTIONS = {"1", "2", "3", "V", "v"};
	private final String[] ACTORS_OPTIONS = {"1", "2", "3", "4", "V", "v"};
	private final String[] LISTS_OPTIONS = {"1", "2", "3", "V", "v"};
	private final String[] GENRE_LIST = {"accion", "ciencia ficcion", "comedia", "drama", "fantasia", "melodrama", "musical", "romance", "suspense", "terror", "documental"};
	private final String[] MODIFY_FILMS_OPTIONS = {"1", "2", "3", "4", "5", "6", "7", "8", "V", "v"};
	private final String[] MODIFY_DIRECTOR_OPTIONS = {"1", "2", "3", "V", "v"};
	private final String[] MODIFY_ACTORS_OPTIONS = {"1", "2", "3", "V", "v"};
	
	
	public String[] getGenreList() {
		return GENRE_LIST;
	}
	public String[] getModifyFilmsOptions() {
		return MODIFY_FILMS_OPTIONS;
	}
	public String[] getModifyActorsOptions() {
		return MODIFY_ACTORS_OPTIONS;
	}
	public String[] getModifyActorsMenu() {
		return MODIFY_ACTORS_MENU;
	}
	public String[] getModifyDirectorOptions() {
		return MODIFY_DIRECTOR_OPTIONS;
	}
	public String[] getModifyFilmsMenu() {
		return MODIFY_FILMS_MENU;
	}
	public String[] getModifyDirectorMenu() {
		return MODIFY_DIRECTOR_MENU;
	}
	public String[] getMainMenuMesssage() {
		return MAIN_MENU;
	}
	public String[] getFilesMenuMesssage() {
		return FILES_MENU;
	}
	public String[] getFilmsMenuMesssage() {
		return FILMS_MENU;
	}
	public String[] getDirectorMenuMesssage() {
		return DIRECTOR_MENU;
	}
	public String[] getActorsMenuMesssage() {
		return ACTORS_MENU;
	}
	public String[] getListsMenuMesssage() {
		return LISTS_MENU;
	}
	
	public String[] getMainOptions() {
		return MAIN_OPTIONS;
	}
	public String[] getFilesOptions() {
		return FILES_OPTIONS;
	}
	public String[] getFilmsOptions() {
		return FILMS_OPTIONS;
	}
	public String[] getDirectorOptions() {
		return DIRECTOR_OPTIONS;
	}
	public String[] getActorsOptions() {
		return ACTORS_OPTIONS;
	}
	public String[] getListsOptions() {
		return LISTS_OPTIONS;
	}
}
