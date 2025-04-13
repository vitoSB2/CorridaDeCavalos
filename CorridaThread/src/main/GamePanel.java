package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyBoardInputs;

public class GamePanel extends JPanel{
	
	Game game;
	public GamePanel(Game game){
		this.game = game;
		setPanelSize();
		setFocusable(true);
		addKeyListener(new KeyBoardInputs(this));
	}
	
	private void setPanelSize() {
		Dimension size = new Dimension(1300, 820);
		setPreferredSize(size);
	}
	
	public Game getGame() {
		return game;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}

}
