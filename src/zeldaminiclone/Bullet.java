package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Rectangle {

	public int dir = 1; // direção
	public int speed = 8; //60 fps 
	
	// para contagem do tempo
	public int frames = 0;
	
	public Bullet(int x, int y, int i) {
		super(x+16,y+16,10,10); //10 por 10 de tamanho do tiro
		
		this.dir = i;
	}
	
	//update na bullet
	public void tick() { 
		x += speed*dir;
		frames++;
		if(frames == 60) {
			Player.bullets.remove(this);
			return;
		}
	}
	
	// Renderizar
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, width, height);
	}
}