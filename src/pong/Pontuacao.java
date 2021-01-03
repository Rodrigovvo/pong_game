package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pontuacao extends Rectangle{
	
	static int GAME_WIDTH;
	static int GAME_HEIGTH;
	int player1;
	int player2;
	
	public Pontuacao(int GAME_WIDTH, int GAME_HEIGTH) {
		
		Pontuacao.GAME_WIDTH = GAME_WIDTH ;
		Pontuacao.GAME_HEIGTH = GAME_HEIGTH;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,60));

		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGTH);
		
		g.drawString(String.valueOf(player1), (GAME_WIDTH/2) -80, 50);
		g.drawString(String.valueOf(player2), (GAME_WIDTH/2) +50, 50);
	}
}
