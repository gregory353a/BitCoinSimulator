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
	public ArrayList<Server> getAllServers() {
		return AllServers;
	}

	public void setAllServers(ArrayList<Server> allServers) {
		AllServers = allServers;
	}



	public Server(String name, ArrayList<BitCoin> btc, ArrayList<BitCoin> myBtc) {

		this.name = name;
		this.setBitCoins(btc);
		this.myBitCoins = myBtc;
		this.active = true;
		this.actual = true;
		this.blockChain = new  ArrayList<>();


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



	public ArrayList<BitCoin> getBitCoins() {
		return bitCoins;
	}

	public void setBitCoins(ArrayList<BitCoin> bitCoins) {
		this.bitCoins = bitCoins;
	}




	public void goSleep() {

		setActive(false);
		setActual(false);
	}

	public void wakeUp() throws ProtocolBitCoinDead {

		Thread thread = new Thread(new wakeUpServer(this));
        thread.start();  
	}

	
	public synchronized void send(BitCoin btc, Server to) {
	        
	
		Thread thread = new Thread(new SendBtc(btc, to , this));
        if(actual==true)thread.start();
   
	
		
		
	}
	public synchronized Boolean verify(BitCoin btc, Server from) {

		
		return from.getName() == btc.getOwner() && bitCoins.contains(btc) ? true : false;

	}


}
