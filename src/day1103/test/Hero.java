package day1103.test;

import java.awt.Color;
import java.awt.Graphics2D;

public class Hero extends GameObject{
	public Hero(int x, int y, int width, int height, int velX, int velY) {
		super(x, y, width, height, velX, velY);
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY;
	}
	
	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setColor(Color.RED);
		g2.fillRect(x, y, width, height);
	}
}
