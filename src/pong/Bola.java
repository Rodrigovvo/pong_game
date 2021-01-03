package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bola extends Rectangle{
	
	Random random;
	int xVelocidade;
	int yVelocidade;
	int velocidadeInicial = 2; 
	
	public Bola(int x, int y, int width, int height) {
		super(x,y,width, height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0)
			randomXDirection--;
		setXDirection(randomXDirection*velocidadeInicial );
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection);
		
	}
	
	public void setXDirection(int randomXDirection) {
		xVelocidade = randomXDirection;
	}
	
	public void setYDirection(int randomYDirection) {
		yVelocidade = randomYDirection;
	}
	
	public void move() {
		x += xVelocidade;
		y += yVelocidade;
	}
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(x,y, height, width);
	}
}
