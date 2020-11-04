package day1103.test;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	GamePanel gp;
	
	public GameWindow() {
		gp=new GamePanel();
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				gp.moveObject(e.getKeyCode());
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				gp.stopObject(e.getKeyCode());
			}
		});
		setLayout(new FlowLayout());
		add(gp);
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	public static void main(String[] args) {
		new GameWindow();
	}
}
