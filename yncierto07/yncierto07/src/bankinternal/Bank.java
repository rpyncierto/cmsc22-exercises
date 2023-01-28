package bankinternal;
import java.util.ArrayList;


/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading exercise
 *
 * (c) Institute of Computer Science, CAS, UPLB
 *
 *
 *************************************************************************************************************************/

public class Bank {

	private ArrayList<Client> clientList;			// keep a list of all client accounts, regardless of type
	private static int numberOfAccounts;

	public Bank(){
		clientList = new ArrayList<Client>();
	}

	public String createStoreClient(String storeName, int storeBalance) {

		BankAccount account = new BankAccount(storeName, ++Bank.numberOfAccounts, storeBalance, this);
		StoreClient store = new StoreClient(this, account, storeName);
		clientList.add(store);

		return storeName;
	}

	public void createSupplierClient(String supplierName, int supplierBalance, String storeName) {

		BankAccount account = new BankAccount(supplierName, ++Bank.numberOfAccounts, supplierBalance, this);
		SupplierClient supplier = new SupplierClient(this, account, supplierName, storeName);
		clientList.add(supplier);
	}

	public int getNumberofClients() {
		return this.clientList.size();
	}

	public ArrayList<Client> getClientThreads() {
		return clientList;
	}

	Client findClient(String name) {
		for (Client c: clientList) {
			if (c.getClientName().equals(name)) {
				return c;
			}
		}
		return null;
	}

	public void processDeposit(StoreClient store, int amountToDeposit) {
		/****
		 * TO DO:
		 * process deposit
		 */
		store.account.deposit(amountToDeposit);

	}

	public void processWithdraw(StoreClient store, int amountToWithdraw) {
		/****
		 * TO DO:
		 * process withdraw request
		 */
		store.account.withdraw(amountToWithdraw);
	}

	public void requestPayment(SupplierClient supplier, int amountToPay, String storeName) {
		/****
		 * TO DO:
		 * process request of supplier for payment from store
		 */
		System.out.println(supplier.getClientName() + " is requesting payment from " + storeName);

		Client storeClient = this.findClient(storeName);
		if (storeClient.account.getBalance() >= amountToPay) {
			storeClient.account.pay(amountToPay, supplier);
		} else {
			System.out.println("Waiting, will try paying: " + amountToPay + " from " + storeName + " to " + supplier.getClientName() + "; Has only " + storeClient.getBalance());
		}
	}

	public void completePayment(int amountToPay, SupplierClient supplier) {
		/****
		 * TO DO:
		 * this is called from BankAccount class, remit money to supplier
		 */
		supplier.account.remit(amountToPay);
		System.out.println("Payment completed: " + amountToPay + " from " + supplier.getStoreName() + " to " + supplier.getClientName());
	}

	public void printBalance() {

		/****
		 * TO DO:
		 * Display all account balance
		 */
		System.out.println("============================\nBank account information:\n");
		for (Client c : clientList) {
			System.out.println(c.getClientName() + " has " + c.getAccount().getBalance());
		}
		System.out.println("============================");
	}

	// Will need this method to do the bonus
	// public void endCycle() {
	// 	for (Client c : clientList) {
	// 		c.getAccount().notifyThreads();
	// 	}
	// }
}
