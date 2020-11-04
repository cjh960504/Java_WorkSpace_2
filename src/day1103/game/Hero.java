/*
	주인공을 정의한다!!
*/
package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Hero extends GameObject {
	GamePanel gamePanel;

	public Hero(GamePanel gamePanel, Image img, int x, int y, int width, int height, int velX, int velY) {
		super(img, x, y, width, height, velX, velY);
		this.gamePanel = gamePanel;
	}

	@Override
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;

		rect.x = x;
		rect.y = y;
		if (gamePanel.hpList.size() > 0) { //에너지가 한개 이상일 경우만..
			collsionCheck();
		}else {//에너지 모두 소진
			gamePanel.over=true;
			gamePanel.flag=false;
		}
	}

	public void collsionCheck() {
		for (int i = 0; i < gamePanel.enemyList.size(); i++) {
			Enemy enemy = gamePanel.enemyList.get(i);
			if (this.rect.intersects(enemy.rect)) {// 충돌
				gamePanel.hpList.remove(gamePanel.hpList.size() - 1);

				gamePanel.enemyList.remove(enemy);
//
//				if (gamePanel.hpList.size() == 0) {
//					gamePanel.flag = false;
//					System.exit(0);
//				}

				break;
			}
		}
	}

	@Override
	public void render(Graphics2D g2) {
//		g2.setColor(Color.RED);
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);

		// 우리가 이미 보유하고 있는 사각형을 시각화 시켜보자!

		g2.drawImage(img, x, y, null);
	}
}
