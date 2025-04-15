package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;

public class Menu implements StateMethods{
	
	Game game;
	BufferedImage bg;
	BufferedImage[] mensagens;
	BufferedImage[][] numeros;
	int x = 0, index = 0;
	public Menu(Game game) {
		this.game = game;
		setImages();
	}
	
	public void update() {
		movimentoBg();
	}
	
	public void draw(Graphics g) {
		
		g.drawImage(bg, x, 0, 6180, 820, null);
		drawNumeros(g);
		
	}
	
	private void drawNumeros(Graphics g) {
		for(int i=0; i< 7; i++) {
			if(index == i) g.drawImage(numeros[1][i], 101+(i*160), 338, 138, 144, null);
			else g.drawImage(numeros[0][i], 101+(i*160), 338, 138, 144, null);
		}	
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(index == 0) index = 6;
			else index--;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(index == 6) index =0;
			else index++;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			GameState.gameState = GameState.JOGO;
			game.reset(index);
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void setImages() {
		BufferedImage numero = null, mensagem = null;
		try {
			bg = ImageIO.read(getClass().getResourceAsStream("/menu/menu_bg2.png"));
			numero = ImageIO.read(getClass().getResourceAsStream("/menu/menu_numero.png"));
			mensagem = ImageIO.read(getClass().getResourceAsStream("/menu/menu_mensagens.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		numeros = new BufferedImage[2][7];
		for(int i = 0; i<2; i++) {
			for(int j=0; j<7; j++) {
				numeros[i][j] = numero.getSubimage(j*46, i*48, 46, 48);
			}
		}
		
		mensagens = new BufferedImage[2];
		for(int i=0; i<2; i++) mensagens[i] = mensagem.getSubimage(0, i+13, 308, 13);
	}
	
	public void movimentoBg() {
		if(x <= -6180) x = 0;
		else x-= 2;
	}

}
