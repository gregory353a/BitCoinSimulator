package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Simulator {

	private JFrame frame;
	private JTextField toTextField;
	private JTextField howManyTextField;

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
		
		JPanel serverPanel = new JPanel();
		serverPanel.setBounds(10, 30, 260, 546);
		frame.getContentPane().add(serverPanel);
		
		JPanel btcPanel = new JPanel();
		btcPanel.setBounds(280, 30, 260, 546);
		frame.getContentPane().add(btcPanel);
		
		JPanel blockchainPanel = new JPanel();
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
}
