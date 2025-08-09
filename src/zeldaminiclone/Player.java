package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{
	
	//Velocidade do player
	public int spd = 4;
	
	//Controles
	public boolean right,up,down,left; 
	
	//Animação
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 15; // quanto maior o valor, menor vai ser a animação
	
	public Player(int x, int y) { //Posições do player
		super(x, y, 32, 32); // posições do player e tamanho dele 
	}
	
	public void tick() {
		
		//Para verificar se o personagem está se mexendo
		boolean moved = false; 
		
		if(right && Word.isFree(x+spd, y)) {
			x += spd;
			moved = true;
			
		}else if(left  && Word.isFree(x-spd, y)) {
			x -= spd; 
			moved = true;
		}
		
		if(up && Word.isFree(x, y-spd)) {
			y -= spd;
			moved = true;
			
		}else if(down && Word.isFree(x, y+spd)) {
			y += spd; 
			moved = true;
		}
		
		
		if(moved) { //verificando se o personagem se moveu
			
			// Fazendo a animação funcionar
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if(curAnimation == Spritesheet.player_front.length) {
					curAnimation = 0;
				}
			}
		}
	}
	
	public void render(Graphics g){
		//g.setColor(Color.blue);
		//g.fillRect(x, y, width, height);
		g.drawImage(Spritesheet.player_front[curAnimation], x, y, 32,32, null);
	}

}
