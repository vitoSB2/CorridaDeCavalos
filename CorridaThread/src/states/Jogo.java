package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.Game;
import main.Mapa;

public class Jogo implements StateMethods{

	Game game;
	public static boolean corridaFinalizada = false;
	Mapa mapa;
	
	public Jogo(Game game) {
		this.game = game;
		mapa = new Mapa();
	}
	
	public void update() {
		mapa.update();
	}
	
	public void draw(Graphics g) {
			mapa.render(g);
	}
	
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(corridaFinalizada)
				GameState.gameState = GameState.MENU;
		}
		
	}

	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void reset(int index) {
		mapa.reset(index);
		corridaFinalizada = false;
	}

}
