package visual;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import controlador.Corredor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class Pista extends JPanel{
	
	JLabel prompt, textoVitoria, bg;
	JSpinner quant;
	JButton confirm;
	public ImageIcon[][] sprites;
	
	public Pista() {
		this.setSize(1280, 820);
		this.setLayout(null);
		this.add(getPrompt());
		this.add(getQuant());
		this.add(getConfirm());
		this.add(getTextoVitoria());
		this.add(getBG());
		criarSprites("/sprites/horses_atlas.png");
	}
	
	public JLabel getBG() {
		if(bg == null) {
			BufferedImage imageBG = null;
			try {
				imageBG = ImageIO.read(getClass().getResourceAsStream("/sprites/bg3.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedImage temp = resizeImage(imageBG, 1300, 820);
			ImageIcon iconBG = new ImageIcon (temp);
			bg = new JLabel(iconBG);
			bg.setHorizontalAlignment(SwingConstants.CENTER);
			bg.setBounds(0, 0, 1300, 820);
		}
		return bg;
	}
	
	public JLabel getPrompt() {
		if(prompt == null) {
			prompt = new JLabel();
			prompt.setBackground(SystemColor.controlHighlight);
			prompt.setForeground(new Color(10, 10, 10));
			prompt.setHorizontalAlignment(SwingConstants.CENTER);
			prompt.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 40));
			prompt.setText("Selecione uma quantidade de corredores:");
			prompt.setBounds(100, 20, 505, 40);
		}
		return prompt;
	}
	
	public JLabel getTextoVitoria() {
		if(textoVitoria == null) {
			textoVitoria = new JLabel();
			textoVitoria.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 52));
			textoVitoria.setBounds(500, 100, 505, 40);
			textoVitoria.setForeground(new Color(255, 255, 255));
		}
		return textoVitoria;
	}
	
	public JSpinner getQuant() {
		if(quant == null) {
			quant = new JSpinner();
			quant.setForeground(new Color(0, 0, 0));
			quant.setBackground(new Color(0, 51, 153));
			quant.setModel(new SpinnerNumberModel(0, 0, 8, 1));
			quant.setBounds(100, 60, 100, 40);
			quant.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 30));
		}
		return quant;
	}
	
	public JButton getConfirm() {
		if(confirm == null) {
			confirm = new JButton();
			confirm.setVerticalAlignment(SwingConstants.TOP);
			confirm.setForeground(new Color(10, 10, 10));
			confirm.setBackground(SystemColor.control);
			confirm.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 30));
			confirm.setText("Confirmar");
			confirm.setBounds(100, 100, 130, 40);
		}
		return confirm;
	}
	
	public void criarSprites(String atlas) {
		BufferedImage imageAtlas = null;
		try {
			imageAtlas = ImageIO.read(getClass().getResourceAsStream(atlas));
		} catch (IOException e) {
			e.printStackTrace();
		}
		sprites = new ImageIcon[8][9];
		
		for(int i=0; i<8; i++) {
			for(int j=0; j<9; j++) {
				BufferedImage temp = resizeImage(imageAtlas.getSubimage(j*32, i*24, 32, 24), 80, 60);
				sprites[i][j] = new ImageIcon (temp);
			}
		}
	}
	
	public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image temp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();
        
        return resizedImage;
    }
	
	public void addCorredores(int quantCorredores, int tamanhoPista) {
		
		for(int i = 0; i<quantCorredores; i++) {
			JLabel corredor = new JLabel(sprites[i][0]);
			corredor.setHorizontalAlignment(SwingConstants.CENTER);
			corredor.setBounds(40, 250+i*65, 80, 60);
			this.add(corredor);
			
			Thread corredorThread = new Thread(new Corredor(tamanhoPista, "Corredor " + (i+1), corredor, textoVitoria));
			corredorThread.start();
			
			Thread animacaoThread = new Thread(new Animation(sprites, corredor, i));
			animacaoThread.start();
		}
	}

}
