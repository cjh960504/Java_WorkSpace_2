/*
	게임에 등장하는 모든~ 요소는 이 객체의 자식이다!!!
	따라서 게임오브젝트 클래스에는 보편적인 특징만 보유해야한다..
*/
package day1103.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {
	int x, y, width, height;
	int velX, velY;
	Image img;
	Rectangle rect;
	
	public GameObject(Image img, int x, int y, int width, int height, int velX, int velY) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		rect = new Rectangle(x, y, width, height); //기존에 가지고 있는 좌표, 너비, 높이 정보를 이용하여
																		//사각형을 생성!
	}

	// 물리량 변화(데이터의 변화)
	public abstract void tick(); // 하위 객체가 어떤 내용으로 물리량을 변화시킬지 부모인 현재 시점에서는
									// 알수도없거니와, 알아서도 안된다!!
									// 이렇게 미완성으로 남겨놓으면,
									// 미래 어느 시점에 자식이, 이 메서드를 자신의 상황에 맞게
									// 재정의할 날이 올 것이다!!(오버라이딩)

	public abstract void render(Graphics2D g2);

}
