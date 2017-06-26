package enviroment;

import java.util.ArrayList;
import java.util.Random;

public class SendBtc  implements Runnable{

	private BitCoin btc;
	private Server to;
	private Server server;
	private Random random = new Random();

    public SendBtc(BitCoin btc, Server to, Server server){
	   
	   this.btc=btc;
	   this.to=to;
	   this.server=server;
	   
    }
 
	public void run() {

		Transaction transaction = new Transaction(btc, server, to);

		Server activeServer = getActualServer(to, server);

		if (activeServer != null) {

			transaction.setValidator(activeServer); // ustawiamy validatora

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
