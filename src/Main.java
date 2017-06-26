import java.awt.EventQueue;
import java.util.ArrayList;
import enviroment.BitCoin;
import enviroment.ProtocolBitCoinDead;
import enviroment.Server;
import Gui.Simulator;

public class Main {
	
	public static void main(String[] args) throws ProtocolBitCoinDead {
		init()
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simulator window = new Simulator();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		System.out.println("zakoñczono");
	}


	private static void initialize() throws ProtocolBitCoinDead {
		ArrayList<BitCoin> s1Btc = new ArrayList<>();
		BitCoin b1=new BitCoin("s1", 1);
		BitCoin b2=new BitCoin("s1", 2);
		BitCoin b3=new BitCoin("s2", 3);
		BitCoin b4=new BitCoin("s2", 4);
		BitCoin b5=new BitCoin("s3", 5);
		BitCoin b6=new BitCoin("s3", 6);
		s1Btc.add(b1);
		s1Btc.add(b2);
		ArrayList<BitCoin> s2Btc = new ArrayList<>();
		s2Btc.add(b3);
		s2Btc.add(b4);
		ArrayList<BitCoin> s3Btc = new ArrayList<>();
		s3Btc.add(b5);
		s3Btc.add(b6);
		ArrayList<BitCoin> all = new ArrayList<>();
		all.addAll(s1Btc);
		all.addAll(s2Btc);
		all.addAll(s3Btc);
      
		Server s1 = new Server("s1", all, s1Btc);
		Server s2 = new Server("s2", all, s2Btc);
		Server s3 = new Server("s3", all, s3Btc);
	    ArrayList<Server> allS= new ArrayList<Server>();
	    s1.setAllServers(allS);
	    s2.setAllServers(allS);
	    s3.setAllServers(allS);
        allS.add(s1);
        allS.add(s2);
        allS.add(s3);
    	ArrayList<BitCoin> allBtc= new ArrayList<>();
        allBtc.add(b1);
        allBtc.add(b2);
        allBtc.add(b3);
        allBtc.add(b4);
        allBtc.add(b5);
        allBtc.add(b6);
	
		System.out.println("s1 Bit coins: "+s1.getMyBitCoins());
		System.out.println("s2 Bit coins: "+s2.getMyBitCoins());
		System.out.println("s3 Bit coins: "+s3.getMyBitCoins());
		System.out.println("Po wysÅ‚aniu BitCoina !!!!!!!!");
 	
    
        s2.send(b1, s1);

  	
		System.out.println("s1 Bit coins: "+s1.getMyBitCoins());
		System.out.println("s2 Bit coins: "+s2.getMyBitCoins());
		System.out.println("s3 Bit coins: "+s3.getMyBitCoins());
	
	}

}
