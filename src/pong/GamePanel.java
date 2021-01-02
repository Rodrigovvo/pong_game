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
	static final int BALL_DIAMETER = 20; 
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	public GamePanel() {
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT );
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread =  new Thread(this);
		gameThread.start();
		
	}
	
	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH /2)-(BALL_DIAMETER/2), (GAME_HEIGHT /2) -(BALL_DIAMETER / 2), BALL_DIAMETER, BALL_DIAMETER);

	}
	
	public void newPaddles() {
		paddle1 = new Paddle(0, (GAME_HEIGHT/2) -(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2) -(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);		
	}
	
	public void paint(Graphics g ){
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		Toolkit.getDefaultToolkit().sync(); 
	}
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
		
	}
	public void checkCollision() {
		
		//Colisão da bola nas bordas da tela
		if(ball.y<=0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >=GAME_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		// Este método previne colisões
		if(paddle1.y<=0)

			paddle1.y=0;

		if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))

			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;

		if(paddle2.y<=0)

			paddle2.y=0;

		if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))

			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		
				
		
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
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
			super.keyPressed(e);
		}
		
		

		@Override
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		
			super.keyReleased(e);
		}


	}
}