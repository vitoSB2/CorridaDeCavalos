package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Game;

public class Menu implements StateMethods{
	
	Game game;
	public Menu(Game game) {
		this.game = game;
	}
	
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Menu", 100, 100);
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			GameState.gameState = GameState.JOGO;
			game.reset();
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
