package bankinternal;

/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading exercise
 *
 * (c) Institute of Computer Science, CAS, UPLB
 *
 *
 *************************************************************************************************************************/

/**
 * TO DO:
 * - Check relevant methods and see which will need the 'synchronized' keyword;
 *
 * - (bonus): which method will need to notify threads?
 */

class BankAccount {
	private int balance;
	private int accountNum;
	private String name;
	private Bank bank;

	private boolean hasPending = false;	// to prevent withdrawing when there's pending payment
	private boolean endOfCycle = false; // bonus: to prevent supplier thread from waiting when store is finished with its cycle

	BankAccount(String name, int accountNum, int balance, Bank b){
		this.name = name;
		this.accountNum = accountNum;
		this.balance = balance;
		System.out.println(this.name + " Initial bank account balance: " + this.balance);
		this.bank = b;
	}

	boolean withdraw(int x){

		/**
		 * TO DO:
		 * Withdraw:
		 * Check first if there is sufficient amount
		 * If there is sufficient amount, make sure there is no pending payment to make
		 */

		// check if there is sufficient amount
		if (this.balance < x) {
			System.out.println("Cannot withdraw from " + this.name + ", insufficient balance.");
			return false;
		}

		// if there is sufficient amount, make sure there is no pending payment to make
		if (this.hasPending) {
			System.out.println("Cannot withdraw from " + this.name + ", has pending payment.");
			return false;
		}

		// if there is sufficient amount and no pending payment, withdraw
		this.balance -= x;
		System.out.println(x + " withdrawn from " + this.name);
		return true;
	}

	boolean pay(int amountToPay, SupplierClient supplier) {

		/**
		 * TO DO:
		 *
		 * Check first if there is sufficient balance to pay
		 * If insufficient amount
		 * - change pending indicator to true and print necessary message
		 *
		 * - (bonus) make the thread wait
		 * - (bonus) You will need a while loop here that checks until balance is sufficient to pay
		 */
		if (this.balance < amountToPay) {
			// change pending indicator to true and print necessary message
			this.hasPending = true;
			System.out.println("Cannot pay " + supplier.getStoreName() + ", insufficient balance.");
			return false;
		}
		// if there is sufficient balance, pay
		this.hasPending = false;
		this.balance -= amountToPay;
		System.out.println(amountToPay + " paid from " + this.name);
		supplier.bank.completePayment(amountToPay, supplier);
		return true;
		// ===== supposedly bonus but was not able to implement =====
		// synchronized (this) {
		// 	while(this.balance < amountToPay) {
		// 		this.hasPending = true;
		// 		System.out.println("Waiting...");
		// 	// make the thread wait
		// 		while (this.balance < amountToPay) {
		// 			try {
		// 				this.wait();
		// 			} catch (InterruptedException e) {
		// 				e.printStackTrace();
		// 			}
		// 		}
		// 	}
		// 	this.hasPending = false;
		// 	this.balance -= amountToPay;
		// 	System.out.println(amountToPay + " paid from " + this.name);
		// 	return true;
		// }
	}

	synchronized void deposit(int amount){
		this.balance+=amount;
		// this.notify();
		System.out.println(amount + " deposited to " + this.name);
	}

	synchronized void remit(int amount){
		this.balance+=amount;
		System.out.println(amount + " remitted to " + this.name);
	}

	int getBalance(){
		return this.balance;
	}

	void printBalance(){
		System.out.println(this.name + " has " + this.getBalance());
	}
}
