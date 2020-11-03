/*
	게임에 등장하는 모든~ 요소는 이 객체의 자식이다!!!
	따라서 게임오브젝트 클래스에는 보편적인 특징만 보유해야한다..
*/
package day1102.game;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class GameObject {
	int x, y, width, height;
	int velX, velY;
	
	public GameObject(int x, int y, int width, int height, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
	}
	
	//물리량 변화(데이터의 변화)
	public abstract void tick(); //하위 객체가 어떤 내용으로 물리량을 변화시킬지 부모인 현재 시점에서는
											//알수도없거니와, 알아서도 안된다!!
											//이렇게 미완성으로 남겨놓으면, 
											//미래 어느 시점에 자식이, 이 메서드를 자신의 상황에 맞게
											//재정의할 날이 올 것이다!!(오버라이딩)
	public abstract void render(Graphics2D g2); 
	
}
