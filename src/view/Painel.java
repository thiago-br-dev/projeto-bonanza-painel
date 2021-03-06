package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import models.Caixa;
import models.Chamada;
import som.Som;
import facade.Fachada;

public class Painel extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, painelTopo, painelSenha;

	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static Dimension dim = toolkit.getScreenSize();
	JLabel horaData, numeroCaixaReal, calendario;

	private int marqueTime = 100;

	static JLabel fundo_frase;
	private Marque marque;
	private static String validaFrase = "";

	private String id;
	private int idChamada;

	private int segundos = 3;

	private Fachada fachada;
	private JLabel label_1;
	private JLabel label_2;

	public Painel() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Painel.class.getResource("/view/img/icon_screen.png")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setTitle("Painel - Bonanza Supermercados");
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		painelSenha = new JPanel();
		painelSenha.setBackground(new Color(245, 249, 254));
		painelSenha.setBounds(0, 225,
				1280, 189);
		painelSenha.setLayout(null);

		/*
		 * calendario = new JLabel(""); calendario.setIcon(new
		 * ImageIcon(Painel.class.getResource("/view/img/calendario.png")));
		 * calendario.setBounds(painelTopo.getWidth() - 147, 0, 147, 130);
		 * painelTopo.add(calendario);
		 */

		numeroCaixaReal = new JLabel("NENHUMA SOLICITACAO");
		numeroCaixaReal.setForeground(Color.WHITE);
		numeroCaixaReal.setHorizontalAlignment(SwingConstants.CENTER);
		numeroCaixaReal.setFont(new Font("DS-Digital", numeroCaixaReal.getFont().getStyle() & ~Font.BOLD & ~Font.ITALIC, 80));
		numeroCaixaReal.setBounds(0, -11, 794, 200);
		painelSenha.add(numeroCaixaReal);

		contentPane.add(painelSenha);

		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Painel.class
				.getResource("/view/img/fundo_senha_caixa.jpg")));
		label_1.setBounds(0, -11, 900, 200);
		painelSenha.add(label_1);

		
		fundo_frase = new JLabel();
		fundo_frase.setIcon(new ImageIcon(Painel.class.getResource("/view/img/fundo_frase.png")));
		fundo_frase.setBounds(0, (int) dim.getHeight() - 206, ((int) dim.getWidth()), 206);
		contentPane.add(fundo_frase);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Painel.class.getResource("/view/img/bonanza_logo_media.png")));
		label_2.setBounds(108, 111, 595, 73);
		contentPane.add(label_2);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		horaData = new JLabel(format.format(new Date()));
		horaData.setForeground(Color.WHITE);
		horaData.setHorizontalAlignment(SwingConstants.CENTER);
		horaData.setFont(new Font("DS-Digital", horaData.getFont().getStyle() & ~Font.BOLD & ~Font.ITALIC, 50));
		horaData.setBounds(0, 0, 784, 100);
		contentPane.add(horaData);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Painel.class
				.getResource("/view/img/background_painel.jpg")));
		label.setBounds(0, 0, 1920, 1080);
		contentPane.add(label);

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

				SimpleDateFormat format = new SimpleDateFormat(
						"dd/MM/yyyy - HH:mm");
				horaData.setText(format.format(new Date()));

				contentPane.repaint();
				

				try {
					
					
					Thread.sleep(1000);
					chamada = fachada.retornaSenha();
					
					
					if (chamada != null){

						caixa = fachada.retornaObjetoCaixa(chamada.getCaixaId());
						
					if (!id.equals(caixa.getCaixa())) {

						try {

							this.numeroCaixaReal.setText("SOLICITACAO CAIXA - "
									+ caixa.getCaixa());

							Som.play();
							id = caixa.getCaixa();
							idChamada = chamada.getId();
							
							//aqui
							//-------------------------------------------------------------------------------
							 try { 
								 URL url = new URL("http://www.tecksoft.com.br/gcm/gcm_engine.php"); 
								 URLConnection conn = url.openConnection(); 

								 //POST DATA 
								 String data = URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode("Solicitação Caixa " + caixa.getCaixa(), "UTF-8"); 
								 data += "&" + URLEncoder.encode("apiKey", "UTF-8") + "=" + URLEncoder.encode("AIzaSyDWY_6iTpTBAoYEI_EhsRYdBN-1luIdyL8", "UTF-8"); 
								 data += "&" + URLEncoder.encode("registrationIDs", "UTF-8") + "=" + URLEncoder.encode("APA91bE7QEcm7NAg85Ztc4-g_nBz2UAp2ozdF2rB5LDaudFNE16POxbYCgHKUP17VG4M8ivzDf9700IJ70i4ZgJT1ehtK9qIvIg9Ps54V1AbuSYX6bkIPBtE7kTVAiw8uG_BPGKaekbdl2rZlK8Qz5RXWYCehgCFUtMuM12emLJmx5_vQ4zMMvg", "UTF-8"); 

								 conn.setDoOutput(true); 
								 OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
								 wr.write(data); 
								 wr.flush(); 

								 BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
								 String line; 
								 while ((line = rd.readLine()) != null) { 
								 System.out.println(line); 
								 } 
								 wr.close(); 
								 rd.close(); 
								 } catch (Exception e) { 
								 }
							
							//--------------------------------------------------------------------------------
							
							
							
							for (int i = segundos; i > 0; i--) {
								System.out.println(i + " segundos");
								Thread.sleep(1000); // 1 segundo
							}
						} catch (InterruptedException e) {
							System.out.println("Interromperam meu sono!");
						}

					}

					if (id.equals(caixa.getCaixa())) {

						if (chamada.getId() == idChamada + 1) {
							try {

								this.numeroCaixaReal.setText("SOLICITACAO CAIXA - "
										+ caixa.getCaixa());

								Som.play();
								id = caixa.getCaixa();
								idChamada = chamada.getId();

								//aqui
								//-------------------------------------------------------------------------------
								 try { 
									 URL url = new URL("http://www.tecksoft.com.br/gcm/gcm_engine.php"); 
									 URLConnection conn = url.openConnection(); 

									 //POST DATA 
									 String data = URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode("Solicitação Caixa " + caixa.getCaixa(), "UTF-8"); 
									 data += "&" + URLEncoder.encode("apiKey", "UTF-8") + "=" + URLEncoder.encode("AIzaSyDWY_6iTpTBAoYEI_EhsRYdBN-1luIdyL8", "UTF-8"); 
									 data += "&" + URLEncoder.encode("registrationIDs", "UTF-8") + "=" + URLEncoder.encode("APA91bFGXAfmCn180RiKCTEtxvVQ7l51IQMQVvByRTJDklSqXwP9L5O-GRhnwsvZPflvvLr6Im78CFSapjigqy2QeahnVIDEDzo48p7uCJ-3fwfL3J9TZ-20nmmlusNRTWkvqrQrtERcDsbbu_c5My8f5OTJ_4K0aQ", "UTF-8"); 

									 conn.setDoOutput(true); 
									 OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
									 wr.write(data); 
									 wr.flush(); 

									 BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
									 String line; 
									 while ((line = rd.readLine()) != null) { 
									 System.out.println(line); 
									 } 
									 wr.close(); 
									 rd.close(); 
									 } catch (Exception e) { 
									 }
								
								//--------------------------------------------------------------------------------
								
								for (int i = segundos; i > 0; i--) {
									System.out.println(i + " segundos");
									Thread.sleep(1000); // 1 segundo
								}
							} catch (InterruptedException e) {
							}

						} else {
							this.numeroCaixaReal.setText("NENHUMA SOLICITACAO");
						}

					} else {
						this.numeroCaixaReal.setText("NENHUMA SOLICITACAO");
					}
					
					
					}else{
						this.numeroCaixaReal.setText("NENHUMA SOLICITACAO");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				
		

		}
	}
}