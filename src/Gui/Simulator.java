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
import enviroment.Transaction;
import enviroment.Information;
import enviroment.ProtocolBitCoinDead;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

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
	private JPanel choosePanel;
	private JTextPane txtpnInformacje;
	private JLabel lblWybranySerwer;
	JButton btnSetActive;
	
	private ArrayList<BitCoin> btcList1 = new ArrayList<>();
	private ArrayList<BitCoin> btcList2 = new ArrayList<>();
	private ArrayList<BitCoin> btcList3 = new ArrayList<>();
	private ArrayList<BitCoin> btcList4 = new ArrayList<>();
	private ArrayList<BitCoin> btcList5 = new ArrayList<>();
	private ArrayList<BitCoin> btcListAll = new ArrayList<>();
	private ArrayList<Server> servers = new ArrayList<>();
	
	private Information info;
	
	private final int offsetY = 5;
	private final int offsetX = 10;
	private final int serverHeight = 105;
	private final int serverWidth = 240;
	private final int btcHeight = 35;
	private final int btcWidth = 240;
	private final int blockChainHeight = 70;
	private final int blockChainWidth = 240;
	
	
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
		init();
		info = new Information(btcListAll, servers);
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
		
		choosePanel = new JPanel();
		choosePanel.setBounds(820, 30, 260, 185);
		frame.getContentPane().add(choosePanel);
		choosePanel.setLayout(new BorderLayout(0, 0));
		
		toTextField = new JTextField();
		toTextField.setBounds(910, 236, 86, 20);
		frame.getContentPane().add(toTextField);
		toTextField.setColumns(10);
		
		howManyTextField = new JTextField();
		howManyTextField.setBounds(910, 257, 86, 20);
		frame.getContentPane().add(howManyTextField);
		howManyTextField.setColumns(10);
		
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
		lblBtc.setBounds(280, 5, 184, 20);
		frame.getContentPane().add(lblBtc);
		
		JLabel lblBlockchain = new JLabel("Blockchain");
		lblBlockchain.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlockchain.setBounds(550, 5, 260, 20);
		frame.getContentPane().add(lblBlockchain);
		
		JLabel lblWybrano = new JLabel("Wybrano:");
		lblWybrano.setBounds(820, 5, 164, 20);
		frame.getContentPane().add(lblWybrano);
		
		JButton btnWyczy =new JButton("Wyczy\u015B\u0107");
		btnWyczy.setBounds(451, 4, 89, 23);
		frame.getContentPane().add(btnWyczy);
		btnWyczy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcPanel.removeAll();
				btcList.clear();
				btcPanel.repaint();
			}
		});
		
		//TODO
		JButton btnWylij = new JButton("Wy\u015Blij");
		btnWylij.setBounds(910, 280, 86, 23);
		frame.getContentPane().add(btnWylij);

		btnWylij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("wysy³anie");
				String reciver = toTextField.getText();
				try{
					Integer.parseInt(howManyTextField.getText());
					if(((!howManyTextField.getText().isEmpty())&&(!reciver.isEmpty())))
						for(int i=0; i<Integer.parseInt(howManyTextField.getText()); i++){
							for(Server r : info.getallServers()){
								if(r.getName().contains(reciver)){
									Boolean ready = false;
									for(Server s : info.getallServers()){
										if(s.getName().contains(lblWybranySerwer.getText())){
											ready = true;
											if(!s.getMyBitCoins().isEmpty())
												s.send(s.getMyBitCoins().get(0), r);
											else{
												JOptionPane.showMessageDialog(null,
												        "Brak bitcoinów", "B³¹d",
												        JOptionPane.ERROR_MESSAGE);
												break;
											}
										}
									}
									if(!ready){
										JOptionPane.showMessageDialog(null,
										        "B³êdna nazwa odbiorcy", "B³¹d",
										        JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						}
				}
				catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(null,
					        "B³êdna liczba","B³¹d",
					        JOptionPane.ERROR_MESSAGE);
				}
				setBlockChainList();
				refresh();
			}
		});
		
		btnSetActive = new JButton("Aktywuj");
		btnSetActive.setBounds(994, 4, 86, 23);
		frame.getContentPane().add(btnSetActive);
		btnSetActive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Server s : info.getallServers()){
					if(s.getName().contains(lblWybranySerwer.getText())){
						try {
							s.wakeUp();
							refresh();
						} catch (ProtocolBitCoinDead e1) {
							JOptionPane.showMessageDialog(null,
							        "Brak wystarczaj¹cej iloœci wêz³ów do wiarygodnego wznowienia blockchaina", "B³¹d",
							        JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		refresh();
	}
	
	private void setServerList(){
		serverList.clear();
		for(int i=0; i<servers.size(); i++){
			serverList.add(newServer(i, servers.get(i)));
		}
	}

	private void setBlockChainList(){
		chainList.clear();
		for(int i=0; i<info.getallTransactions().size(); i++){
			chainList.add(newBlockChain(i, info.getallTransactions().get(info.getallTransactions().size()-1-i)));
		}
	}
	
	private void setBtcListPanel(Server server){
		btcList.clear();
		for(int i=0; i<server.getMyBitCoins().size(); i++){
			btcList.add(newBtc(i, server.getMyBitCoins().get(i)));	
		}
	}
	
	private JPanel newBlockChain(int position, Transaction chain) {
		JPanel panelBtc = new JPanel();
		int y = (offsetY + blockChainHeight)* position + offsetY;
		panelBtc.setBounds(offsetX, y, blockChainWidth, blockChainHeight);
		panelBtc.setLayout(new BorderLayout(0, 0));
		{
			String transact = (chain.getFrom() + " " + chain.getBtc() + " " + chain.getTo() + chain.getDate());
			JLabel lblServernumber = new JLabel(transact.hashCode() + "");
			lblServernumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblServernumber.setFont(new Font("Tahoma", Font.BOLD, 12));
			panelBtc.add(lblServernumber, BorderLayout.NORTH);
			
			JTextPane txtpnInformation = new JTextPane();
			txtpnInformation.setContentType("text/html");
			txtpnInformation.setText("Bitcoin nr:" + chain.getBtc().getBtcNumber() + 
					" wys³a³ " + chain.getFrom().getName() +
					" do " + chain.getTo().getName() + 
					" zatwierdzi³ " + chain.getValidator().getName() +
					" zatwierdzono " + chain.getValidate());
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
			JLabel lblServernumber = new JLabel("btcNumber:" + btc.getBtcNumber());
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
			txtpnInformation.setText("Serwer " + (server.getActive()?"":"nie ") + "jest aktywny: " +
					"<br>Serwer " + (server.getActual()?"":"nie ") + "jest uruchomiony" + 
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
						//server.setActive(false);
						server.goSleep();
						txtpnInformation.setText("Serwer " + (server.getActive()?"":"nie ") + "jest aktywny: " +
								"<br>Serwer " + (server.getActual()?"":"nie ") + "jest uruchomiony" + 
								"<br>Serwer posiada: " +  server.getMyBitCoins().size() + " BTC");
						choosePanel.removeAll();
						choosePanel.repaint();
						refresh();
					}
				});
	
				JButton btnTurnOn = new JButton("Uruchom");
				panelButton.add(btnTurnOn, BorderLayout.EAST);
				btnTurnOn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						server.setActual(true);
						txtpnInformation.setText("Serwer " + (server.getActive()?"":"nie ") + "jest aktywny: " +
								"<br>Serwer " + (server.getActual()?"":"nie ") + "jest uruchomiony" + 
								"<br>Serwer posiada: " +  server.getMyBitCoins().size() + " BTC");
						choosePanel.removeAll();
						choosePanel.repaint();
						refresh();
					}
				});
				
				JButton btnTracking = new JButton("\u015Aled\u017A");
				panelButton.add(btnTracking, BorderLayout.CENTER);
				btnTracking.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						choosePanel.removeAll();
						fillChoosePane();
						lblWybranySerwer.setText(server.getName());
						txtpnInformacje.setText("Serwer " + (server.getActive()?"":"nie ") + "jest aktywny: " +
								"<br>Serwer " + (server.getActual()?"":"nie ") + "jest uruchomiony" + 
								"<br>Serwer posiada: " +  server.getMyBitCoins().size() + " BTC");
						choosePanel.repaint();;
						setBtcListPanel(server);
						refresh();
					}
				});
			}
		}
		return panelServer;
	}
	
	private void fillChoosePane(){
		lblWybranySerwer = new JLabel("");
		lblWybranySerwer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWybranySerwer.setHorizontalAlignment(SwingConstants.CENTER);
		choosePanel.add(lblWybranySerwer, BorderLayout.NORTH);
		
		txtpnInformacje = new JTextPane();
		txtpnInformacje.setContentType("text/html");
		txtpnInformacje.setText("");
		choosePanel.add(txtpnInformacje, BorderLayout.CENTER);
	}
	
	private void refresh(){
		setBlockChainList();
		blockchainPanel.removeAll();
		for(int i=0; i<chainList.size(); i++){
			blockchainPanel.add(chainList.get(i));
		}
		setServerList();
		serverPanel.removeAll();
		for(int i=0; i<serverList.size(); i++){
			serverPanel.add(serverList.get(i));
		}
		serverPanel.repaint();
		btcPanel.removeAll();
		for(int i=0; i<btcList.size(); i++){
			if(!btcList.isEmpty())
				btcPanel.add(btcList.get(i));
		}
		
	}

	public JFrame getFrame() {
		return frame;
	}

	private void init(){
		for(int i = 0; i< 10; i++){
			btcList1.add(new BitCoin("Alice", i));
		}
		for(int i = 10; i< 20; i++){
			btcList2.add(new BitCoin("Bob", i));			
		}
		for(int i = 20; i< 30; i++){
			btcList3.add(new BitCoin("Carol", i));
		}
		for(int i = 30; i< 40; i++){
			btcList4.add(new BitCoin("Dave", i));
		}
		for(int i = 40; i< 50; i++){
			btcList5.add(new BitCoin("Ewa", i));
		}
		btcListAll.addAll(btcList1);
		btcListAll.addAll(btcList2);
		btcListAll.addAll(btcList3);
		btcListAll.addAll(btcList4);
		btcListAll.addAll(btcList5);
		
		servers.add(new Server("Alice", btcListAll, btcList1));
		servers.add(new Server("Bob", btcListAll, btcList2));
		servers.add(new Server("Carol", btcListAll, btcList3));
		servers.add(new Server("Dave", btcListAll, btcList4));
		servers.add(new Server("Ewa", btcListAll, btcList5));
		
		for(Server s : servers){
			s.setAllServers(servers);
		}
	}
}
