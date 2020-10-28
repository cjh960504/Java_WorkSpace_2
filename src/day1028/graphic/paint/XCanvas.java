package day1028.graphic.paint;

import java.awt.Canvas;
import java.awt.Graphics;

public class XCanvas extends Canvas{
	//캔버스는 개발자가 직접 그림을 그릴 수 있는 반 도화지이다!!
	//따라서, paint() 메서드를 재정의하면 된다..
	LineMaker2 lineMaker;
	int x1, y1, x2, y2;
	
	public void setLineMaker(LineMaker2 linemaker) {
		this.lineMaker = linemaker;
	}
	
	//이 메소드를 호출하는 자는, LineMaker의 주소값을 넘기면 된다!!
	//생성자뿐만아니라 일반 메서드도 사용가능함!
	@Override
	public void paint(Graphics g) {
		
		x1 = Integer.parseInt(lineMaker.t_x1.getText());
		y1 = Integer.parseInt(lineMaker.t_y1.getText());
		x2 = Integer.parseInt(lineMaker.t_x2.getText());
		y2 = Integer.parseInt(lineMaker.t_y2.getText());
		
		g.drawLine(x1, y1, x2, y2); // 두 점을 연결한 선 그리기
	}
}
