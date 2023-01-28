package bankinternal;
import java.util.Random;

/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading exercise
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * 
 *
 *************************************************************************************************************************/
public class StoreClient extends Client {

	private int bankDepositAmount = 350;
	private int bankWithdrawAmount = 350;
	
	public static final int CYCLE_COUNT = 50;
	
	StoreClient(Bank b, BankAccount account, String name){
		this.bank = b;
		this.account = account;
		this.name = name;
	}

	public void run(){
		/**
		 * TO DO
		 * deposit-withdraw cycle
		 * Do this 50 times:
		 * get a random number (0 or 1); 
		 * -if 0, request for deposit of money; use variable this.bankDepositAmount
		 * -if 1, request to process withdrawal; use variable this.bankWithdrawAmount
		 * put interval (or sleep) of 100 ms
		 * 
		 * Bonus: 
		 * Do you need to do anything at the end of the cycle? What if there are pending 
		 * payments, do you need to notify waiting threads?  Hint: You need to send notification
		 * via bank and through bank account.
		 * 
		 */
		for (int i = 0; i<CYCLE_COUNT; i++) {
			Random rand = new Random();
			int n = rand.nextInt(2);
			if (n == 0) {
				this.bank.processDeposit(this, this.bankDepositAmount);
			} else {
				this.bank.processWithdraw(this, this.bankWithdrawAmount);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("\nStore account's deposit-withdraw cycle has ended.\n");
	}
	
}

