package gigglemaps;

public class Restaurant {
	// attributes
	int capacity;
	int rating;
	int id;
	int scopeCounter = 0;
	int reservationCounter = 0;

	float cost;
	float sales = 0;

	String name;

	Location[] scope;
	User[] reservation;

	final static int MAX_SCOPE = 10;
	final static int MAX_RESERVATION = 10;

	// constructor
	Restaurant(int id, String name, int rating, int capacity, float cost) {
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.capacity = capacity;
		this.cost = cost;
		this.scope = new Location[MAX_SCOPE];
		this.reservation = new User[MAX_RESERVATION];
	}

	// methods
	void addLocation(Location location) {
		this.scope[scopeCounter++] = location; // add location to scope
		location.addResto(this); // add resto to location
	}

	void addReservee(User reservee) {
		this.reservation[this.reservationCounter++] = reservee; // add reservee to reservation
	}

	void viewState() {
		System.out.println("------------ RESTAURANT -------------");
		System.out.println("ID: " + this.id);
		System.out.println("Name: " + this.name);
		System.out.println("Current capacity: " + this.capacity);
		System.out.println("Total sales: " + this.sales);
		System.out.print("Scope: ");
		if(this.scopeCounter == 0) {
			System.out.println("none");
		} else if(this.scopeCounter == 1) {
			System.out.println(this.scope[this.scopeCounter-1].name);
		} else {
			System.out.println();
			for(int i = 0; i < this.scopeCounter; i++) {
				System.out.println("\t" + (i+1) + ". " + this.scope[i].name);
			}
		}
		System.out.print("Reservations received: ");
		if(this.reservationCounter == 0) {
			System.out.println("No entry yet.");
		} else if(this.reservationCounter == 1) {
			System.out.println(this.reservation[this.reservationCounter-1].username);
		} else {
			System.out.println();
			for(int i = 0; i < this.reservationCounter; i++) {
				System.out.println("\t" + (i+1) + ". " + this.reservation[i].username);
			}
		}
		System.out.print("Last reservation received: ");
		if(this.reservationCounter == 0) {
			System.out.println("none");
		} else {
			System.out.println(this.reservation[this.reservationCounter-1].username);
		}
		System.out.println();
	}
}
