/*
	진행상황을 직관적으로 알 수 있는 프로그래스바를 사용해보자!
*/
package day1103.thread;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class ProgressApp extends JFrame{
	JLabel la;
	JProgressBar bar;
	Thread barThread; // 바를 증가시킬 쓰레드
	int n=0;
	
	public ProgressApp() {
		la = new JLabel("0", SwingConstants.CENTER);
		bar = new JProgressBar();
		barThread = new Thread() {
			public void run() {
				while(n<=100) {
					n++;
					setBarValue();	
					setLabelValue();
					try {
						Thread.sleep(50);//non-runnable에 빠져있다가 0.5초 뒤 복귀하라는 명령
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		//스타일
		la.setPreferredSize(new Dimension(500, 150));
		la.setFont(new Font("Verdana", Font.BOLD|Font.ITALIC, 90));
		bar.setPreferredSize(new Dimension(500, 30));
		
		add(la);
		add(bar);
		
		setLayout(new FlowLayout());
		setSize(600, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		barThread.start(); //쓰레드 동작!
	}
	
	public void setLabelValue() {
		la.setText(n+"%");
	}
	
	//바의 값 제어
	public void setBarValue() {
		bar.setValue(n);//20퍼센트가 채워짐
	}
	
	public static void main(String[] args) {
		new ProgressApp();
	}
}
