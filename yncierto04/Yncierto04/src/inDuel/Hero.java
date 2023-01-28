/*
	TODO:
	Package declaration if necessary
*/
package inDuel;

import java.util.Random;

public class Hero {
	/*
		TODO:
		Provide the appropriate access modifiers for: the attributes and constants, the class and constructor
	*/
	private String name;
	private int life;
	private int mana;
	private boolean alive;
	private Duel duel;			// to monitor which duel the hero was added to

	public final static String DARNA = "Darna";
	public final static String PANDAY = "Panday";
	public final static String CAPTAIN_BARBELL = "Captain Barbell";
	public final static String STARLA = "Starla";
	public final static String VICTOR = "Victor Magtanggol";
	public final static String PEDRO = "Pedro Penduko";
	public final static int ATTACK_DAMAGE = 5;
	public final static int RESPAWN_COST = 5;
	public final static int RESPAWN_LIFE = 5;

	public final static int MIN_LIFE = 10;
	public final static int MAX_LIFE = 20;
	public final static int MIN_MANNA = 5;
	public final static int MAX_MANNA = 10;


	public Hero(String name) {
		Random r = new Random();

		this.name = name;
		this.life = r.nextInt(Hero.MAX_LIFE-Hero.MIN_LIFE+1)+Hero.MIN_LIFE; // randomizes int values from 15-25
		this.mana = r.nextInt(Hero.MAX_MANNA-Hero.MIN_MANNA+1)+Hero.MIN_MANNA; // randomizes int values from 10-15
		this.alive = true;
	}

	/*
		TODO:
		Complete displayState() and provide the most appropriate access modifier
	*/

	void displayState() {
		// displays the hero's name
		System.out.println("name: "+this.name);
		// displays the hero's life
		System.out.println("life: "+this.life);
		// displays the hero's mana
		System.out.println("mana: "+this.mana);
		// displays the hero's status
		System.out.println("alive: "+(this.alive ? "true" : "false") + "\n");
	}


	/*
		TODO:
		Provide the most appropriate access modifier for this method
	*/
	void attack(Hero opponent) {
		System.out.println(this.name+" attacks "+opponent.name);

		if(opponent.life-Hero.ATTACK_DAMAGE>0)
			opponent.life -= Hero.ATTACK_DAMAGE;

		else {					// if deducting 5 will result to 0 or negative
			System.out.println(">> Hero "+opponent.name+" has been slain!");

			opponent.life = 0;
			opponent.respawn();
		}
	}

	/*
		TODO:
		Complete the code and provide the most appropriate access modifier for this method
		Note to properly encapsulate the attributes being accessed: Call the necessary setter(s) and getter(s)
	*/
	private void respawn() {
		// check if the hero can afford respawn
		if(this.mana-Hero.RESPAWN_COST>=0) {
			// reset life and mana
			this.life = Hero.RESPAWN_LIFE;
			this.mana -= Hero.RESPAWN_COST;
			// print respawned message
			System.out.println(">> Hero "+this.name+" has respawned!");
			return;
		}
		// otherwise, hero is deads
		this.alive = false;
		// number of alive heroes in the duel is decreased
		this.duel.setAliveHeroesNum();
	}

	/*
		TODO:
		Create the necessary getter(s) to properly encapsulate this class
		Make sure to use the most appropriate access modifier(s)
	*/
	public Duel getDuel() {
		// getter for duel
		return duel;
	}

	public String getName() {
		// getter for name
		return name;
	}

	public boolean isAlive() {
		// getter for alive
		return alive;
	}

	/*
		TODO:
		Create the necessary setter(s) to properly encapsulate this class
		Make sure to use the most appropriate access modifier(s)
	*/
	public void setDuel(Duel duel2) {
		// setter for duel
		this.duel = duel2;
    }
}

