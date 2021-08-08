package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import br.com.poli.CampoMinado.mapa.*;
import br.com.poli.CampoMinado.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MenuPrincipal extends JFrame {

    private TelaJogo tela;
    private JPanel contentPane;
    protected JTextField textField;

    
      
     
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal frame = new MenuPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

      
     
    public MenuPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 450);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
 

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCampoMinado = new JLabel("Campo Minado");
        lblCampoMinado.setForeground(Color.RED);
        lblCampoMinado.setFont(new Font("MingLiU-ExtB", Font.PLAIN, 18));
        lblCampoMinado.setBounds(150, 10, 130, 65);
        contentPane.add(lblCampoMinado);
        JLabel lblEscolhaADificuldade = new JLabel("Escolha a dificuldade:");
        lblEscolhaADificuldade.setFont(new Font("Yu Gothic", Font.PLAIN, 13));
        lblEscolhaADificuldade.setBounds(66, 87, 140, 14);
        contentPane.add(lblEscolhaADificuldade);

        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Yu Gothic", Font.PLAIN, 13));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"F\u00E1cil", "M\u00E9dio", "Dif\u00EDcil"}));
        comboBox.setBounds(216, 84, 113, 20);
        contentPane.add(comboBox);

        JButton btnJogar = new JButton("Jogar");
        btnJogar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(comboBox.getSelectedIndex() == 0) {

                    tela = new TelaJogo(Dificuldade.FACIL);
                    setVisible(true);
                    dispose();

                }
                else if(comboBox.getSelectedIndex() == 1) {
                    tela = new TelaJogo(Dificuldade.MEDIO);
                    setVisible(true);
                    dispose();

                    }
                else if(comboBox.getSelectedIndex() == 2){
                    tela = new TelaJogo(Dificuldade.DIFICIL);
                    setVisible(true);
                    dispose();
                }



            }
        });
        btnJogar.setFont(new Font("Yu Gothic", Font.PLAIN, 13));
        btnJogar.setBounds(169, 240, 96, 20);
        contentPane.add(btnJogar);
JLabel lblDevelopedBy = new JLabel("Developed by: ");
        lblDevelopedBy.setFont(new Font("Yu Gothic", Font.PLAIN, 11));
        lblDevelopedBy.setBounds(340, 347, 84, 14);
        contentPane.add(lblDevelopedBy);

        JLabel lblPedroRibeiro = new JLabel("Pedro Ribeiro");
        lblPedroRibeiro.setFont(new Font("Yu Gothic", Font.PLAIN, 11));
        lblPedroRibeiro.setBounds(340, 372, 84, 14);
        contentPane.add(lblPedroRibeiro);
        JLabel lblVinciusMartins = new JLabel("Vin\u00EDcius Martins");
        lblVinciusMartins.setFont(new Font("Yu Gothic", Font.PLAIN, 11));
        lblVinciusMartins.setBounds(340, 386, 84, 14);
        contentPane.add(lblVinciusMartins);

      /*  JButton btnRanking = new JButton("Ranking");
        btnRanking.setFont(new Font("Yu Gothic", Font.PLAIN, 13));
        btnRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnRanking.setBounds(169, 271, 96, 23);
        contentPane.add(btnRanking);*/

        JLabel lblColoqueSeuNome = new JLabel("Coloque seu nome:");
        lblColoqueSeuNome.setFont(new Font("Yu Gothic", Font.PLAIN, 13));
        lblColoqueSeuNome.setBounds(66, 141, 127, 14);
        contentPane.add(lblColoqueSeuNome);

        textField = new JTextField();
        textField.setBounds(216, 138, 113, 20);
        contentPane.add(textField);
        textField.setColumns(10);
        textField.getText();
    }
    public JTextField getTextField() {
        return textField;
    }
    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

}