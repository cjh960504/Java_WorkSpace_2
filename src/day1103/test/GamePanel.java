package day1103.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.sun.glass.events.KeyEvent;

public class GamePanel extends JPanel{
	Thread loopThread;
	public static final int WIDTH=1600;
	public static final int HEIGHT=900;
	Hero hero;
	Bullet bullet;
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>(); 
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		createHero();
		loopThread = new Thread() {
			@Override
			public void run() {
				while(true) {
					gameLoop();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		loopThread.start();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		
		render(g2);
	
	}
	public void moveObject(int key) {
		switch(key){
			case KeyEvent.VK_LEFT: hero.velX=-2;break;
			case KeyEvent.VK_RIGHT: hero.velX=2;break;
			case KeyEvent.VK_UP: hero.velY=-2;break;
			case KeyEvent.VK_DOWN: hero.velY=2;break;
			case KeyEvent.VK_SPACE: fire(); break;
		}
	}
	public void stopObject(int key) {
		switch(key){
		case KeyEvent.VK_LEFT: hero.velX=0;break;
		case KeyEvent.VK_RIGHT: hero.velX=0;break;
		case KeyEvent.VK_UP: hero.velY=0;break;
		case KeyEvent.VK_DOWN: hero.velY=0;break;
	}
	}
	
	public void fire() {
		bulletList.add(new Bullet(hero.x, hero.y, 50, 50, 2, 0));
	}
	public void createHero() {
		hero = new Hero(200, 200, 100, 50, 0, 0);
	}
	
	public void tick() {
		hero.tick();
		for(int i=0;i<bulletList.size();i++) {
			bulletList.get(i).tick();
		}
	}
	
	public void render(Graphics2D g2) {
		hero.render(g2);
		for(int i=0;i<bulletList.size();i++) {
			Bullet tmp = bulletList.get(i);
			tmp.render(g2);
			if(tmp.width>WIDTH) {
				bulletList.remove(tmp);
			}
		}
	}
	public void gameLoop() {
		System.out.println("on");
		tick();
		this.repaint();
	}
}
