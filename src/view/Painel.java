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

public class Painel extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, painelTopo, painelSenha, painelRodape;

	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static Dimension dim = toolkit.getScreenSize();
	JLabel horaData, numeroCaixaReal;

	private Color backgroundColor = Color.WHITE;
	private String marqueString;
	private int marqueTime = 100;

	static Marque marque;

	private String id;

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

				marqueString = preferencia.get(0).getTexto();
			} else {
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
		horaData.setBounds((painelTopo.getWidth() - 520) - 10, 15, 490, 100);
		painelTopo.add(horaData);

		painelSenha = new JPanel();
		painelSenha.setBackground(new Color(245, 249, 254));

		painelSenha.setBorder(BorderFactory.createLineBorder(new Color(192,
				191, 191)));

		painelSenha.setBounds(80, 250, ((int) dim.getWidth() - (80 + 80)), 300);
		painelSenha.setLayout(null);

		numeroCaixaReal = new JLabel();
		numeroCaixaReal.setForeground(Color.BLACK);
		numeroCaixaReal.setHorizontalAlignment(SwingConstants.CENTER);
		numeroCaixaReal.setFont(new Font("Lucida Grande", Font.BOLD, 160));
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

	}

	// ----------------------------------------------------------------------
	@Override
	public void run() {
		while (1 == 1) {
			try {
				Thread.sleep(2000);
				fachada = Fachada.getInstance();
				Chamada chamada = new Chamada();
				Caixa caixa = new Caixa();
				try {
					chamada = fachada.retornaSenha();
					caixa = fachada.retornaObjetoCaixa(chamada.getCaixaId());
					System.out.println("afdaf" + caixa.getCaixa());

					 //if (!id.equals(caixa.getCaixa())){
					// return caixa.getCaixa();
						// Som.play();
					this.numeroCaixaReal.setText(caixa.getCaixa());
					 //}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}