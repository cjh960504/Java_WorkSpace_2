package day1103.thread;

import java.util.Calendar;

public class TimeThread extends Thread {
	@Override
	public void run() {
		while (true) {
			// 현재 시간을 구해서 1초마다 갱신해서 출력!!
			Calendar cal = Calendar.getInstance(); // 추상클래스이므러 자체 메서드로 인스턴스 얻자!
			System.out.println(cal.get(Calendar.YEAR)+"년 "+
			(cal.get(Calendar.MONTH)+"월 "+cal.get(Calendar.DATE)+"일 ")
			+cal.get(Calendar.HOUR)+"시 "+cal.get(Calendar.MINUTE)+"분 "+cal.get(Calendar.SECOND)+"초");
			try {
				Thread.sleep(1000);// 1/1000초까지 표현가능
				// 1초동안 non-runnable로 상태로 있다가 다시 복귀하라는 명령
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
