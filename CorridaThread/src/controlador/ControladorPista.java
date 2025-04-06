package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import visual.Pista;

public class ControladorPista implements ActionListener{
	
	Pista p;
	int tamanhoPista = 1100;
	public static boolean corridaFinalizada = false;
	
	public ControladorPista(Pista p) {
		this.p = p;
		addEventos();
	}

	public void addEventos() {
		p.getConfirm().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == p.getConfirm()) {
			corridaFinalizada = false;
			p.getConfirm().setEnabled(false);
			p.addCorredores((Integer) p.getQuant().getValue(), tamanhoPista);
			p.add(p.getBG());
		}
	}
	
	public static boolean getCorridaFinalizada() {
			return corridaFinalizada;
	}
	
	public static void setCorridaFinalizada(boolean b) {
		corridaFinalizada = b;
	}

}
