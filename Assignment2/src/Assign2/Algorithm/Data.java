package Assign2.Algorithm;

import java.util.ArrayList;
import java.util.List;

public class Data {
	private List<Athlete> list = new ArrayList<Athlete>();
	private List<Official> olist = new ArrayList<Official>();
	private List<Game> glist = new ArrayList<Game>();

	public Data() {
		list.add(new Swimmer("1", "Georgia", 25, "Vic"));
		list.add(new Swimmer("2", "Jones", 31, "Vic"));
		list.add(new Swimmer("3", "Mick", 28, "Vic"));
		list.add(new Swimmer("4", "Daniel", 30, "Vic"));
		list.add(new Athlete("5", "Igor", 30, "Vic"));
		list.add(new Runner("6", "Jack", 24, "Vic"));
		list.add(new Runner("7", "Arnold", 26, "Vic"));
		list.add(new Runner("8", "Jess", 36, "Vic"));
		list.add(new Runner("9", "Johnny", 30, "Vic"));
		list.add(new Runner("10", "Jim", 32, "Vic"));
		list.add(new Cyclist("11", "Jane", 33, "Vic"));
		list.add(new Cyclist("12", "Jean", 30, "Vic"));
		list.add(new Runner("13", "Louis", 27, "Vic"));
		list.add(new Cyclist("14", "Theo", 30, "Vic"));
		list.add(new Cyclist("15", "Remy", 29, "Vic"));
		list.add(new Cyclist("16", "Alex", 30, "Vic"));
		list.add(new Swimmer("17", "Antony", 34, "Vic"));
		list.add(new Runner("18", "Chris", 26, "Vic"));
		list.add(new Athlete("19", "Prabil", 30, "Vic"));
		list.add(new Cyclist("20", "Sas", 31, "Vic"));
		list.add(new Swimmer("21", "Steph", 30, "Vic"));
		list.add(new Cyclist("22", "Matty", 28, "Vic"));
		list.add(new Runner("23", "Lachie", 27, "Vic"));
		list.add(new Swimmer("24", "Jono", 30, "Vic"));
		list.add(new Athlete("25", "Matt", 30, "Vic"));
		list.add(new Cyclist("26", "Stu", 29, "Vic"));
		list.add(new Swimmer("27", "Alli", 30, "Vic"));
		list.add(new Athlete("28", "Kiki", 33, "Vic"));
		list.add(new Athlete("29", "Simon", 30, "Vic"));
		list.add(new Athlete("30", "Prasad", 27, "Vic"));

		olist.add(new Official("A", "James", 31, "Vic"));
		olist.add(new Official("B", "John", 30, "Vic"));
		olist.add(new Official("C", "Joe", 32, "Vic"));
		olist.add(new Official("D", "Jerome", 30, "Vic"));
		olist.add(new Official("E", "Jose", 33, "Vic"));

		glist.add(new Game("Swimming", "S1"));
		glist.add(new Game("Swimming", "S2"));
		glist.add(new Game("Swimming", "S3"));
		glist.add(new Game("Running", "R1"));
		glist.add(new Game("Running", "R2"));
		glist.add(new Game("Running", "R3"));
		glist.add(new Game("Cycling", "C1"));
		glist.add(new Game("Cycling", "C2"));
		glist.add(new Game("Cycling", "C3"));
	}

	public List<Athlete> getAthletes() {
		return list;
	}

	public List<Official> getOfficials() {
		return olist;
	}

	public List<Game> getGames() {
		return glist;
	}

}
