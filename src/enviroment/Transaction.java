package enviroment;

public class Transaction {

	private BitCoin btc;
	private Server from;
	private Server to;
	private Server validator;
	private Boolean validate; 
	
	
	@Override
	public String toString() {
		return "Transaction [btc=" + btc + ", from=" + from.getName() + ", to=" + to.getName() + ", validator=" + validator.getName() + ", validate="
				+ validate + "]";
	}


	public BitCoin getBtc() {
		return btc;
	}


	public void setBtc(BitCoin btc) {
		this.btc = btc;
	}


	public Server getFrom() {
		return from;
	}


	public void setFrom(Server from) {
		this.from = from;
	}


	public Server getTo() {
		return to;
	}


	public void setTo(Server to) {
		this.to = to;
	}


	public Transaction(BitCoin btc,Server from,Server to) {
	             this.btc=btc;
	             this.from=from;
	             this.to=to;
	             this.validator=null;
	             this.validate=false;
	}


	public Boolean getValidate() {
		return validate;
	}


	public void setValidate(Boolean validate) {
		this.validate = validate;
	}


	public Server getValidator() {
		return validator;
	}


	public void setValidator(Server validator) {
		this.validator = validator;
	}

}
