package day1028.graphic;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;

/* 지금까지는 sun사의 개발자가 제공해준 그대로 컴포넌트들을 보아왔지만,
 * 이 예제에서는 우리가 컴포넌트의 그려지는 방식을 간섭하여, 원하는 그림으로
 * 컴포넌트가 보여지도록 처리해보자!!(그래픽 처리에 관여.. - 즉, 우리 주도하에 그림 그리자) */
public class PaintTest extends JFrame{
	//현재 클래스인 PaintTest가 JFrame의 paint메서드를 오버라이드하면,
	//실행 시 자식이 재정의한 메서드를 우선 순위로 호출된다..(구현력은 자식클래스에)
	//버튼, 프레임, 초이스 컴포넌트 등 거의 다 방해하지말자.. 하려면 제대로
	//그런데, 컴포넌트 중 개발자가 주도해서 그림을 그릴 수 있는 컴포넌트
	//Canvas(AWT), JPanel(텅 비어있는 컴포넌트)	
	MyCanvas can; //도화지를 표현한 컴포넌트 
	public PaintTest() {
		can = new MyCanvas();
		can.setBackground(Color.RED);
		//캔버스에 그림을 그리려면, 캔버스가 스스로 그리는 메서드인 paint()메서드를 재정의
		add(can);//캔버스를 프레임에 부착!
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//부모의 메서드를 오버라이딩
//	public void paint(Graphics g) {
//		System.out.println("저 지금 직접 스스로를 그려요");
//	}
	public static void main(String[] args) {
		new PaintTest();
	}
}
