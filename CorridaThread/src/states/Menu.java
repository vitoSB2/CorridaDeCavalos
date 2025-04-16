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
	BufferedImage bg, logo;
	BufferedImage[] mensagens;
	BufferedImage[][] numeros;
	int x = 0, x2 = 1300, index = 0, count = 0;
	public Menu(Game game) {
		this.game = game;
		setImages();
	}
	
	public void update() {
		movimentoBg();
	}
	
	public void draw(Graphics g) {
		
		g.drawImage(bg, x, 0, 6180, 820, null);
		if(x <= -4880) g.drawImage(bg, x2, 0, 6180, 820, null);
		g.drawImage(logo, 258, 62, 783, 270, null);
		drawNumeros(g);
		drawMensagens(g);
		
	}
	
	private void drawMensagens(Graphics g) {
		g.drawImage(mensagens[0], 191, 446, 918, 42, null);
		
		if(count < 30) g.drawImage(mensagens[1], 374, 750, 680, 31, null);
		if(count == 59) count = 0;
		else count++;
	}

	private void drawNumeros(Graphics g) {
		for(int i=0; i< 7; i++) {
			if(index == i) g.drawImage(numeros[1][i], 185+(i*140), 500, 90, 115, null);
			else g.drawImage(numeros[0][i], 185+(i*140), 500, 90, 115, null);
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
			numero = ImageIO.read(getClass().getResourceAsStream("/menu/menu_numeros.png"));
			mensagem = ImageIO.read(getClass().getResourceAsStream("/menu/menu_mensagens.png"));
			logo = ImageIO.read(getClass().getResourceAsStream("/menu/menu_logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		numeros = new BufferedImage[2][7];
		for(int i = 0; i<2; i++) {
			for(int j=0; j<7; j++) {
				numeros[i][j] = numero.getSubimage(j*75, i*96, 75, 96);
			}
		}
		
		mensagens = new BufferedImage[2];
		for(int i=0; i<2; i++) mensagens[i] = mensagem.getSubimage(0, i*14, 309, 14);
	}
	
	public void movimentoBg() {
		if(x < (-6180)) x = 0;
		else x-= 5;
		

		if(x <= -4880) {
			if(x2 < 0) x2 = 1300;
			else x2-= 5;
		}
	}

}
