package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{
	
	//Controles
	public boolean right,up,down,left; 
	
	//Velocidade do player
	public int spd = 4;
	
	public Player(int x, int y) { //Posições do player
		super(x, y, 32, 32); // posições do player e tamanho dele 
	}
	
	public void tick() {
		
		if(right) {
			x += spd;
			
		}else if(left) {
			x -= spd; 
		}
		
		if(up) {
			y -= spd;
			
		}else if(down) {
			y += spd; 
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}

}
