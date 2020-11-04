package day1103.test;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameObject{
	public Bullet(int x, int y, int width, int height, int velX, int velY) {
		super(x, y, width, height, velX, velY);
	}
	
	@Override
	public void tick() {
		x += velX;
	}
	
	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.black);
		g2.fillOval(x, y, width, height);
	}
}
