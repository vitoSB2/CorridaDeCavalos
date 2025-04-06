package controlador;

import java.util.Random;

import javax.swing.JLabel;

import visual.Pista;

public class Corredor implements Runnable{

	int nome, distanciaTotal, distanciaPercorrida = 0;
	boolean corridaFinalizada = ControladorPista.corridaFinalizada;
	JLabel corredor, tv;
	Pista p;
	
	public Corredor(int distanciaTotal, int nome, JLabel corredor, JLabel tv, Pista p) {
		this.distanciaTotal = distanciaTotal;
		this.nome = nome;
		this.corredor = corredor;
		this.tv = tv;
		this.p = p;
	}
	
	public void run() {

		Random random = new Random();
        
        while (distanciaPercorrida <= distanciaTotal && !ControladorPista.getCorridaFinalizada()) {
            int passo = random.nextInt(4);
            distanciaPercorrida += passo * 8;
            corredor.setLocation(corredor.getX() + passo*8, corredor.getY());
            
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (distanciaPercorrida >= distanciaTotal && !ControladorPista.getCorridaFinalizada()) {
                ControladorPista.setCorridaFinalizada(true);
                tv.setText("Cavalo " + nome + " venceu a corrida!");
            }
        }
        
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        p.remove(corredor);
        p.repaint();
        
        tv.setText("");
        p.getConfirm().setEnabled(true);
	}

}
