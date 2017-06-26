package enviroment;

import java.util.ArrayList;

public class Information {

	ArrayList<Server> allServers;
	ArrayList<Transaction> allTransactions;
	ArrayList<BitCoin> allBitCoins;
	// znajdz aktywny server :)

	// informacja na temat wszystkich serverow

	public Information(ArrayList<BitCoin> b, ArrayList<Server> a){
           		
		this.allTransactions=null;
		this.allServers=a;
		this.allBitCoins=b;
		
	}
	
	public ArrayList<Server> getallServers() {
		return allServers;

	}

	// informacja na temat wszystkich aktualnych tranzakcki

	public ArrayList<Transaction> getallTransactions() {

		if (getServer() != null) {
			return getServer().getBlockChain();
		} else
			return null;

	}

	public Server getServer() {

		for (Server sr : allServers) {

			if (sr.getActive()) {
				return sr;
			}

			else {

				int transactions = 0;
				Server serv = null;
				for (Server sr1 : allServers) {

					if (sr1.getBlockChain().size() > transactions) {
						transactions = sr1.getBlockChain().size();
						serv = sr1;
					}
				}
				if (transactions > 0)
					return serv;

			}
		}
		return null;
	}

}
