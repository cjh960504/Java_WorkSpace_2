package day1028.graphic;

import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class MyCanvas2 extends Canvas{
	int x1, x2, y1, y2;
	public MyCanvas2() {
	}
	public void setPoint(int x1,int x2,int y1,int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	public void paint(Graphics g) {
		g.drawLine(x1, x2, y1, y2);
	}
}
