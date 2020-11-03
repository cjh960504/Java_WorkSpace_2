package day1103.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class KeyBoardFileApp {
	String dir;
	FileWriter writer;

	public KeyBoardFileApp() {
		URL url = this.getClass().getClassLoader().getResource("res/");
		URL url2;
		try {
			url2 = new URL(url, "empty.txt"); //디렉터리와 파일의 조합
			URI uri = url2.toURI();
//			System.out.println(uri);
			writer = new FileWriter(new File(uri));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveFile() {
		// 키보드로부터 입력받은 데이터를 파일로 저장해본다!
		// 키보드 스트림은 System 으로부터 얻어온다
		InputStream is = System.in; // 바이트
		InputStreamReader reader = new InputStreamReader(is);// 문자기반스트림으로 업그레이드
		BufferedReader buffR = new BufferedReader(reader);// 버퍼기반의 문자스트림으로 업그레이드

		// 파일출력스트림 계열은 (empty)빈 파일을 생성해준
//				FileWriter writer=new FileWriter(null);
		String msg = null;

		try {
			msg = buffR.readLine();
			System.out.println(msg);
			writer.write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new KeyBoardFileApp().saveFile();
	}
}
