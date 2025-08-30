package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle{
	
	//Velocidade do player
	public int spd = 4;
	
	//Controles
	public boolean right,up,down,left; 
	
	//Animação
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 15; // quanto maior o valor, menor vai ser a animação
	
	// Sistema de Tiro
	public static List<Bullet> bullets = new ArrayList<Bullet>(); //Onde os tiros vão ser armazenados
	
	//saber onde o player andou
	public int dir = 1;
	
	
	//Variavel para saber que está atirando
	public boolean shoot = false;
	
	
	
	
	public Player(int x, int y) { //Posições do player
		super(x, y, 32, 32); // posições do player e tamanho dele 
	}
	
	public void tick() {
		
		//Para verificar se o personagem está se mexendo
		boolean moved = false; 
		
		if(right && Word.isFree(x+spd, y)) {
			x += spd;
			moved = true;
			dir = 1;
			
		}else if(left  && Word.isFree(x-spd, y)) {
			x -= spd; 
			moved = true;
			dir = -1;
		}
		
		if(up && Word.isFree(x, y-spd)) {
			y -= spd;
			moved = true;
			  dir = 0; // Virando para cima (supondo 0 como direção "cima")
		}else if(down && Word.isFree(x, y+spd)) {
			y += spd; 
			moved = true;
			dir = 2; // Virando para baixo (supondo 2 como direção "baixo")
		}
		
		//verificando se o personagem se moveu
		
		if(moved) { 
			
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
		
		
		if(shoot) {
			shoot = false;
			bullets.add(new Bullet(x,y,dir));
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}
	
	public void render(Graphics g){
		//g.setColor(Color.blue);
		//g.fillRect(x, y, width, height);
		g.drawImage(Spritesheet.player_front[curAnimation], x, y, 32,32, null);
		
		//renderizando a bullet
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}

}
