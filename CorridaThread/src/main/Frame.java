package main;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	public Frame(GamePanel gp) {
		super();
		this.add(gp);
		this.setTitle("Corrida de Cavalo");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setResizable(false);
	    this.pack();
	    this.setVisible(true);
	}

}
