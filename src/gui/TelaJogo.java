package gui;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import br.com.poli.CampoMinado.mapa.*;
import br.com.poli.CampoMinado.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class TelaJogo extends JFrame {

	private JLabel lblBandeiras;
	private int numBandeiras;
	private int seg, min, hora;
	private int contador = 0;
	private Timer tm;
	private JLabel contagemTempo;
	private JButton[][] button;
	private JPanel contentPane;
	private Dificuldade dificuldade;
	private Mapa mapa;

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { TelaJogo frame = new
	 * TelaJogo(dificuldade); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	JPanel panel = new JPanel();

	public TelaJogo(Dificuldade dificuldade) {
		getContentPane().setBackground(Color.DARK_GRAY);

		this.dificuldade = dificuldade;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		setBounds(100, 100, 550, 550);
		if (dificuldade == Dificuldade.DIFICIL) {
			setBounds(200, 200, 800, 1100);
		} else if (dificuldade == Dificuldade.MEDIO) {
			setBounds(150, 150, 675, 675);

		}
		getContentPane().setLayout(null);

		panel.setBackground(Color.GRAY);
		panel.setBounds(64, 75, 400, 400);

		getContentPane().add(panel);
		panel.setLayout(new GridLayout());

		if (dificuldade == Dificuldade.FACIL) {
			numBandeiras = 10;
			mapa = new MapaFacil();
		} else if (dificuldade == Dificuldade.MEDIO) {
			mapa = new MapaMedio();
			panel.setBounds(70, 80, 500, 500);
			numBandeiras = 40;

		} else {
			mapa = new MapaDificil();
			panel.setBounds(80, 80, 600, 600);
			numBandeiras = 99;
		}
		setVisible(true);
		panel.setLayout(new GridLayout(dificuldade.getDificuldade(), dificuldade.getDificuldade()));
		cronometro();
		contagemTempo.setBackground(Color.BLUE);
		contagemTempo.setForeground(Color.YELLOW);
		contagemTempo.setFont(new Font("Comics San", Font.BOLD, 17));
		contagemTempo.setBounds(228, 11, 236, 69);
		getContentPane().add(contagemTempo);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				MenuPrincipal menu1 = new MenuPrincipal();
				menu1.setVisible(true);
			}

		});
		btnSair.setFont(new Font("Comics San", Font.BOLD, 13));
		btnSair.setBounds(64, 24, 89, 23);
		getContentPane().add(btnSair);

		lblBandeiras = new JLabel("Bandeiras: " + Integer.toString(numBandeiras));
		lblBandeiras.setForeground(Color.YELLOW);
		lblBandeiras.setFont(new Font("Comics San", Font.BOLD, 20));
		lblBandeiras.setBounds(269, 0, 205, 25);
		getContentPane().add(lblBandeiras);

		criarBotoes();

	}

	public void criarBotoes() {
		button = new JButton[dificuldade.getDificuldade()][dificuldade.getDificuldade()];

		for (int i = 0; i < dificuldade.getDificuldade(); i++) {
			for (int j = 0; j < dificuldade.getDificuldade(); j++) {

				button[i][j] = new JButton();

				panel.add(button[i][j]);

				mouseListener(i, j);
				escolherPosicaoInterface(button[i][j], i, j);

			}
		}

	}

	public void mouseListener(int linha, int coluna) {

		button[linha][coluna].addMouseListener(new MouseAdapter() {
			@Override

			public void mousePressed(MouseEvent e) {

				if (SwingUtilities.isRightMouseButton(e) == true) {
                     
					if (getMapa().getCelula(linha, coluna).getVisivel() == false
						&& getMapa().getCelula(linha, coluna).getBandeira() == false) {
						if(numBandeiras > 0) {
						    getMapa().getCelula(linha, coluna).setBandeira(true);
							button[linha][coluna].setIcon(new ImageIcon(".\\Imagens\\lansoabraba.jpg"));
							button[linha][coluna].setEnabled(false);
							numBandeiras--;
                            lblBandeiras.setText("Bandeiras: " + Integer.toString(numBandeiras));
						
					}
							}
					
					 else if (getMapa().getCelula(linha, coluna).getVisivel() == false
	    						&& getMapa().getCelula(linha, coluna).getBandeira() == true) {
	    					getMapa().getCelula(linha, coluna).setBandeira(false);
	    					button[linha][coluna].setEnabled(true);
	    					button[linha][coluna].setIcon(new ImageIcon(""));
	    					numBandeiras++;
	    					lblBandeiras.setText("Bandeiras: " + Integer.toString(numBandeiras));
					 }
	    				
	    				else if(getMapa().getCelula(linha, coluna).getVisivel() == true
	    				    && getMapa().getCelula(linha, coluna).getBandeira() == true) {
	    					getMapa().getCelula(linha, coluna).setBandeira(false);
	    					button[linha][coluna].setEnabled(true);
	    					button[linha][coluna].setIcon(new ImageIcon(""));
	    					numBandeiras++;
	    					lblBandeiras.setText("Bandeiras: " + Integer.toString(numBandeiras));
	    					
	    					
	    						
	    					}
					}
				
				
				}
                       
				});
                    
           }
		
	
	

	

	public void escolherPosicaoInterface(JButton button, int linha, int coluna) {

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				getMapa().escolherPosicao(linha, coluna);

				if (getMapa().getCelula(linha, coluna).getBomba() == true) {

					button.setBackground(Color.RED);
					button.setIcon(new ImageIcon(".\\Imagens\\bomba.png"));
					tm.cancel();
					JOptionPane.showMessageDialog(null, "Você perdeu!");
					dispose();
					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);

				} else if (getMapa().getCelula(linha, coluna).getQtdBombasVizinhas() != 0) {
					if (dificuldade == Dificuldade.DIFICIL) {
						button.setMargin(new Insets(0, 0, 0, 0));
						button.setFont(new Font("Comics San", Font.PLAIN, 1));
					} else if (dificuldade == Dificuldade.MEDIO) {
						button.setMargin(new Insets(0, 0, 0, 0));
						button.setFont(new Font("Comics San", Font.PLAIN, 5));

					}
					button.setBackground(Color.WHITE);
					button.setFont(new Font("Comics San", Font.PLAIN, 13));
					button.setText(Integer.toString(getMapa().getCelula(linha, coluna).getQtdBombasVizinhas()));

				} else if (getMapa().getCelula(linha, coluna).getQtdBombasVizinhas() == 0) {

					revelarEspaçosInterface();

				}
				if (getMapa().verificarGanhouJogo() == true) {
					JOptionPane.showMessageDialog(null, "Você ganhou!");
					tm.cancel();
					dispose();
					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);
				}

			}
		});
	}

	public void revelarEspaçosInterface() {

		for (int i = 0; i < dificuldade.getDificuldade(); i++) {
			for (int j = 0; j < dificuldade.getDificuldade(); j++) {

				if (getMapa().getCelula(i, j).getVisivel() == true
						&& mapa.getCelula(i, j).getQtdBombasVizinhas() == 0) {

					this.button[i][j].setBackground(Color.WHITE);
				} else if (getMapa().getCelula(i, j).getVisivel() == true
						&& mapa.getCelula(i, j).getQtdBombasVizinhas() != 0) {
					if (dificuldade == Dificuldade.DIFICIL) {
						button[i][j].setMargin(new Insets(0, 0, 0, 0));
						button[i][j].setFont(new Font("Comics San", Font.PLAIN, 1));
					} else if (dificuldade == Dificuldade.MEDIO) {
						button[i][j].setMargin(new Insets(0, 0, 0, 0));
						button[i][j].setFont(new Font("Comics San", Font.PLAIN, 5));
					}
					this.button[i][j].setText(Integer.toString(getMapa().getCelula(i, j).getQtdBombasVizinhas()));
					this.button[i][j].setBackground(Color.WHITE);
					this.button[i][j].setFont(new Font("Comic Sans", Font.PLAIN, 13));
				}
			}
		}
	}

	public void cronometro() {

		contagemTempo = new JLabel();
		tm = new Timer();
		tm.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				contador++;
				seg = contador % 60;
				min = contador / 60;
				hora = min / 60;
				min %= 60;
				contagemTempo.setText(String.format("Tempo decorrido: %02d:%02d:%02d", hora, min, seg));
			}
		}, 1000, 1000);

	}

	public Mapa getMapa() {
		return mapa;
	}
}