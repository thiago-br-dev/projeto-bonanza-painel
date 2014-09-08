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
	private ArrayList<Preferencia> preferencia;
	private JLabel label_1;
	private JLabel label_2;

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
				preferencia.get(0).getTexto();
			} else {
				validaFrase = "Bonanza";
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
		painelTopo.setVisible(false);
		painelTopo.setLayout(null);

		contentPane.add(painelTopo);

		JLabel calendario = new JLabel("");
		calendario.setIcon(new ImageIcon(Painel.class
				.getResource("/view/img/calendario.png")));
		calendario.setBounds(painelTopo.getWidth() - 147, 0, 147, 130);
		painelTopo.add(calendario);

		JLabel fraseInicio = new JLabel("BONANZA SUPERMERCADOS");
		fraseInicio.setForeground(Color.BLACK);
		fraseInicio.setHorizontalAlignment(SwingConstants.LEFT);
		fraseInicio.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		fraseInicio.setBounds(30, 15, 600, 100);
		painelTopo.add(fraseInicio);

		painelSenha = new JPanel();
		painelSenha.setBackground(new Color(245, 249, 254));
		painelSenha.setBounds(0, (int) (dim.getHeight() / 2) - 175,
				((int) dim.getWidth()), 350);
		painelSenha.setLayout(null);

		/*
		 * calendario = new JLabel(""); calendario.setIcon(new
		 * ImageIcon(Painel.class.getResource("/view/img/calendario.png")));
		 * calendario.setBounds(painelTopo.getWidth() - 147, 0, 147, 130);
		 * painelTopo.add(calendario);
		 */

		numeroCaixaReal = new JLabel("CAIXA LIVRE - 03");
		numeroCaixaReal.setForeground(Color.WHITE);
		numeroCaixaReal.setHorizontalAlignment(SwingConstants.CENTER);
		numeroCaixaReal.setFont(new Font("DS-Digital", numeroCaixaReal.getFont().getStyle() & ~Font.BOLD & ~Font.ITALIC, 220));
		numeroCaixaReal.setBounds(0, 75, painelSenha.getWidth(), 200);
		painelSenha.add(numeroCaixaReal);

		contentPane.add(painelSenha);

		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Painel.class
				.getResource("/view/img/fundo_senha_caixa.jpg")));
		label_1.setBounds(0, 0, 1920, 566);
		painelSenha.add(label_1);

		marque = new Marque("Bonanza Supermercados - Tá tudo Aqui !", marqueTime);
		marque.setBackground(Color.BLACK);
		marque.setBounds(0, (int) dim.getHeight() - 100, ((int) dim.getWidth()), 100);
		marque.start();
		contentPane.add(marque);
		
		fundo_frase = new JLabel();
		fundo_frase.setIcon(new ImageIcon(Painel.class.getResource("/view/img/fundo_frase.png")));
		fundo_frase.setBounds(0, (int) dim.getHeight() - 206, ((int) dim.getWidth()), 206);
		contentPane.add(fundo_frase);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Painel.class.getResource("/view/img/bonanza_logo_media.png")));
		label_2.setBounds((int) (dim.getWidth() / 2) - 297, (int) (dim.getHeight() / 2) - 300, 595, 73);
		contentPane.add(label_2);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		horaData = new JLabel(format.format(new Date()));
		horaData.setForeground(Color.WHITE);
		horaData.setHorizontalAlignment(SwingConstants.CENTER);
		horaData.setFont(new Font("DS-Digital", horaData.getFont().getStyle() & ~Font.BOLD & ~Font.ITALIC, 100));
		horaData.setBounds(0, (int) (dim.getHeight() / 2) - 450, (int) dim.getWidth(), 100);
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

			try {

				preferencia = new ArrayList<Preferencia>();

				try {
					preferencia = (ArrayList<Preferencia>) fachada
							.listarPreferencia();

					System.out.println(validaFrase);
					
					if (preferencia.size() != 0){
					
					if (!preferencia.get(0).getTexto().equals(validaFrase)) {

						validaFrase = preferencia.get(0).getTexto();

						marque = new Marque(validaFrase, 10);
						marque.setBounds(0, (int) dim.getHeight() - 100, ((int) dim.getWidth()), 100);
						contentPane.add(marque);

					}
					}
					else{
						validaFrase = "Bonanza Supermercados - Tá tudo Aqui !";
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

					if (!id.equals(caixa.getCaixa())) {

						try {

							this.numeroCaixaReal.setText("CAIXA LIVRE - "
									+ caixa.getCaixa());

							Som.play();
							id = caixa.getCaixa();
							idChamada = chamada.getId();
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

								this.numeroCaixaReal.setText("CAIXA LIVRE - "
										+ caixa.getCaixa());

								Som.play();
								id = caixa.getCaixa();
								idChamada = chamada.getId();

								for (int i = segundos; i > 0; i--) {
									System.out.println(i + " segundos");
									Thread.sleep(1000); // 1 segundo
								}
							} catch (InterruptedException e) {
							}

						} else {
							this.numeroCaixaReal.setText("AGUARDE");
						}

					} else {
						this.numeroCaixaReal.setText("AGUARDE");
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