package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Word {
	
	//Criando uma lista para adicionar vários objetos no jogo
	public static List<Blocks> blocos = new ArrayList<Blocks>(); // bloco como estático para poder acessar no método "isfree"
	
	//Construtor
	public Word() {
		//Criando blocos na horizontal
		for(int xx = 0; xx < 15; xx++) { // Calculo baseado no tamanho da tela
			blocos.add(new Blocks(xx*32,0));
		}
		//Criando blocos na parte inferior
		for(int xx = 0; xx < 15; xx++) { // Calculo baseado no tamanho da tela
			blocos.add(new Blocks(xx*32,480-32));
		}
		//Criando blocos na vertical
		for(int yy = 0; yy < 15; yy++) { // Calculo baseado no tamanho da tela
			blocos.add(new Blocks(0,yy*32));
		}
		//Criando blocos na vertical
		for(int yy = 0; yy < 15; yy++) { // Calculo baseado no tamanho da tela
			blocos.add(new Blocks(480-32,yy*32));
		}
	}
	
	//Criando colisões
	public static boolean isFree(int x, int y) {
		for(int i = 0; i < blocos.size(); i++) {
			Blocks blocoAtual = blocos.get(i);
			
			//intersects metodo para verificar colisão
			if(blocoAtual.intersects(new Rectangle(x,y,32,32))) { //Simulando o player
				return false;
			}
		}
		
		return true;
	}
	
	public void render(Graphics g) {
		//Vamos renderizar nosso bloco independendo de quanto tiver
		for(int i = 0; i < blocos.size(); i++) {
			blocos.get(i).render(g);
			
		}
	}

}
