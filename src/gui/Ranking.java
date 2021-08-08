package gui;


import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JTextField;

public class Ranking {
	

	    public Ranking() {
	        try {
	            FileOutputStream ranking = new FileOutputStream("ranking.txt");
	            PrintWriter pr = new PrintWriter(ranking);
	           // pr.println(getTextField().getText());

	            pr.close();
	            ranking.close();
	        }

	        catch(Exception e){
	            System.out.println("Erro ao escrever no arquivo");
	        }
	    }

	}


