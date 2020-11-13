package day1113.xml.down;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import commom.file.FileManager;

public class DownLoader extends JFrame {
	JProgressBar bar;
	JButton bt_down;
	MovieHandler movieHandler;
	Thread parsingThread;

	public DownLoader() {
		bt_down = new JButton("다운로드");
		bar = new JProgressBar();

		// 스타일
		bar.setPreferredSize(new Dimension(580, 45));
		bar.setForeground(Color.blue);
		bar.setBackground(Color.gray);

		bar.setFont(new Font("Verdana", Font.BOLD, 35));
		bar.setStringPainted(true);
		bar.setValue(0); // 35%

		setLayout(new FlowLayout());
		add(bt_down);
		add(bar);

		parsingThread = new Thread() { // 메인 메서드가 오로지 디자인업무에 집중할 수 있게
			public void run() {
				parseData();
				//총 몇건이 존재하는 지 출력
				for(Movie obj:movieHandler.movieList) {
					download(obj.getUrl());
				}
			}
		};

		setSize(600, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		bt_down.addActionListener((e) -> { // 다운로드 버튼과 리스너 연결
			parsingThread.start();
		});
	}

	public void parseData() {
		// xml을 파싱하여 url만 추출해야함!!
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser(); // 파서객체 생성
			URL url = this.getClass().getClassLoader().getResource("res/marvel.xml");
			File file = new File(url.toURI());
			saxParser.parse(file, movieHandler = new MovieHandler());// 파싱 시작!!
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 인터넷상의 자원과 연결한 후, 스트림으로 데이터를 읽어와 로컬 하드 경로에 저장하기!!
	public void download(String path) {
		InputStream is = null;
		FileOutputStream fos = null; // 파일을 저장할 스트림
		int count=0;

		try {
			URL url = new URL(path); // 자원의 주소
			URLConnection con = url.openConnection(); // http커넥션을 만들기 위해
			HttpURLConnection http = (HttpURLConnection) con; // 인터넷상의 자원과 연결을 위한 객체
			// 웹에 특화된 커넥션 객체!! 따라서 get/post 등 웹기반의 요청이 가능

			http.setRequestMethod("GET");
			is = http.getInputStream();// 연결된 URL로부터 입력스트림 얻기!!
			long time = System.currentTimeMillis(); // 현재시간을 파일명으로 사용하자!!
			String fileName = FileManager.getFileName(path)+".jpg";


			fos = new FileOutputStream("C:/java_workspace/workspace/res/download/" + fileName);
			int data = -1;

			while (true) {
				data = is.read();
				bar.setValue(count++);
				if (data == -1)
					break;
				fos.write(data);
			}
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
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new DownLoader();
	}
}
