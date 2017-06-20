package enviroment;

public interface BitCoinManagament {

	public Boolean send(BitCoin btc, Server toName) throws InterruptedException;
	public Boolean verify(Server actSer, BitCoin btc, Server from);
	
}
