package Gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import enviroment.BitCoin;
import enviroment.Server;

import javax.swing.JLayeredPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

public class Simulator {

	private JFrame frame;
	private JTextField toTextField;
	private JTextField howManyTextField;
	private JLayeredPane blockchainPanel;
	private JLayeredPane btcPanel;
	private JLayeredPane serverPanel;
	private ArrayList<JPanel> chainList = new ArrayList<>();
	private ArrayList<JPanel> serverList = new ArrayList<>();
	private ArrayList<JPanel> btcList = new ArrayList<>();
	
	private final int offsetY = 5;
	private final int offsetX = 10;
	private final int serverHeight = 105;
	private final int serverWidth = 240;
	private final int btcHeight = 35;
	private final int btcWidth = 240;
	private final int blockChainHeight = 35;
	private final int blochChainWidth = 240;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simulator window = new Simulator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Simulator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1108, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		serverPanel = new JLayeredPane();
		serverPanel.setBounds(10, 30, 260, 621);
		frame.getContentPane().add(serverPanel);
				
		btcPanel = new JLayeredPane();
		btcPanel.setBounds(280, 30, 260, 621);
		frame.getContentPane().add(btcPanel);	
		
		blockchainPanel = new JLayeredPane();
		blockchainPanel.setBounds(550, 30, 260, 621);
		frame.getContentPane().add(blockchainPanel);
		
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
		
		for(int i= 0; i<10; i++){
		serverPanel.add(newServer(i, new Server("hello"+i, all, s1Btc)));
		blockchainPanel.add(newBlockChain(i));
		}
		
		JPanel choosePanel = new JPanel();
		choosePanel.setBounds(820, 30, 260, 185);
		frame.getContentPane().add(choosePanel);
		
		toTextField = new JTextField();
		toTextField.setBounds(910, 236, 86, 20);
		frame.getContentPane().add(toTextField);
		toTextField.setColumns(10);
		
		howManyTextField = new JTextField();
		howManyTextField.setBounds(910, 257, 86, 20);
		frame.getContentPane().add(howManyTextField);
		howManyTextField.setColumns(10);
		
		JButton btnWylij = new JButton("Wy\u015Blij");
		btnWylij.setBounds(910, 280, 86, 23);
		frame.getContentPane().add(btnWylij);
		
		JLabel lblDo = new JLabel("Do");
		lblDo.setBounds(854, 239, 46, 14);
		frame.getContentPane().add(lblDo);
		
		JLabel lblIle = new JLabel("Ile");
		lblIle.setBounds(854, 260, 46, 14);
		frame.getContentPane().add(lblIle);
		
		JLabel lblSerwery = new JLabel("Serwery");
		lblSerwery.setHorizontalAlignment(SwingConstants.CENTER);
		lblSerwery.setBounds(10, 5, 260, 20);
		frame.getContentPane().add(lblSerwery);
		
		JLabel lblBtc = new JLabel("BTC");
		lblBtc.setHorizontalAlignment(SwingConstants.CENTER);
		lblBtc.setBounds(280, 5, 260, 20);
		frame.getContentPane().add(lblBtc);
		
		JLabel lblBlockchain = new JLabel("Blockchain");
		lblBlockchain.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlockchain.setBounds(550, 5, 260, 20);
		frame.getContentPane().add(lblBlockchain);
		
		JLabel lblWybrano = new JLabel("Wybrano:");
		lblWybrano.setBounds(820, 5, 250, 20);
		frame.getContentPane().add(lblWybrano);
	}
	
	//TODO
	private void setServerList(){
	//	serverList = ;
	}

	private void setBtcListPanel(Server server){
		btcList.clear();
		for(int i=0; i<server.getMyBitCoins().size(); i++){
		btcList.add(newBtc(i, server.getMyBitCoins().get(i)));	
		}
	}
	
	//TODO
	private JPanel newBlockChain(int position) {
		JPanel panelBtc = new JPanel();
		int y = (offsetY + blockChainHeight)* position + offsetY;
		panelBtc.setBounds(offsetX, y, blochChainWidth, blockChainHeight);
		panelBtc.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblServernumber = new JLabel("chainNumber");
			lblServernumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblServernumber.setFont(new Font("Tahoma", Font.BOLD, 12));
			panelBtc.add(lblServernumber, BorderLayout.NORTH);
			
			JTextPane txtpnInformation = new JTextPane();
			txtpnInformation.setText("Informacje kto, co, kiedy");
			panelBtc.add(txtpnInformation, BorderLayout.CENTER);
		}
		return panelBtc;
	}	
	
	private JPanel newBtc(int position, BitCoin btc) {
		JPanel panelBtc = new JPanel();
		int y = (offsetY + btcHeight)* position + offsetY;
		panelBtc.setBounds(offsetX, y, btcWidth, btcHeight);
		panelBtc.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblServernumber = new JLabel("btcNumber");
			lblServernumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblServernumber.setFont(new Font("Tahoma", Font.BOLD, 14));
			panelBtc.add(lblServernumber, BorderLayout.NORTH);
			
			JTextPane txtpnInformation = new JTextPane();
			txtpnInformation.setText("W³aœciciel bitcoina: " + btc.getOwner());
			panelBtc.add(txtpnInformation, BorderLayout.CENTER);
		}
		return panelBtc;
	}
	
	private JPanel newServer(int position, Server server){
		JPanel panelServer = new JPanel();
		int y = (offsetY + serverHeight)* position + offsetY;
		panelServer.setBounds(offsetX, y, serverWidth, serverHeight);
		panelServer.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblServernumber = new JLabel(server.getName());
			lblServernumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblServernumber.setFont(new Font("Tahoma", Font.BOLD, 14));
			panelServer.add(lblServernumber, BorderLayout.NORTH);
			
			JTextPane txtpnInformation = new JTextPane();
			txtpnInformation.setContentType("text/html");
			txtpnInformation.setText("Serwer jest aktywny: " + server.getActive() +
					"<br>Serwer jest uruchomiony" + server.getActual() + 
					"<br>Serwer posiada: " +  server.getMyBitCoins().size() + " BTC");
			txtpnInformation.setEditable(false);
			panelServer.add(txtpnInformation, BorderLayout.CENTER);
			
			JPanel panelButton = new JPanel();
			panelButton.setBounds(10, 11, 240, 40);
			panelServer.add(panelButton, BorderLayout.SOUTH);
			panelButton.setLayout(new BorderLayout(0, 0));
			
			{
				
				JButton btnAccident = new JButton("Awaria");
				panelButton.add(btnAccident, BorderLayout.WEST);
				btnAccident.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						server.setActive(false);
						refresch();
					}
				});
	
				JButton btnTurnOn = new JButton("Uruchom");
				panelButton.add(btnTurnOn, BorderLayout.EAST);
				btnTurnOn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						server.setActual(true);
						refresch();
					}
				});
				
				JButton btnTracking = new JButton("\u015Aled\u017A");
				panelButton.add(btnTracking, BorderLayout.CENTER);
				btnTracking.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setBtcListPanel(server);
						refresch();
					}
				});
			}
		}
		return panelServer;
	}
	private void refresch(){
		btcPanel.removeAll();
		for(int i=0; i<btcList.size(); i++){
			btcPanel.add(btcList.get(i));
		}
	}
}
