package controlador;

import visual.Frame;
import visual.Pista;

public class ControladorFrame {
	
	Frame f;
	Pista p;
	ControladorPista cp;
	
	public ControladorFrame() {
		f = new Frame();
		p = new Pista();
		f.setContentPane(p);
		cp = new ControladorPista(p);
	}

	public static void main(String[] args) {
		new ControladorFrame();
	}

}
