package day1028.graphic.color;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPicker2 extends JFrame{
	JPanel p_north;
	JPanel p_center;
	ThumbPanel[] thumb = new ThumbPanel[7];
	//7가지 색상을 배열로 보유하자 (반복문마다 틀린 색을 사용해야 하기 떄문)
	Color[] colorArray = {
			Color.RED,
			Color.orange,
			Color.yellow,
			Color.green,
			Color.cyan,
			Color.blue,
			Color.pink
	};
	public ColorPicker2() {
		p_north = new JPanel();
		p_center = new JPanel();
		for(int i=0;i<thumb.length;i++) {
			thumb[i] = new ThumbPanel(p_center, colorArray[i]);
			p_north.add(thumb[i]); 
		}
		p_center.setBackground(Color.WHITE);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		setSize(770, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new ColorPicker2();
	}
}
