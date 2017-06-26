package enviroment;

import java.util.ArrayList;
import java.util.Random;

public class SendMoreThen1 implements Runnable {

	private ArrayList<BitCoin> btcs;
	private Server to;
	private Server server;
	private Random random = new Random();
	
	public SendMoreThen1(ArrayList<BitCoin> btcs, Server to, Server server) {
		// TODO Auto-generated constructor stub
		   this.btcs=btcs;
		   this.to=to;
		   this.server=server;
	
	}
	public void run() {

		// ustawiam transakcje dla wszystkich bitcoinow
	
		Server activeServer = getActualServer(to, server);

		if (activeServer != null) {

			for(BitCoin btc: btcs){
				Transaction transaction = new Transaction(btc, server, to);
				transaction.setValidator(activeServer);
			
				// wysyłamy bitCina tylko dla zweryfikowanego użytkownika
				if (activeServer.verify(btc, server)) {

					// zmieniam wlasciciela BitCoina
					btc.setOwner(to.getName());
					// usuwam u siebie Bit coina
					server.getMyBitCoins().remove(btc);

					transaction.setValidate(true);

					sendInformationToOtherServers();
					if (to.getActive())
						to.getMyBitCoins().add(btc);

					server.getBlockChain().add(transaction);
				} else {
					transaction.setValidate(false);
					server.getBlockChain().add(transaction);
					System.out.println("You can't send bit coin which you don't have!");
				}
			
			
			
			}
	

		}

	}


	public synchronized void sendInformationToOtherServers() {

		for (Server serv : server.getAllServers()) {

			if (serv.getActive()) {

				serv.setBitCoins(server.getBitCoins());
				serv.setBlockChain(server.getBlockChain());

			}

		}

	}

	public synchronized Server getActualServer(Server to, Server from) {
		ArrayList<Integer>sequence = new ArrayList<>();
		while(sequence.size()<server.getAllServers().size()){
			int x = random.nextInt(server.getAllServers().size());
			if(!sequence.contains(x))
				sequence.add(x);
		}
		for (int i=0; i<sequence.size(); i++) {

			if ((!server.getAllServers().get(sequence.get(i)).equals(from)) && (!server.getAllServers().get(sequence.get(i)).equals(to) &&
					(server.getAllServers().get(sequence.get(i)).getActual() == true)))
				return server.getAllServers().get(sequence.get(i));
		}

		System.out.println("No server is avaliable right now to verify transaction");

		return null;

	}


}
