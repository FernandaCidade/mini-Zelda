package zeldaminiclone;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Word {
	
	//Criando uma lista para adicionar vários objetos no jogo
	public List<Blocks> blocos = new ArrayList<Blocks>();
	
	//Construtor
	public Word() {
		//Criando blocos na horizontal
		for(int xx = 0; xx < 15; xx++) { // Calculo baseado no tamanho da tela
			blocos.add(new Blocks(xx*32,0));
		}
	}
	
	public void render(Graphics g) {
		//Vamos renderizar nosso bloco independendo de quanto tiver
		for(int i = 0; i < blocos.size(); i++) {
			blocos.get(i).render(g);
			
		}
	}

}
