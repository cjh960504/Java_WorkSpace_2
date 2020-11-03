package day1103.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CircleMove extends JFrame {
	JPanel can;
	JButton bt;
	int x=100, y=100;
	Thread thread;
	
	public CircleMove() {
		// 내부익명클래스도 클래스이다? O => 명칭이 없으므로
		// 내부적으로 현재클래스명$순번.class로 존재!
		can = new JPanel() {
			// 내부익명클래스 사용 시 장점?
			// 1) .java를 굳이 만들지 않아도 된다.
			// 2) 외부클래스의 멤버를 마치 자기꺼처럼 사용할 수 있다.
			public void paint(Graphics g) {
				g.setColor(Color.blue); //페인트 색상 적용 
				g.fillRect(0, 0, 700, 550); //채워진 사각형..
				
				//원그리기
				g.setColor(Color.white);
				g.fillOval(x, y, 50, 50); //채워진 원
			}
		};

		bt=new JButton("움직이기");
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//쓰레드를 runnable 로 진입시키자~
				thread.start();
			}
		});
		
		thread = new Thread() {
			public void run() {
				while(true) {
					move();
					can.repaint(); // update()->paint()
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		//스타일
		can.setPreferredSize(new Dimension(700, 550));
		
		setLayout(new FlowLayout());
		add(bt);
		add(can);
		
		setSize(740, 640);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void move() {
		x+=2;
		y+=2;
		
	}
	public static void main(String[] args) {
		new CircleMove();
	}
}
