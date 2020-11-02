/*
	리스너는 인터페이스이기 떄문에, 리스너를 구현하는 자는 반드시
	추상메서드를 재정의해야한다..
	하지만, 리스너가 보유한 추상메서드의 수가 너무 많은 경우
	사용하지도 않은 비어있는 메서드를 우리가 정의한 클래스 내에 두는 것이 효율적이지 못함
	따라서, Sun에서는 리스너메서드가 3개이상일경우, 각각의 리스너를 구현한 어댑터라는 객체를 지원!
	ex) WindowListener를 구현한 클래스 WindowAdapter
		MouseListener- MouseAdapter
		KeyListener - KeyAdapter
*/
package day1102.event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWinAdapter extends WindowAdapter{
	 @Override
	public void windowClosed(WindowEvent e) {//창에 의해 프로그램 종료 시
		 System.out.println("windowClosed");
	} 
	 
	 @Override
	public void windowClosing(WindowEvent e) {
		 System.out.println("windowClosing");
		 System.exit(0); //프로세스 종료
	 }
}
