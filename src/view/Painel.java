package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import som.Som;
import models.Caixa;
import models.Chamada;
import models.Preferencia;
import facade.Fachada;

import javax.swing.ImageIcon;

public class Painel extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, painelTopo, painelSenha, painelRodape;

	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static Dimension dim = toolkit.getScreenSize();
	JLabel horaData, numeroCaixaReal, calendario;

	private Color backgroundColor = new Color(245, 249, 254);
	private String marqueString;
	private int marqueTime = 100;

	static Marque marque;
	private static String validaFrase = "";

	private String id;
	private int idChamada;
	
	
	private int segundos = 3; 

	private Fachada fachada;
	private ArrayList<Preferencia> preferencia;

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

		fachada = Fachada.getInstance();
		
		preferencia = new ArrayList<Preferencia>();


		try {
			preferencia = (ArrayList<Preferencia>) fachada.listarPreferencia();

			if (preferencia.size() != 0) {

				validaFrase = preferencia.get(0).getTexto();
				marqueString = preferencia.get(0).getTexto();
			} else {
				validaFrase = "Bonanza";
				marqueString = "Bonanza";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
			



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
		horaData.setBounds((painelTopo.getWidth() - 520) - 140, 15, 490, 100);
		painelTopo.add(horaData);

		painelSenha = new JPanel();
		painelSenha.setBackground(new Color(245, 249, 254));

		painelSenha.setBorder(BorderFactory.createLineBorder(new Color(192,
				191, 191)));

		painelSenha.setBounds(80, 250, ((int) dim.getWidth() - (80 + 80)), 300);
		painelSenha.setLayout(null);
		
		/*calendario = new JLabel("");
		calendario.setIcon(new ImageIcon(Painel.class.getResource("/view/img/calendario.png")));
		calendario.setBounds(painelTopo.getWidth() - 147, 0, 147, 130);
		painelTopo.add(calendario);*/

		numeroCaixaReal = new JLabel("Aguarde ...");
		numeroCaixaReal.setForeground(Color.BLACK);
		numeroCaixaReal.setHorizontalAlignment(SwingConstants.CENTER);
		numeroCaixaReal.setFont(new Font("Lucida Grande", Font.BOLD, 170));
		numeroCaixaReal.setBounds(0, 50, painelSenha.getWidth(), 200);
		painelSenha.add(numeroCaixaReal);

		contentPane.add(painelSenha);

		painelRodape = new JPanel();
		painelRodape.setBackground(new Color(245, 249, 254));

		painelRodape.setBorder(BorderFactory.createLineBorder(new Color(192,
				191, 191)));

		painelRodape
				.setBounds(80, 600, ((int) dim.getWidth() - (80 + 80)), 130);
		painelRodape.setLayout(null);


		marque = new Marque(marqueString, marqueTime);
		marque.setBackground(backgroundColor);
		painelRodape.add(marque);
		marque.setBounds(30, 25, ((int) dim.getWidth() - (220)), 100);

		marque.start();

		contentPane.add(painelRodape);
		
		id = "0";
		idChamada = 0;

	}

	// ----------------------------------------------------------------------
	@Override
	public void run() {
		
		fachada = Fachada.getInstance();
		Chamada chamada = new Chamada();
		Caixa caixa = new Caixa();
		
		while (1 == 1) {
		
			try {
			
				preferencia = new ArrayList<Preferencia>();
				
				try {
					preferencia = (ArrayList<Preferencia>) fachada.listarPreferencia();
					
					
					System.out.println(validaFrase);
					System.out.println(preferencia.get(0).getTexto());
					if (!preferencia.get(0).getTexto().equals(validaFrase)) {
						
						validaFrase =preferencia.get(0).getTexto();
						
						marque.stop();
						marque = new Marque(preferencia.get(0).getTexto(), marqueTime);
						marque.setBackground(backgroundColor);
						painelRodape.add(marque);
						marque.setBounds(30, 25, ((int) dim.getWidth() - (220)), 100);
						marque.setToolTipText("dfasdfasdfas");
						marque.start();
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				SimpleDateFormat format = new SimpleDateFormat(
						"dd/MM/yyyy - HH:mm");
						horaData.setText(format.format(new Date()));
						painelTopo.repaint();
						
						
				Thread.sleep(1000);

				try {
					chamada = fachada.retornaSenha();
					caixa = fachada.retornaObjetoCaixa(chamada.getCaixaId());

					 if (!id.equals(caixa.getCaixa())){
						 
						 
						  try{  
							  
							  
							  this.numeroCaixaReal.setText("Caixa Livre "+caixa.getCaixa());
							  
								 Som.play();
								 id = caixa.getCaixa();
								 idChamada =  chamada.getId();
						       for (int i = segundos; i > 0; i--){  
						           System.out.println(i + " segundos");  
						           Thread.sleep(1000); // 1 segundo  
						       }  
						       System.out.println("Terminei!");  
						   } catch (InterruptedException e){  
						       System.out.println("Interromperam meu sono!");  
						   }  

					 }
					 
					 if (id.equals(caixa.getCaixa())){
						 
						 
						 if (chamada.getId() == idChamada+1){
						  try{  
							  
							  
							  this.numeroCaixaReal.setText("Caixa Livre "+caixa.getCaixa());
							  
								 Som.play();
								 id = caixa.getCaixa();
								 idChamada =  chamada.getId();
								 
						       for (int i = segundos; i > 0; i--){  
						           System.out.println(i + " segundos");  
						           Thread.sleep(1000); // 1 segundo  
						       }  
						       System.out.println("Terminei!");  
						   } catch (InterruptedException e){  
						       System.out.println("Interromperam meu sono!");  
						   }  
						  
						 }
						 else{
							 this.numeroCaixaReal.setText("Aguarde...");
						 }
						 
					 }
					 else{
						 this.numeroCaixaReal.setText("Aguarde..."); 
					 }

				} catch (SQLException e) {
					e.printStackTrace();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}