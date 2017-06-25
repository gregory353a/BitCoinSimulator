package Gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

public class Simulator {

	private JFrame frame;
	private JTextField toTextField;
	private JTextField howManyTextField;
	private JLayeredPane blockchainPanel;
	private JLayeredPane btcPanel;
	private JLayeredPane serverPanel;
	private ArrayList<JPanel> chainList;
	private ArrayList<JPanel> serverList;
	private ArrayList<JPanel> btcList;
	
	private final int offsetY = 10;
	private final int offsetX = 10;
	private final int serverHeight = 100;
	private final int serverWidth = 240;
	private final int btcHeight = 150;
	private final int btcWidth = 240;
	
	
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
		frame.setBounds(100, 100, 1096, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		serverPanel = new JLayeredPane();
		serverPanel.setBounds(10, 30, 260, 546);
		frame.getContentPane().add(serverPanel);
				
		btcPanel = new JLayeredPane();
		btcPanel.setBounds(280, 30, 260, 546);
		frame.getContentPane().add(btcPanel);
		
		JPanel panelBtc = new JPanel();
		int y = (offsetY + btcHeight)* position + offsetY;
		panelBtc.setBounds(offsetX, y, btcWidth, btcHeight);
		btcPanel.add(panelBtc);
		panelBtc.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblServernumber = new JLabel("serverNumber");
			lblServernumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblServernumber.setFont(new Font("Tahoma", Font.BOLD, 14));
			panelBtc.add(lblServernumber, BorderLayout.NORTH);
			
			JTextPane txtpnInformation = new JTextPane();
			txtpnInformation.setText("Szcz\u0105tkowe informacje o serwerze");
			panelBtc.add(txtpnInformation, BorderLayout.CENTER);
			
			JPanel panelButton = new JPanel();
			panelButton.setBounds(10, 11, 240, 40);
			panelBtc.add(panelButton, BorderLayout.SOUTH);
			panelButton.setLayout(new BorderLayout(0, 0));
			
			{
				
				JButton btnAccident = new JButton("Awaria");
				panelButton.add(btnAccident, BorderLayout.WEST);
				
				JButton btnTurnOn = new JButton("Uruchom");
				panelButton.add(btnTurnOn, BorderLayout.EAST);
				
				JButton btnTracking = new JButton("\u015Aled\u017A");
				panelButton.add(btnTracking, BorderLayout.CENTER);
			}
		}		
		
		blockchainPanel = new JLayeredPane();
		blockchainPanel.setBounds(550, 30, 260, 546);
		frame.getContentPane().add(blockchainPanel);
		
		JPanel choosePanel = new JPanel();
		choosePanel.setBounds(820, 30, 250, 185);
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
	
	private JPanel newServer(int position){
		JPanel panelServer = new JPanel();
		int y = (offsetY + serverHeight)* position + offsetY;
		panelServer.setBounds(offsetX, y, serverWidth, serverHeight);
		panelServer.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblServernumber = new JLabel("serverNumber");
			lblServernumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblServernumber.setFont(new Font("Tahoma", Font.BOLD, 14));
			panelServer.add(lblServernumber, BorderLayout.NORTH);
			
			JTextPane txtpnInformation = new JTextPane();
			txtpnInformation.setText("Szcz\u0105tkowe informacje o serwerze");
			panelServer.add(txtpnInformation, BorderLayout.CENTER);
			
			JPanel panelButton = new JPanel();
			panelButton.setBounds(10, 11, 240, 40);
			panelServer.add(panelButton, BorderLayout.SOUTH);
			panelButton.setLayout(new BorderLayout(0, 0));
			
			{
				
				JButton btnAccident = new JButton("Awaria");
				panelButton.add(btnAccident, BorderLayout.WEST);
				
				JButton btnTurnOn = new JButton("Uruchom");
				panelButton.add(btnTurnOn, BorderLayout.EAST);
				
				JButton btnTracking = new JButton("\u015Aled\u017A");
				panelButton.add(btnTracking, BorderLayout.CENTER);
			}
		}
		return panelServer;
	}
	
}
