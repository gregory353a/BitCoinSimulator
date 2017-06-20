package enviroment;

import java.util.ArrayList;

public class Server implements BitCoinManagament {

	private String name;
	private ArrayList<BitCoin> bitCoins;
	private ArrayList<BitCoin> myBitCoins;
	private Boolean active;
	private Boolean actual;
	private ArrayList<Transaction> blockChain;

	public Server(String name, ArrayList<BitCoin> btc, ArrayList<BitCoin> myBtc) {

		this.name = name;
		this.setBitCoins(btc);
		this.myBitCoins = myBtc;
		this.active = true;
		this.actual = true;

	}
    public void addBitCoin(BitCoin btc){
    	
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

	public Boolean send(BitCoin btc, Server to)  {

		Transaction transaction = new Transaction(btc, this, to);
		
		Server activeServer = AllServers.getActualServer(this,to);
		
		transaction.setValidator(activeServer);
		
		if (!activeServer.equals(this)&&verify(activeServer, btc,this)) {
				
		
			this.myBitCoins.remove(btc);
			BitCoin newBtc= btc;
			newBtc.setOwner(to.name);
			AllServers.actualize(newBtc,btc);
			to.myBitCoins.add(newBtc);
			AllServers.actualizeTransaction(transaction,true);
	
			
			return true;
			
		} else {
			AllServers.actualizeTransaction(transaction,false);
			System.out.println("You can't send bit coin which you don't have!");
		}

		return false;
	}

	public Boolean verify(BitCoin btcVerfy) {

		return this.bitCoins.contains(btcVerfy) ? true : false;

	}


	public ArrayList<BitCoin> getBitCoins() {
		return bitCoins;
	}

	public void setBitCoins(ArrayList<BitCoin> bitCoins) {
		this.bitCoins = bitCoins;
	}

	public Boolean verify(Server actSer, BitCoin btc, Server from) {

		// kruczek : dopoki mi nie zrobi veryfaja nic nie moze zmienic mi server

		ArrayList<BitCoin> all = actSer.getBitCoins();
		return from.getName()==btc.getOwner()&&all.contains(btc) ? true : false;

	}

}
