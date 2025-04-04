package controlador;

import java.util.Random;

import javax.swing.JLabel;

public class Corredor implements Runnable{

	int distanciaTotal, distanciaPercorrida = 0;
	boolean corridaFinalizada = ControladorPista.corridaFinalizada;
	String nome;
	JLabel corredor, tv;
	
	public Corredor(int distanciaTotal, String nome, JLabel corredor, JLabel tv) {
		this.distanciaTotal = distanciaTotal;
		this.nome = nome;
		this.corredor = corredor;
		this.tv = tv;
	}
	
	public void run() {

		Random random = new Random();
        
        while (distanciaPercorrida < distanciaTotal && !ControladorPista.getCorridaFinalizada()) {
            int passo = random.nextInt(4) + 3;
            distanciaPercorrida += passo * 8;
            corredor.setLocation(corredor.getX() + passo*8, corredor.getY());
            
            try {
                Thread.sleep(200); // Simula o tempo de corrida
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (distanciaPercorrida >= distanciaTotal && !ControladorPista.getCorridaFinalizada()) {
                ControladorPista.setCorridaFinalizada(true);
                tv.setText(nome + " venceu a corrida!");
            }
        }
		
	}

}
