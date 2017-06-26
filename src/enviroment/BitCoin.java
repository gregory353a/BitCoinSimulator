package enviroment;

public class BitCoin {


	private Integer btcNumber;
	private String owner;

	public String toString() {
		return "BitCoin [btcNumber=" + btcNumber + ", owner=" + owner + "]";
	}

	
	
	public BitCoin(String owner, Integer btcNum) {
		
		this.owner=owner;
		this.btcNumber=btcNum;
	   
	}

	public Integer getBtcNumber() {
		return btcNumber;
	}

	public void setBtcNumber(Integer btcNumber) {
		this.btcNumber = btcNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
