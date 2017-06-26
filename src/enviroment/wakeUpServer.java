package enviroment;

public class wakeUpServer implements Runnable {

	private Server server;
	public wakeUpServer(Server server) {
             this.server=server;
	}

	public void run() {
		// TODO Auto-generated method stub

		server.setActive(true);
		// uzupelnic liste myBitCoin ,Bit coins , block chain

		// 1. wyslac zapytanie to aktualnego servera
		Server serv = getActualServer();
		System.out.println("actual: " + serv.getName());
		if (serv != null) {

			server.setBitCoins(serv.getBitCoins()); 
			int size = server.getBlockChain().size();

			for (int i = size; i < serv.getBlockChain().size(); i++) {

				server.getBlockChain().add(serv.getBlockChain().get(i));

				if (serv.getBlockChain().get(i).getTo() == server && (serv.getBlockChain().get(i).getValidate())) {
					server.getMyBitCoins().add(serv.getBlockChain().get(i).getBtc());
				}
			}

		} else {

			try {
				throw new ProtocolBitCoinDead();
			} catch (ProtocolBitCoinDead e) {
			    e.message();
				e.printStackTrace();
			}

		}
		server.setActual(true);
	}
	public synchronized Server getActualServer() {

		for (Server s : server.getAllServers()) {

			if ((!s.equals(this)) && (s.getActual() == true))
				return s;
		}

		System.out.println("No server is avaliable right now to verify transaction");

		return null;

	}
}
