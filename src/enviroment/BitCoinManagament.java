package enviroment;

public interface BitCoinManagament {

	public Boolean send(BitCoin btc, Server to);
	public Boolean verify(BitCoin btc, Server from);
	
}
