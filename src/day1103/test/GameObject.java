package day1103.test;

import java.awt.Graphics2D;

public abstract class GameObject {
	int x, y, width, height, velX, velY;

	public GameObject(int x, int y, int width, int height, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g2);
	
	
}
