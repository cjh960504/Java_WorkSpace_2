package day1103.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import commom.image.ImageUtil;

//사실 모든 게임의 그래픽 처리는 패널이 담당하게 됨!!
public class GamePanel extends JPanel {
	Thread loopThread; // 게임 루프를 무한루프로 실행할 쓰레드!
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	Hero hero;
//	Bullet bullet;
	// 다수의 총알을 담기 위한 컬렉션 프레임웍 중 List를 이용해보자!!
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	ArrayList<Block> blockList = new ArrayList<Block>();
//	Image bgImg;
	GameBg[] bg = new GameBg[2];
	boolean flag = false;
	boolean over = false;
	int score = 0;
	ArrayList<HP> hpList = new ArrayList<HP>();

	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		createBg();// 배경생성
		createHero();// 주인공 생성
		createEnemy();// 적군 생성
		createBlock();// 블럭 생성
		createBg();// 배경 생성
		createHp();// hp 생성

		loopThread = new Thread() {
			@Override
			public void run() {
				while (true) {
					if (flag) {
						gameLoop();
					}
					try {
						Thread.sleep(10); // 1/100 초
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		loopThread.start();

	}

	// 1) 플랫폼종속된 경로 : Toolkit
	// 2) 클래스패스 : 클래스로더.getResources()
	public void createHero() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/plane.png", 100, 65).getImage();
		hero = new Hero(this, img, 200, 200, 100, 65, 0, 0);
	}

	// 게임윈도우로부터 어떤 식을 입력받았는지
	public void moveKey(int key) {
		// System.out.println(key);
		switch (key) {
		case KeyEvent.VK_LEFT:
			hero.velX = -5;
			break;
		case KeyEvent.VK_RIGHT:
			hero.velX = 5;
			break;
		case KeyEvent.VK_UP:
			hero.velY = -5;
			break;
		case KeyEvent.VK_DOWN:
			hero.velY = 5;
			break;
		case KeyEvent.VK_SPACE:
			fire();
			break;
		}
	}

	public void stopKey(int key) {
		switch (key) {
		case KeyEvent.VK_LEFT:
			hero.velX = 0;
			break;
		case KeyEvent.VK_RIGHT:
			hero.velX = 0;
			break;
		case KeyEvent.VK_UP:
			hero.velY = 0;
			break;
		case KeyEvent.VK_DOWN:
			hero.velY = 0;
			break;
		}
	}

	// 총알발사
	public void fire() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/ball.png", 20, 20).getImage();
		Bullet bullet = new Bullet(this, img, hero.x + hero.width, hero.y + (hero.height / 2), 20, 20, 10, 0);
		bulletList.add(bullet);// 생성된 총알을 배열에 담자!
	}

	// 적군생성
	public void createEnemy() {
		String[] path = { "e1.png", "e2.png", "e3.png", "e4.png", "e5.png" };

		for (int i = 0; i < 20; i++) {
			double r = Math.random();
			int n = (int) (r * path.length);
			Image img = ImageUtil.getIcon(this.getClass(), "res/game/" + path[n], 80, 65).getImage();
			Enemy enemy = new Enemy(this, img, WIDTH - 50, 10 + (50 * i), 80, 65, -3, 0);
			enemyList.add(enemy);
		}
	}

	// 적군생성
	public void createBlock() {
		for (int i = 0; i < 20; i++) {
			Image img = ImageUtil.getIcon(this.getClass(), "res/game/block.png", 60, 60).getImage();
			Block block = new Block(img, 300 + (i * 60), 600, 60, 60, 0, 0);
			blockList.add(block);
		}
	}

	// 배경이미지 생성
	public void createBg() {
		Image bgImg = ImageUtil.getIcon(this.getClass(), "res/game/bg.jpg", WIDTH, HEIGHT).getImage();
		for (int i = 0; i < bg.length; i++) {
			bg[i] = new GameBg(bgImg, WIDTH * i, 0, WIDTH, HEIGHT, -2, 0);
		}
	}

	public void createHp() {
		Image hpImg = ImageUtil.getIcon(this.getClass(), "res/game/plane.png", 50, 50).getImage();
//		System.out.println(hpImg);
		for (int i = 0; i < 4; i++) {
			hpList.add(new HP(hpImg, 10 + (60 * i), 70, 50, 50, 0, 0));
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; // 2D에 더 적합한 그래픽스 객체로 형변환

		// 전체지우기
		g2.setColor(Color.white);
		g2.fillRect(0, 0, WIDTH, HEIGHT); // 패널의 크기만큼 사각형이 채워진다(화면을 깨끗이 닫는 효과)

		render(g2);
	}

	// 게임의 상황, 정보 출력
	public void printData(Graphics2D g2) {
		if (!over) {
			g2.setFont(new Font("Verdana", Font.BOLD, 30));

			StringBuffer sb = new StringBuffer();
			sb.append("Bullet: " + bulletList.size());
			sb.append(" Enemy: " + enemyList.size());
			sb.append(" Score: " + score);

			g2.drawString(sb.toString(), 0, 50);
		} else {
			showGameOver(g2);
		}
	}

	public void showGameOver(Graphics2D g2) {
		g2.setFont(new Font("Verdana", Font.BOLD, 200));

		StringBuffer sb = new StringBuffer();
		sb.append("Game Over");
		g2.drawString(sb.toString(), 150, 400);
	}

	// 물리량 변화
	public void tick() {
		hero.tick();
		// 다수의 총알에 대한 tick()
//		for(Bullet tmp:bulletList) {
//			
//		}
		// 배경에 대한 tick()
		for (int i = 0; i < bg.length; i++) {
			bg[i].tick();
//			if(bg[i].x<=-WIDTH) {
//				bg[i].x=WIDTH;
//			}
		}
		// 총알에 대한 tick
		for (int i = 0; i < bulletList.size(); i++) {
			Bullet bullet = bulletList.get(i);
			bullet.tick();
		}
		// 적군에 대한 tick
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			enemy.tick();
		}
		// 블럭에 대한 tick
//		for (int i = 0; i < enemyList.size(); i++) {
//			Block block = blockList.get(i);
//			block.tick();
//		}
	}

	// 렌더링 (이미지처리)
	public void render(Graphics2D g2) {
//		g2.drawImage(bgImg, 0, 0, this);

		// 배경에 대한 render
		for (int i = 0; i < bg.length; i++) {
			bg[i].render(g2);
		}

		hero.render(g2);
		// 다수의 총알에 대한 render()
		for (int i = 0; i < bulletList.size(); i++) {
			Bullet bullet = bulletList.get(i);
			bullet.render(g2);
		}
		// 적군에 대한 render
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			enemy.render(g2);
		}
		// 블럭에 대한 render
		for (int i = 0; i < blockList.size(); i++) {
			Block block = blockList.get(i);
			block.render(g2);
		}
		printData(g2);
		for (int i = 0; i < hpList.size(); i++) {
			HP hp = hpList.get(i);
//			System.out.println(hp);
			hp.render(g2);
		}

	}

	// 모든 게임의 tick(), render()를 호출! 즉 게임엔진!!
	public void gameLoop() {
		tick();
		this.repaint();
//		System.out.println("GameLoop On...");
	}
}
