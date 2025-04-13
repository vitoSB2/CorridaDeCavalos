package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import states.GameState;

public class KeyBoardInputs implements KeyListener{
	
	public GamePanel gp;
	public KeyBoardInputs(GamePanel gp) {
		this.gp = gp;
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		
		switch (GameState.gameState) {
		case MENU:
			gp.getGame().getMenu().keyPressed(e);
			break;
		case JOGO:
			gp.getGame().getJogo().keyPressed(e);
			break;
		default:
			break;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		
		switch (GameState.gameState) {
		case MENU:
			gp.getGame().getMenu().keyReleased(e);
			break;
		case JOGO:
			gp.getGame().getJogo().keyReleased(e);
			break;
		default:
			break;

		}
	}

}
