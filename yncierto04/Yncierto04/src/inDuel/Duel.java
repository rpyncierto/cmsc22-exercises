/*
	TODO:
	Package declaration if necessary
*/
package inDuel;

import java.util.Random;

public class Duel {
	/*
		TODO:
		Provide the appropriate access modifiers for: the attributes and constants, the class, and constructor
	*/
	private static int duelCount;		// class attribute to track number of duels created (auto-incremented)

	private int duelID;
	private int noOfHeroes;
	private int noOfAliveHeroes;
	private Hero[] heroes;

	public final static int MAX_HEROES = 5;

	/*
		TODO:
		Initialize attributes in the constructor
	*/
	public Duel() {
		// increments the number of duels created
		Duel.duelCount++;
		// sets the duel ID
		this.duelID = Duel.duelCount - 1;
		// sets the number of heroes to 0
		this.noOfHeroes = 0;
		// sets the number of alive heroes to 0
		this.noOfAliveHeroes = 0;
		// initializes the array of heroes
		this.heroes = new Hero[Duel.MAX_HEROES];
	}

	/*
		TODO:
		Complete addHero() and provide the most appropriate access modifier for this method
		Note to properly encapsulate the attributes being accessed: Call the necessary setter(s) and getter(s)
	*/
	public void addHero(Hero hero) {
		// checks if less than the limit
		if (this.noOfHeroes >= Duel.MAX_HEROES) {
			return;
		}
		// checks if hero is not yet in this duel (hint: use 'this' keyword)
		if (hero.getDuel() != this) {
		// add the hero to the list
			this.heroes[noOfHeroes] = hero;
		// update the duel attribute of the hero to be this duel (hint: use 'this' keyword)
			hero.setDuel(this);
		// update counter of heroes and alive heroes
			this.noOfHeroes++;
			this.noOfAliveHeroes++;
		// print "<hero name> was added to the duel."
			System.out.println(hero.getName()+" was added to the duel.");
		}
	}

	/*
		TODO:
		Provide the most appropriate access modifiers for methods begin() and randomizeEnemy()
		Modify this method to properly encapsulate the attributes being accessed: Call the necessary setter(s) and getter(s)
	*/
	public void begin(int rounds) {

		for(int i=0;i<rounds;i++){
			if(this.noOfAliveHeroes>1){										// checks if there are at least 2 heroes

				System.out.println("=====ROUND: "+(i+1)+"=====");

				for(int j=0;j<this.noOfHeroes;j++){							// for every hero in this duel..
					if(this.heroes[j].isAlive()) {
						int enemyIndex = this.randomizeEnemy(j);
						this.heroes[j].attack(this.heroes[enemyIndex]);
					}
				}
				this.displayHeroStats();

			} else {
				System.out.println("Not enough heroes.");
				break;
			}
		}
	}

	private int randomizeEnemy(int heroIndex) {
		Random r = new Random();
		int enemyIndex;

		do{
			enemyIndex = r.nextInt(this.noOfHeroes);
		}while(this.heroes[heroIndex].equals(this.heroes[enemyIndex]) || !this.heroes[enemyIndex].isAlive()); 	// ensures that the hero will not attack itself
																												// and that a dead hero is not attacked
		return enemyIndex;
	}

	/*
		TODO:
		Provide the most appropriate access modifiers for the methods viewState() and displayHeroStats()
	*/
	public void viewState() {
		System.out.println("\nDuel ID: "+this.duelID);
		this.displayHeroStats();
	}

	private void displayHeroStats() {
		System.out.println("\nHeroes' Stats: ");
		for(int i=0;i<this.noOfHeroes;i++){
			this.heroes[i].displayState();
		}
	}

	/*
		TODO:
		Create the necessary setter(s) to properly encapsulate this class
		Make sure to use the most appropriate access modifier(s)
	*/
	public void setAliveHeroesNum() {
		// decrement the number of alive heroes
		this.noOfAliveHeroes--;
	}

	public String getDuelID() {
		// getter for duelID
		return Integer.toString(this.duelID);
	}


}