package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

class Raquete extends Rectangle{
	
	int id;

	int yVelocidade;

	int velocidade = 10;

	

	Raquete(int x, int y, int RAQUETE_WIDTH, int RAQUETE_HEIGHT, int id){

		super(x,y,RAQUETE_WIDTH,RAQUETE_HEIGHT);

		this.id=id;

	}



	public void keyPressed(KeyEvent e) {

		if(id == 1) {
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(-velocidade);

			}

			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(velocidade);

			}
		}else {

			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(-velocidade);

			}

			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(velocidade);

			}

		}

	}

	public void keyReleased(KeyEvent e) {

		switch(id) {

		case 1:

			if(e.getKeyCode()==KeyEvent.VK_W) {

				setYDirection(0);

			}

			if(e.getKeyCode()==KeyEvent.VK_S) {

				setYDirection(0);

			}

			break;

		case 2:

			if(e.getKeyCode()==KeyEvent.VK_UP) {

				setYDirection(0);

			}

			if(e.getKeyCode()==KeyEvent.VK_DOWN) {

				setYDirection(0);

			}

			break;

		}

	}

	public void setYDirection(int yDirection) {

		yVelocidade = yDirection;

	}

	public void move() {

		y = y + yVelocidade;

	}

	public void draw(Graphics g) {

		if(id==1)

			g.setColor(Color.blue);

		else

			g.setColor(Color.red);

		g.fillRect(x, y, width, height);

	}

}
