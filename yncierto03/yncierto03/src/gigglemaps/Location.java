package gigglemaps;

public class Location {
	// attributes
	int id;
	int restoCounter = 0;

	String name;

	Restaurant[] restaurants;

	final static int MAX_RESTO = 10;

	// constructor
	Location(int id, String name) {
		this.id = id;
		this.name = name;
		this.restaurants = new Restaurant[MAX_RESTO];
	}

	// methods
	void addResto(Restaurant resto) {
		this.restaurants[this.restoCounter++] = resto; // add resto to restaurants
	}

	void viewState() {
		System.out.println("------------ LOCATION -------------");
		System.out.println("ID: " + this.id);
		System.out.println("Name: " + this.name);
		System.out.println();
	}
}
