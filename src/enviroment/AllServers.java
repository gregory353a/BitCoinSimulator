package enviroment;

import java.util.ArrayList;

public class AllServers {

	public static ArrayList<Server> allServers;
	public static ArrayList<BitCoin> bitCoins;
	public static ArrayList<Transaction> transactions;

	public static Server getActualServer(Server from, Server to) {

		

			for (Server s : allServers) {

				if ((!s.equals(from)) && (s.getActual()) && (!s.equals(to)))
					return s;

			}

		   System.out.println("No server is avaliable right now to verify transaction");
			
		   return from;
		

	}

	public static void actualize(BitCoin btcNew, BitCoin btcOld) {

		bitCoins.remove(btcOld);
		bitCoins.add(btcNew);
		for (Server s : allServers) {

			s.setBitCoins(bitCoins);
		}
	}
	public static void actualizeTransaction(Transaction tr, Boolean valid) {

	          tr.setValidate(valid);
	          transactions.add(tr);
	          
	          for(Server s: allServers){
	        	  
	        	  s.setBlockChain(transactions);
	          }

	}

}
