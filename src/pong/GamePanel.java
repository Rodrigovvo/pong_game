package pong;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	
	public static final int GAME_WIDTH = 1000;
	public static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BOLA_DIAMETRO = 20; 
	static final int RAQUETE_WIDTH = 25;
	static final int RAQUETE_HEIGHT = 100;
	//Thread
	Thread gameThread;
	// Elementos Gráficpos
	Image imagem;
	Graphics graphics;
	// Importe de valor aleatório
	Random random;
	// Elementos do Jogo
	Raquete raquete1;
	Raquete raquete2;
	Bola bola;
	Pontuacao score;
	
	public GamePanel() {
		novasRaquetes();
		novaBola();
		score = new Pontuacao(GAME_WIDTH, GAME_HEIGHT );
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread =  new Thread(this);
		gameThread.start();
		
	}
	
	/*
	 * Método para invoacação da bola;
	 * O uso do 'random' gera a bola aleatoriamente na vertical no cento da tela.
	 */
	public void novaBola() {
		random = new Random();
		bola = new Bola((GAME_WIDTH /2)-(BOLA_DIAMETRO/2), random.nextInt(GAME_HEIGHT-BOLA_DIAMETRO), BOLA_DIAMETRO, BOLA_DIAMETRO); 
		

	}
	
	public void novasRaquetes() {
		raquete1 = new Raquete(0, (GAME_HEIGHT/2) -(RAQUETE_HEIGHT/2), RAQUETE_WIDTH, RAQUETE_HEIGHT, 1);
		raquete2 = new Raquete(GAME_WIDTH-RAQUETE_WIDTH, (GAME_HEIGHT/2) -(RAQUETE_HEIGHT/2), RAQUETE_WIDTH, RAQUETE_HEIGHT, 2);		
	}
	
	public void paint(Graphics g ){
		imagem = createImage(getWidth(), getHeight());
		graphics = imagem.getGraphics();
		draw(graphics);
		g.drawImage(imagem,0,0,this);
	}
	
	public void draw(Graphics g) {
		raquete1.draw(g);
		raquete2.draw(g);
		bola.draw(g);
		score.draw(g);
		Toolkit.getDefaultToolkit().sync(); 
	}
	public void move() {
		raquete1.move();
		raquete2.move();
		bola.move();
		
	}
	public void checkCollision() {
			
		//Colisão da bola nas bordas da tela
		if(bola.y<=0) {
			bola.setYDirection(-bola.yVelocidade);
		}
		if(bola.y >=GAME_HEIGHT-BOLA_DIAMETRO) {
			bola.setYDirection(-bola.yVelocidade);
		}
		
		// colisão com as raquetes
		if(bola.intersects(raquete1)) {
			bola.xVelocidade = Math.abs(bola.xVelocidade);
			bola.xVelocidade++; // Acrescentando mais velocidade a cada colisão com raquete
			if(bola.yVelocidade>0) {
				bola.yVelocidade++;
			}else {
				bola.yVelocidade--;
			}
			bola.setXDirection(bola.xVelocidade);
			bola.setYDirection(bola.yVelocidade);
		}
		
		
		if(bola.intersects(raquete2)) {
			bola.xVelocidade = Math.abs(bola.xVelocidade);
			bola.xVelocidade++; // Acrescentando mais velocidade a cada colisão com raquete
			if(bola.yVelocidade>0) {
				bola.yVelocidade++;
			}else {
				bola.yVelocidade--;
			}
			bola.setXDirection(-bola.xVelocidade);
			bola.setYDirection(bola.yVelocidade);
		}
		
		
		
		// Este método previne colisões
		if(raquete1.y<=0)

			raquete1.y=0;

		if(raquete1.y >= (GAME_HEIGHT-RAQUETE_HEIGHT))

			raquete1.y = GAME_HEIGHT-RAQUETE_HEIGHT;

		if(raquete2.y<=0)

			raquete2.y=0;

		if(raquete2.y >= (GAME_HEIGHT-RAQUETE_HEIGHT))

			raquete2.y = GAME_HEIGHT-RAQUETE_HEIGHT;
		
		// Pontuação e Nova Bola no jogo
		
		if(bola.x<=0) {
			score.player2++;
			novasRaquetes();
			novaBola();

		}
		
		if(bola.x>=GAME_WIDTH-BOLA_DIAMETRO) {
			score.player1++;
			novasRaquetes();
			novaBola();
		}
		
		
	}
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks=60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		while(true) {

			long now = System.nanoTime();

			delta += (now -lastTime)/ns;

			lastTime = now;

			if(delta >=1) {

				move();

				checkCollision();

				repaint();

				delta--;


			}

		}

		
	}
	public class AL extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			raquete1.keyPressed(e);
			raquete2.keyPressed(e);
			super.keyPressed(e);
		}
		
		

		@Override
		public void keyReleased(KeyEvent e) {
			raquete1.keyReleased(e);
			raquete2.keyReleased(e);
		
			super.keyReleased(e);
		}


	}
}
