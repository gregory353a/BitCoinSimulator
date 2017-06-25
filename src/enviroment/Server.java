package enviroment;

import java.util.ArrayList;

public class Server implements BitCoinManagament {

	private String name;
	private ArrayList<BitCoin> bitCoins;
	private ArrayList<BitCoin> myBitCoins;
	private ArrayList<Transaction> blockChain;
	private ArrayList<Server> AllServers;
	private Boolean active;
	private Boolean actual;

	public Server(String name, ArrayList<BitCoin> btc, ArrayList<BitCoin> myBtc) {

		this.name = name;
		this.setBitCoins(btc);
		this.myBitCoins = myBtc;
		this.active = true;
		this.actual = true;

	}

	public void addBitCoin(BitCoin btc) {

		myBitCoins.add(btc);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<BitCoin> getMyBitCoins() {
		return myBitCoins;
	}

	public void setMyBitCoins(ArrayList<BitCoin> myBitCoins) {
		this.myBitCoins = myBitCoins;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getActual() {
		return actual;
	}

	public void setActual(Boolean actual) {

		this.actual = actual;
	}

	public ArrayList<Transaction> getBlockChain() {
		return blockChain;
	}

	public void setBlockChain(ArrayList<Transaction> blockChain) {
		this.blockChain = blockChain;
	}

	public Boolean send(BitCoin btc, Server to) {

		Transaction transaction = new Transaction(btc, this, to);

		Server activeServer = getActualServer(to);

		if (activeServer != null) {

			transaction.setValidator(activeServer); // ustawiamy validatora

			// wysyłamy bitCina tylko dla zweryfikowanego użytkownika
			if (activeServer.verify(btc, this)) {

				// zmieniam wlasciciela BitCoina
				btc.setOwner(to.getName());
				// usuwam u siebie Bit coina
				myBitCoins.remove(btc);

				transaction.setValidate(true);
				blockChain.add(transaction);

				return true;

			} else {
				transaction.setValidate(false);
				blockChain.add(transaction);
				System.out.println("You can't send bit coin which you don't have!");
			}

		}


		return false;
	}
	public void sendInformationToOtherServers(){
		      
		  for(Server serv: AllServers){
			  
			  if(serv.getActive()){
				  
				  serv.setBitCoins(bitCoins);
				  serv.setBlockChain(blockChain);
				  
			  }
			  
		  }	
		
	}

	public ArrayList<BitCoin> getBitCoins() {
		return bitCoins;
	}

	public void setBitCoins(ArrayList<BitCoin> bitCoins) {
		this.bitCoins = bitCoins;
	}

	public Boolean verify(BitCoin btc, Server from) {

		// TODO
		// kruczek : dopoki mi nie zrobi veryfaja nic nie moze zmienic mi server
		return from.getName() == btc.getOwner() && bitCoins.contains(btc) ? true : false;

	}

	public Server getActualServer(Server to) {

		for (Server s : AllServers) {

			if ((!s.equals(this)) && (s.actual == true) && (!s.equals(to)))
				return s;
		}

		System.out.println("No server is avaliable right now to verify transaction");

		return null;

	}

	public Server getActualServer() {

		for (Server s : AllServers) {

			if ((!s.equals(this)) && (s.actual == true))
				return s;
		}

		System.out.println("No server is avaliable right now to verify transaction");

		return null;

	}

	public void goSleep() {

		setActive(false);
		setActual(false);
	}

	public void wakeUp() throws ProtocolBitCoinDead {

		setActive(true);
		// uzupelnic liste myBitCoin ,Bit coins , block chain

		// 1. wyslac zapytanie to aktualnego servera
		Server serv = getActualServer();

		if (serv != null) {

			bitCoins = serv.getBitCoins();
			int size = myBitCoins.size();

			for (int i = size - 1; i < serv.getBlockChain().size(); i++) {

				this.blockChain.add(serv.getBlockChain().get(i));
				if (serv.getBlockChain().get(i).getTo() == this && (serv.getBlockChain().get(i).getValidate())) {
					this.myBitCoins.add(serv.getBlockChain().get(i).getBtc());
				}
			}

		} else {

			throw new ProtocolBitCoinDead();

		}

	}

}
