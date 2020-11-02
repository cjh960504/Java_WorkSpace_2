package day1102.event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

// .java 재사용성 때문에...
public class WindowApp extends JFrame {
	public WindowApp() {
		addWindowListener(new WindowAdapter() {
			 @Override
				public void windowClosed(WindowEvent e) {//창에 의해 프로그램 종료 시
					 System.out.println("windowClosed");
				} 
				 
				 @Override
				public void windowClosing(WindowEvent e) {
					 System.out.println("windowClosing");
					 System.exit(0); //프로세스 종료
				 }
			});
		setSize(300, 400);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new WindowApp();
	}
}
