package day1103.game;

import java.awt.Image;
import java.util.ArrayList;

public class Enemy extends GameObject {
	GamePanel gamePanel;

	public Enemy(GamePanel gamePanel, Image img, int x, int y, int width, int height, int velX, int velY) {
		super(img, x, y, width, height, velX, velY);
		this.gamePanel=gamePanel;
	}

	public void tick() {
		this.x += this.velX;
		
		//변경된 좌표는 즉시 사각형에 반영되어야, 총알과 적군이 충돌검사가 될 수 있다.
		this.rect.x = x;
		rect.y = y;
	}

	public void render(java.awt.Graphics2D g2) {
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
		g2.drawImage(img, x, y, null);// 게임 유저들을 위한 이미지
	};
}
