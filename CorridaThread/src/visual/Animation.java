package visual;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controlador.ControladorPista;

public class Animation implements Runnable{
	
	ImageIcon[][] sprites;
	JLabel corredor;
	int estado = 0, nCorredor;

	public Animation(ImageIcon[][] sprites, JLabel corredor, int nCorredor) {
		this.sprites = sprites;
		this.corredor = corredor;
		this.nCorredor = nCorredor;
	}

	public void run() {
		
		while (!ControladorPista.getCorridaFinalizada()) {
			
            corredor.setIcon(sprites[nCorredor][estado]);
            
            if(estado == 8) estado = 0;
            else estado ++;
            
            try {
                Thread.sleep(50); // Simula o tempo de corrida
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		
	}

}
