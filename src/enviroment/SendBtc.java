package enviroment;

public class SendBtc  implements Runnable{

	private BitCoin btc;
	private Server to;
	private Server server;

    public SendBtc(BitCoin btc, Server to, Server server){
	   
	   this.btc=btc;
	   this.to=to;
	   this.server=server;
	   
    }
 
	public void run() {

		Transaction transaction = new Transaction(btc, server, to);

		Server activeServer = getActualServer(to);

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

	public synchronized Server getActualServer(Server to) {

		for (Server s : server.getAllServers()) {

			if ((!s.equals(this)) && (s.getActual() == true) && (!s.equals(to)))
				return s;
		}

		System.out.println("No server is avaliable right now to verify transaction");

		return null;

	}

}
