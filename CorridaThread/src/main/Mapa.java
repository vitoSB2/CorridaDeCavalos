package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mapa {
	
	public BufferedImage bg = null;
	public BufferedImage[][] sprites;
	public BufferedImage[] mensagemVitoria;
	Cavalo[] cavalos;
	int quant = 8;
	
	public Mapa() {
		setImage("/sprites/bg3.png", bg);
		criarSprites();
		criarCavalos();
	}
	
	private void criarCavalos() {
		cavalos = new Cavalo[quant];
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
	}
	
	public void setImage(String caminho, BufferedImage imagem) {
		try {
			bg = ImageIO.read(getClass().getResourceAsStream(caminho));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void criarSprites() {
		BufferedImage imageAtlas = null;
		try {
			imageAtlas = ImageIO.read(getClass().getResourceAsStream("/sprites/horses_atlas.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		sprites = new BufferedImage[8][9];
		
		for(int i=0; i<8; i++)
			for(int j=0; j<9; j++) {
				sprites[i][j] = imageAtlas.getSubimage(j*32, i*24, 32, 24);
			}
		
		try {
			imageAtlas = ImageIO.read(getClass().getResourceAsStream("/sprites/mensagemVitoria.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mensagemVitoria = new BufferedImage[8];
		
		for(int j=0; j<8; j++) {
			mensagemVitoria[j] = imageAtlas.getSubimage(0, j*12, 186, 12);
		}
	}

	public void reset() {
		for(int i=0; i<quant; i++) {
			cavalos[i].reset();
		}
	}

}
