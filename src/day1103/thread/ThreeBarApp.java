package day1103.thread;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ThreeBarApp extends JFrame {
	JLabel la1, la2, la3;
	JProgressBar bar1, bar2, bar3;
	BarThead barThread1, barThread2, barThread3;

	public ThreeBarApp() {
		la1 = new JLabel("0%");
		la2 = new JLabel("0%");
		la3 = new JLabel("0%");
		
		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();		
		//코드가 중복되니 내부익명클래스로 하지말고 쓰레드클래스를 생성하여
		//재사용하자
		barThread1 =  new BarThead(la1, bar1, 100);	
		barThread2 = new BarThead(la2, bar2, 250);	
		barThread3 = new BarThead(la3, bar3, 500);

		
		la1.setFont(new Font("verdana", Font.BOLD|Font.ITALIC, 20));
		la2.setFont(new Font("verdana", Font.BOLD|Font.ITALIC, 20));
		la3.setFont(new Font("verdana", Font.BOLD|Font.ITALIC, 20));
		bar1.setPreferredSize(new Dimension(500, 50));
		bar2.setPreferredSize(new Dimension(500, 50));
		bar3.setPreferredSize(new Dimension(500, 50));

		add(bar1);
		add(la1);
		add(bar2);
		add(la2);
		add(bar3);
		add(la3);
		
		setLayout(new FlowLayout());
		setSize(600, 280);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		barThread1.start();
		barThread2.start();
		barThread3.start();

	}

//	public void setBarValue(JLabel la ,JProgressBar bar, int n) {
//		int count=0;
//		while (count<=100) {
//			count+=n;
//			bar.setValue(count);
//			la.setText(count+"%");
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	public static void main(String[] args) {
		new ThreeBarApp();
	}
}
