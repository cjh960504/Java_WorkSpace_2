/*
	지금까지는 URL상의 자원을 가져올 떄, 대상을 이미지로 하여왔었다..
	ex) ImageIO.read() 등 실습...
	지금 이 실습에서는 이미지뿐만 아니라 원격지의 모든 종류의 자원을 대상으로 연결하여
	스트림으로 데이터를 읽어보는 실습을 진행해본다.
*/
package day1113.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLLoadApp {
	// 웹상의 모든 자원을 대상으로, 연결 및 데이터를 읽어올 수 있는 객체
	URLConnection con; // 추상클래스이므로, URL객체로부터 인스턴스를 얻는다!!
	HttpURLConnection http; // HTTP의 최적화된 URL커넥션
	URL url;
	FileOutputStream fos;

	public URLLoadApp() {
		try {
			url = new URL("https://img.huffingtonpost.com/asset/5d706827250000ad0004642a.jpeg?ops=1200_630");

			// 지정한 원격지의 자원과 연결을 시도!!
			con = url.openConnection();// 추상클래스인 URLConnection의 인스턴스를 자원의 URL로부터 얻어오기
			http = (HttpURLConnection) con; // 자식객체로 형변환

			// http의 자원을 GET방식으로 자원을 요청하자!!
			http.setRequestMethod("GET");

			// 연결 객체로부터 스트림을 얻어와서 데이터를 읽어보자!
			InputStream is = http.getInputStream();

			// 파일로 저장해보자 파일데이터를 내 컴퓨터로 뿌리기 위해 Output
			File file = new File("C:/java_workspace/workspace/res/data/copy.jpg");
			fos = new FileOutputStream(file);

			// 한바이트씩 읽어와서 출력스트림을 이용하여 파일에 쓰자
			int data = -1; //처음에는 읽어들인 데이터가 없다고 가정
			
			while (true) {
				data = is.read();
				if (data == -1)
					break;
				fos.write(data);
			}
			
			System.out.println("인터넷 상의 파일을 로컬로 저장완료했습니다.");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new URLLoadApp();
	}
}
