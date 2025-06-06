package main;

import java.awt.Graphics;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import states.GameState;
import states.Jogo;
import states.Menu;

public class Game implements Runnable{
	
	Frame frame;
	GamePanel gamePanel;
	Thread gameThread;
	Jogo jogo;
	Menu menu;
	
	public Game() {
		initClasses();
		gamePanel = new GamePanel(this);
		frame = new Frame(gamePanel);
		frame.setLocationRelativeTo(null);
		gamePanel.requestFocusInWindow();
		iniciaLoop();
	}
	
	private void initClasses() {
		menu = new Menu(this);
		jogo = new Jogo(this);
	}
	
	public void iniciaLoop(){
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void reset(int index) {
		jogo.reset(index);
	}
	
	public void update() {
		switch (GameState.gameState) {
		case MENU:
			menu.update();
			break;
		case JOGO:
			jogo.update();
			break;
		default:
			break;

		}
	}
	
	public void render(Graphics g) {
		switch (GameState.gameState) {
		case MENU:
			menu.draw(g);
			break;
		case JOGO:
			jogo.draw(g);
			break;
		default:
			break;

		}
	}

	public void run() {
		
		double intervalo = 1000000000.0 / 60;
		long lastTime = System.nanoTime();
		long agora = System.nanoTime();
		
		while(gameThread != null){
			agora = System.nanoTime();
			if(agora - lastTime >= intervalo){
				update();
				gamePanel.repaint();
				lastTime = agora;
			}
		}
		
	}

	public Jogo getJogo() {
		return jogo;
	}

	public Menu getMenu() {
		return menu;
	}
	
	public static Clip play(String nomeDoAudio) {
		try {
			URL audioUrl = Game.class.getResource(nomeDoAudio + ".wav");
			if (audioUrl == null) {
				System.err.println("O arquivo de áudio não foi encontrado: " + nomeDoAudio);
				return null;
			}
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioUrl);
			Clip audioClip = AudioSystem.getClip();
			audioClip.open(audioStream);
			audioClip.start();
			
			return audioClip;
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
