package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import states.Jogo;

public class Mapa {
	
	public BufferedImage bg, mensagem;
	public BufferedImage[][] sprites;
	public BufferedImage[] mensagemVitoria;
	Cavalo[] cavalos;
	int quant = 8, count = 0;
	
	public Mapa() {
		setImage();
		criarSprites();
		criarCavalos();
	}
	
	private void criarCavalos() {
		cavalos = new Cavalo[8];
		for(int i=0; i<quant; i++) {
			cavalos[i] = new Cavalo(sprites, i, mensagemVitoria);
		}
	}

	public void update() {
		for(int i=0; i<quant; i++) {
			cavalos[i].update();
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(bg, 0, 0, 1300, 820, null);
		for(int i=0; i<quant; i++) {
			cavalos[i].render(g);
		}
		
		drawMensagem(g);
	}
	
	private void drawMensagem(Graphics g) {
		if(Jogo.corridaFinalizada)
			if(count >= 30) g.drawImage(mensagem, 397, 750, 506, 31, null);
			if(count == 59) count = 0;
			else count++;
	}
	
	public void setImage() {
		try {
			bg = ImageIO.read(getClass().getResourceAsStream("/sprites/bg3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void criarSprites() {
		BufferedImage imageAtlas = null, imageAtlas2 = null;
		try {
			imageAtlas = ImageIO.read(getClass().getResourceAsStream("/sprites/horses_atlas.png"));
			imageAtlas2 = ImageIO.read(getClass().getResourceAsStream("/sprites/mensagemVitoria.png"));
			mensagem = ImageIO.read(getClass().getResourceAsStream("/sprites/jogo_mensagem.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		sprites = new BufferedImage[8][9];
		
		for(int i=0; i<8; i++)
			for(int j=0; j<9; j++) {
				sprites[i][j] = imageAtlas.getSubimage(j*32, i*24, 32, 24);
			}
		
		mensagemVitoria = new BufferedImage[8];
		
		for(int j=0; j<8; j++) {
			mensagemVitoria[j] = imageAtlas2.getSubimage(0, j*12, 186, 12);
		}
	}

	public void reset(int index) {
		quant = index+2;
		for(int i=0; i<quant; i++) {
			cavalos[i].reset();
		}
	}

}
