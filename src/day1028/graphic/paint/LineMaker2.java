package day1028.graphic.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.org.apache.xml.internal.utils.SuballocatedByteVector;

public class LineMaker2 extends JFrame {
	JLabel la_x1, la_y1, la_x2, la_y2;
	JTextField t_x1, t_y1, t_x2, t_y2;
	MyButton bt;//쓸데없지만 공부목적
	JPanel p_north;
	XCanvas can;
	
	public LineMaker2() {
		super("선 그리기 어플리케이션");
		
		//객체 생성
		la_x1 = new JLabel("x1");
		la_y1 = new JLabel("y1");
		la_x2 = new JLabel("x2");
		la_y2 = new JLabel("y2");
		
		t_x1 = new JTextField("0", 5);
		t_y1 = new JTextField("0", 5);
		t_x2 = new JTextField("199", 5);
		t_y2 = new JTextField("444", 5);
		
		p_north = new JPanel();
		can = new XCanvas();
		can.setLineMaker(this);
		bt = new MyButton("OK");
		bt.addActionListener(new MyListener(can));
		
		//조립
		p_north.add(la_x1);
		p_north.add(t_x1);
		p_north.add(la_y1);
		p_north.add(t_y1);
		p_north.add(la_x2);
		p_north.add(t_x2);
		p_north.add(la_y2);
		p_north.add(t_y2);
		p_north.add(bt);
		
		//스타일 적용
		can.setBackground(Color.yellow);

		//프레임에 붙이자
		add(p_north, BorderLayout.NORTH);
		add(can);
		
		setSize(700, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new LineMaker2();
	}
}
