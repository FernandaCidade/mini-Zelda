package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inimigo extends Rectangle{ //Inimigo vai ser controlado por inteligencia artificial
	
	//Velocidade do player
	public int spd = 2;
	
	//Controles
	public int right = 1,up = 0,down = 0,left = 0; 
	
	//Animação
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 15; // quanto maior o valor, menor vai ser a animação
	
	// Sistema de Tiro
	public static List<Bullet> bullets = new ArrayList<Bullet>(); //Onde os tiros vão ser armazenados
	
	//saber onde o player andou
	public int dir = 1;
	
	
	//Variavel para saber que está atirando
	public boolean shoot = false;
	
	
	
	
	public Inimigo(int x, int y) { //Posições do player
		super(x, y, 32, 32); // posições do player e tamanho dele 
	}
	
	
	
	
	//criando inteligencia artificial
	public void perseguirPlayer() {
		Player p = Game.player;
		
		if(x < p.x && Word.isFree(x+spd, y)) { //Word.isFree para o inimigo colidir com o bloco
		
			
			if(new Random().nextInt(100)<50) //caso tenha uma probabilidade de 50
				x+=spd; // ele se mexe
			
			
		}else if(x > p.x && Word.isFree(x-spd, y)) {
			if(new Random().nextInt(100)<50) 
				x-=spd;
		}
		
		
		if(y < p.y && Word.isFree(x, y+spd)) {
			
			if(new Random().nextInt(100)<50) 
				y+=spd;
		}else if(y > p.y && Word.isFree(x, y-spd)) {
			
			if(new Random().nextInt(100)<50) 
				y-=spd;
		}
	}
	
	
	
	public void tick() {
		
		//Para verificar se o personagem está se mexendo
		boolean moved = true; 
		
		
		//metodo
		
		perseguirPlayer();
		
				
		
		
		//verificando se o personagem se moveu
		
		if(moved) { 
			
			// Fazendo a animação funcionar
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if(curAnimation == Spritesheet.inimigo_front.length) {
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
		g.drawImage(Spritesheet.inimigo_front[curAnimation], x, y, 32,32, null);
		
		//renderizando a bullet
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}

}
