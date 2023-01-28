/*
 * This program is a simulation of a bank account application that uses concurrency to manage transactions between a store
 * and supplier clients. It allows for processes such as depositing, withdrawing, and making payment requests to be
 * executed concurrently. It also monitors the balances of the accounts and indicates if an account has a pending balance
 * or it its balance is insufficient to make a payment.
 *
 * @author Reymond P. Yncierto
 * @created 2022-12-25 24:45
 * @finisehd 2022-12-26 22:08
 * */

package main;

import bankinternal.Bank;
import bankinternal.Client;

/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading exercise
 *
 * (c) Institute of Computer Science, CAS, UPLB
 *
 *
 *************************************************************************************************************************/
public class Main {
	public static void main(String[] args){

		Bank bank = new Bank();

		String storeName = bank.createStoreClient("Store #1", 3000);
		bank.createSupplierClient("Supplier #1", 3000, storeName);

		/****
		 * TO DO:
		 * Get bank threads; start each thread
		 */
		for (Client client:bank.getClientThreads()) {
			client.start();
		}

		/****
		 * TO DO:
		 * Make sure all threads have finished before printing bank accounts' balance
		 */
		// all threads must be finished before printing the accounts balance
		for (Client client:bank.getClientThreads()) {
			try {
				client.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		bank.printBalance();
	}
}
