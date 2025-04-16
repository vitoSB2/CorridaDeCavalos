package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import states.Jogo;
import states.Menu;

public class Cavalo {
	
	public BufferedImage[][] sprites;
	public BufferedImage[] mensagemVitoria;
	int numCavalo, state = 0, distanciaPercorrida, y = 40, updateCounter = 0, updateInterval = 8, stateCounter = 0, stateInterval = 5;
	Random random;
	
	public Cavalo(BufferedImage[][] sprites, int numCavalo, BufferedImage[] mensagemVitoria) {
		this.sprites = sprites;
		this.numCavalo = numCavalo;
		this.mensagemVitoria = mensagemVitoria;
		random = new Random();
	}
	
	public void getNewPosition() {
		int passo = random.nextInt(4);
        distanciaPercorrida += passo * 8;
        y += distanciaPercorrida;
        distanciaPercorrida = 0;
	}
	
	public void getState() {
		if(state == 8)
		state = 0;
		else
		state++;
	}
	
	public void updatePosition() {
		updateCounter++;
		if (updateCounter >= updateInterval) {
            getNewPosition();
            updateCounter = 0;
        }
	}
	
	public void updateState() {
		stateCounter++;
        
        if(stateCounter >= stateInterval) {
        	getState();
        	stateCounter = 0;
        }
	}
	
	public void verificarVitoria(Graphics g) {
		if(y >= 1100) {
			Jogo.corridaFinalizada = true;
			Menu.musicaCorrida.stop();
			Menu.musicaMenu.start();
			g.drawImage(mensagemVitoria[numCavalo], 371, 80, 558, 36, null);
		}
	}
	
	public void update() {
		
		if(!Jogo.corridaFinalizada) {
			updatePosition();
			updateState();
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(sprites[numCavalo][state], y, 250+numCavalo*65, 80, 60, null);
		verificarVitoria(g);
	}

	public void reset() {
		y = 0;
		state = 0;
	}

}
