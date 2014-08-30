package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Painel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, painelTopo, painelSenha, painelRodape;
	
	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static Dimension dim = toolkit.getScreenSize();
	JLabel horaData, numeroCaixaReal;
	
	private Color backgroundColor = Color.WHITE;
	private String marqueString = "Bonanza Supermercados - O melhor preço está aqui !";
	private int marqueTime = 100;

	static Marque marque;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					Painel frame = new Painel();
					frame.setVisible(true);
					
				}
				
				catch (Exception e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		});
		
	}

	public Painel() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Painel - Bonanza Supermercados");
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		painelTopo = new JPanel();
		painelTopo.setBackground(new Color(245, 249, 254));
		
		painelTopo.setBorder(BorderFactory.createLineBorder(new Color(192, 191,
				191)));
		
		painelTopo.setBounds(80, 70, ((int) dim.getWidth() - (80 + 80)), 130);
		painelTopo.setLayout(null);
		
		contentPane.add(painelTopo);
		
		JLabel fraseInicio = new JLabel("BONANZA SUPERMERCADOS");
		fraseInicio.setForeground(Color.BLACK);
		fraseInicio.setHorizontalAlignment(SwingConstants.LEFT);
		fraseInicio.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		fraseInicio.setBounds(30, 15, 600, 100);
		painelTopo.add(fraseInicio);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		horaData = new JLabel(format.format(new Date()));
		horaData.setForeground(Color.BLACK);
		horaData.setHorizontalAlignment(SwingConstants.RIGHT);
		horaData.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		horaData.setBounds((painelTopo.getWidth() - 520) - 10, 15, 490, 100);
		painelTopo.add(horaData);
		
		painelSenha = new JPanel();
		painelSenha.setBackground(new Color(245, 249, 254));
		
		painelSenha.setBorder(BorderFactory.createLineBorder(new Color(192, 191,
				191)));
		
		painelSenha.setBounds(80, 250, ((int) dim.getWidth() - (80 + 80)), 300);
		painelSenha.setLayout(null);
		
		numeroCaixaReal = new JLabel("000");
		numeroCaixaReal.setForeground(Color.BLACK);
		numeroCaixaReal.setHorizontalAlignment(SwingConstants.CENTER);
		numeroCaixaReal.setFont(new Font("Lucida Grande", Font.BOLD, 160));
		numeroCaixaReal.setBounds(0, 50, painelSenha.getWidth(), 200);
		painelSenha.add(numeroCaixaReal);
		
		contentPane.add(painelSenha);
		
		painelRodape = new JPanel();
		painelRodape.setBackground(new Color(245, 249, 254));
		
		painelRodape.setBorder(BorderFactory.createLineBorder(new Color(192, 191,
				191)));
		
		painelRodape.setBounds(80, 600, ((int) dim.getWidth() - (80 + 80)), 130);
		painelRodape.setLayout(null);
		
		marque = new Marque(marqueString, marqueTime);
		marque.setBackground(backgroundColor);
		painelRodape.add(marque);
		
		marque.setBounds(30, 25,
				((int) dim.getWidth() - (220)), 100);
		
		marque.start();
		contentPane.add(painelRodape);
		
	}

}