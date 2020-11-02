package day1102.inner;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TestApp extends JFrame {
	Canvas can;
	JButton bt;
	JTextField t;

	public TestApp() {
		// 캔버스를 현재 클래스에서만 사용하고, 추후 재사용성이 없을 경우
		// 굳이 거푸집(.java)으로 정의할 이유가 있을까??
		// 즉, 일회성의 객체 사용인 경우 내부 익명 클래스로 사용할 수 있다..
		// Inner Anonymous Class : 내부 익명 클래스
		// 형식은 앞의 new로 선언된 클래스를 구현한 클래스로 이해하면 된다~
		// 주로, 이벤트를 구현하는 클래스는 해당 어플리케이션에만 사용되므로
		// 재사용가능성이 적다. 따라서 내부익명클래스로 많이 사용된다!
		can = new Canvas() {
			@Override
			public void paint(Graphics g) {
				g.drawLine(0, 0, 100, 100);
			}
		};
		setLayout(new FlowLayout());
		add(bt = new JButton("나 버튼") {
			@Override
			public void paint(Graphics g) {
				g.setColor(Color.RED);
				g.fillRect(0, 0, 100, 40);
			}
		});
		//내부익명클래스는 지역변수가 final로 된것만 접근이 가능
		final int x=0;
		//Action이벤트 연결
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(x+"버튼 눌렀어?");
			}
		});
		add(t = new JTextField(20));
		//키 이벤트 연결
		t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("키 눌럿니");
			}
		});
		//윈도우 이벤트 연결
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("잘가고~");
				System.exit(0);
			}
		});
		//내부익명클래스는 멤버변수를 참조하고 위해 쓰인다!!!!
		//final지역변수아닌 이상 지역변수는 접근못한다!!
		add(can);
		can.setPreferredSize(new Dimension(300, 300));
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new TestApp();
	}
}
