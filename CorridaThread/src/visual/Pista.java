package visual;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import controlador.Corredor;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class Pista extends JPanel{
	
	JLabel prompt, textoVitoria;
	JSpinner quant;
	JButton confirm;
	
	public Pista() {
		this.setSize(1280, 720);
		this.setLayout(null);
		this.add(getPrompt());
		this.add(getQuant());
		this.add(getConfirm());
		this.add(getTextoVitoria());
	}
	
	public JLabel getPrompt() {
		if(prompt == null) {
			prompt = new JLabel();
			prompt.setHorizontalAlignment(SwingConstants.CENTER);
			prompt.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 40));
			prompt.setText("Selecione uma quantidade de corredores:");
			prompt.setBounds(100, 40, 505, 40);
		}
		return prompt;
	}
	
	public JLabel getTextoVitoria() {
		if(textoVitoria == null) {
			textoVitoria = new JLabel();
			textoVitoria.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 40));
			textoVitoria.setBounds(500, 100, 505, 40);
			textoVitoria.setForeground(Color.red);
		}
		return textoVitoria;
	}
	
	public JSpinner getQuant() {
		if(quant == null) {
			quant = new JSpinner();
			quant.setModel(new SpinnerNumberModel(0, 0, 10, 1));
			quant.setBounds(100, 80, 100, 40);
			quant.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 30));
		}
		return quant;
	}
	
	public JButton getConfirm() {
		if(confirm == null) {
			confirm = new JButton();
			confirm.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 30));
			confirm.setText("Confirmar");
			confirm.setBounds(100, 120, 130, 40);
		}
		return confirm;
	}
	
	public void addCorredores(int quantCorredores, int tamanhoPista) {
		
		for(int i = 0; i<quantCorredores; i++) {
			JLabel corredor = new JLabel(""+(i+1));
			corredor.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 40));
			corredor.setHorizontalAlignment(SwingConstants.CENTER);
			corredor.setBounds(40, 200+i*50, 40, 40);
			this.add(corredor);
			
			Thread corredorThread = new Thread(new Corredor(tamanhoPista, "Corredor" + (i+1), corredor, textoVitoria));
			corredorThread.start();
		}
	}

}
