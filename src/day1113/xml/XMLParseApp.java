/*
 * java로 xml를 파싱하는 방법은 크게 2가지 있다.
 * 1)DOM 방식 - html과 같은 원리...
 * 						즉, 모든 태그마다 1:1대응하는 DOM객체를 메모리에 생성해놓고 프로그래밍언어에서 필요한 객체를 접근하는 방식
 * 						예) javascript - DOM
 * 						무거운 DOM객체가 메모리에 부하를 일으킬 수 있다.. 특히 메모리크기가 pc에 비해 상대적으로 작은 디바이스의 경우 DOM방식은 적절치못하다!!
 * 2)SAX 방식 - xml문서를 이루는 엘리먼트, 텍스트 등의 모든 노드에 대한 이벤트를 발생시켜주는 방식
 * 					따라서 개발자는 적절한 자바의 객체를 메모리에 올려, xml을 대신하여 데이터를 사용하면 된다!!
 * 
*/
package day1113.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XMLParseApp {
	// 실행중인 자바프로세스가 파일에 접근하기 위해서는 파일입력스트림 계열이 필요하다!
	FileInputStream fis;
	InputStreamReader reader;
	BufferedReader buffr;
	File file;
	URL url;
	URI uri;
	
	public XMLParseApp() {
		url = this.getClass().getClassLoader().getResource("res/pets.xml");
		try {
			uri = url.toURI(); // File 클래스의 생성자에서는 URL이 아닌 URI를 원하므로, 변환하자!
			fis = new FileInputStream(file = new File(uri)); //스트림얻기!! 현재 1byte씩얻는상태
			parseData(); //파싱시작!!
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//xml파싱해보자!!
	public void parseData() {
		//SAX방식의 파서는 SAXParserFactory 객체로부터 얻는다.
		SAXParserFactory factory; 
		factory = SAXParserFactory.newInstance(); //static메서드를 이용하여 인스턴스얻음
		try {
			SAXParser saxParser=factory.newSAXParser();//Factory로부터 파서의 인스턴스를 얻을 수 있다..
			saxParser.parse(fis, new MyHandler());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readTest() {
		try {
			
			//육안으로 확인할 때 한글이 깨질 수 있으므로, Reader로 업그레이드하자
			reader = new InputStreamReader(fis); //문자로 얻는 걸로 업그레이드
			buffr = new BufferedReader(reader);// 한 줄씩 얻는 걸로 업그레이드
			//한문자씩 읽어들이면 너무 시간이 오래걸리므로, 한줄씩 읽어 들이기
			
			//파싱은 나중에하고, 먼저 xml을 제대로 스트림으로 읽어들일 수 있는 지 체크
			String data = null;
			while(true) {
				data = buffr.readLine();
				if(data==null) break;
				System.out.println(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new XMLParseApp();
	}
}
