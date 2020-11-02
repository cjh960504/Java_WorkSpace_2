/*
	실행중인 프로그램으로 데이터를 읽거나 쓸 때, 한 바이트, 한 문자씩 입출력을 수행하면
	데이터량이 많을 때 너무 많은 입출력을 수행하게 된다.. --> 속도저하
	마치 cmd 창의 버퍼처리처럼 메모리상의 버퍼에 데이터를 모아놓고, 한줄이 끝나는
	시점에만 입력 및 출력을 처리하면 프로그램 실행의 효율성이 극대화됨!!!
	스트림 중 버퍼를 지원하는 스트림은 접두어로 Buffered~~가 붙는다!!
	
	실습) 키보드로 입력된 데이터를 한줄씩 가져와서 화면에 출력해보자!
*/
package day1102.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedIOApp {
	public static void main(String[] args) {
		// keyboard에 연결된 스트림은 개발자가 원하는 타임에 new할수있다!
		// 왜?? OS가 이미 얻어다놓았으므로, 따라서 자바에서는 시스템으로부터 얻어올수있다

		// 입력스트림의 최상위 추상 클래스이다!!!!!
		InputStream is = System.in;

		// 한글이 깨지지않도록 문자기반으로 업그레이드
		InputStreamReader reader = new InputStreamReader(is);// 바이트기반을 문자기반스트림으로 변환
		BufferedReader buffR = new BufferedReader(reader);//버퍼처리된 문자기반의 입력스트림
		
		//int data;
		String data="";
		
		try {
			while(true) {
				data = buffR.readLine();// 1줄씩을 읽는다.
				System.out.println(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
