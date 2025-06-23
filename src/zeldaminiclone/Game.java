package zeldaminiclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	//Definir constantes para o tamanho da janela
	
	public static int WIDTH = 480, HEIGHT = 480;
	
	public Game() {
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //Criando uma nova dimensão
	}
	
	//responsável pela lógica do jogo 
	public void tick() {
		
	}
	
	//Onde será renderizado os gráficos
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3); //otimizações gráficas
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT); //Criar um retângulo na tela
		
		
		g.setColor(Color.red);
		g.fillRect(0, 0, 100, 100);
		
		
		bs.show();
		
	}
	
	//méthodo principal
	
	public static void main(String[] args) {
		
		Game game = new Game();
		JFrame frame = new JFrame(); //Janela do Java
		
		frame.add(game);
		frame.setTitle("Mini Zelda");
		
		frame.pack(); // Empacotar tudo e calcular o tamanho certo da nossa janela
		frame.setLocationRelativeTo(null); //Deixar a Janela centralizada
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quando fechar o Jframe, o Java também ser finalizado
		
		frame.setVisible(true); //Mostar a Janela
		
		// Manter o Looping para ficar renderizando
		
		new Thread(game).start();
	}

	@Override
	public void run() {
		
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	

}
