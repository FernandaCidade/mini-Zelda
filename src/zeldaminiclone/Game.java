package zeldaminiclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
	
	//Definir constantes para o tamanho da janela
	public static int WIDTH = 480, HEIGHT = 480;
	
	//Instanciando o player
	public Player player;
	
	//Instânciando o mundo
	public Word word;
	
	public Game() {
		
		this.addKeyListener(this);
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //Criando uma nova dimensão
		
		//iniciando a sprite
		
		new Spritesheet();
		
		player = new Player(32,32);
		
		word = new Word();
	}
	
	//responsável pela lógica do jogo 
	public void tick() {
		
		player.tick();
		
	}
	
	//Onde será renderizado os gráficos
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3); //otimizações gráficas
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
	
		g.setColor(new Color(0,135,13));
		g.fillRect(0,0, WIDTH, HEIGHT);
		
		player.render(g);
		word.render(g);
		
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
	
	@Override
	public void keyPressed(KeyEvent e) { //Detectando o botão pressionado
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void keyReleased(KeyEvent e) { // Quando parar de pressionar o botão, o personagem tem que parar
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		
	}
	
	

}
