package day1111.json;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonGallery extends JFrame{
	// 상세내용출력,큰 그림 패널, 썸네일붙일, 그리드 적용할
	JPanel p_detail, p_can, p_south, p_center;
	Movie thumb;
	Image big; //상세이미지
	String[] la_title = {"Title", "Phase", "Category", "Release"};
	JLabel[] la = new JLabel[4];
	
	public JsonGallery() {
		p_can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(big, 0, 0, p_can);
			}
		};
		p_detail = new JPanel();
		for(int i=0;i<la.length;i++) {
			la[i] = new JLabel(la_title[i]);
			la[i].setPreferredSize(new Dimension(380, 50));
			la[i].setFont(new Font("Verdana", Font.BOLD, 15));
			p_detail.add(la[i]);
		}
		p_center = new JPanel();
		p_south = new JPanel();
//			createThumb();
		p_center.setLayout(new GridLayout(1, 2));// 1층 2호수 그리드 적용
		
		
		// 스타일 적용
		p_center.setBackground(Color.yellow);
		p_south.setBackground(Color.green);
		p_can.setBackground(Color.RED);
		p_detail.setBackground(Color.blue);
		p_south.setPreferredSize(new Dimension(800, 100));

		p_center.add(p_can);
		p_center.add(p_detail);
		add(p_center);
		add(p_south, BorderLayout.SOUTH);

		setSize(800, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		createThumb(); // 썸네일 구성하기

	}

	// 영화 썸네일 생성하기!
	public void createThumb() {
		BufferedReader buffr = null;

		try {
			// 클래스패스 상에 있는 텍스트 파일 읽기
			URL url = this.getClass().getClassLoader().getResource("res/data.json");
			URI uri = url.toURI(); // url을 uri로 변경!
			FileReader reader = new FileReader(new File(uri));
			buffr = new BufferedReader(reader);
			StringBuffer sb = new StringBuffer();
			String data = null;
			while (true) {
				data = buffr.readLine();
				if (data == null)
					break;
				sb.append(data);
			}
			System.out.println(sb.toString());
			// 파싱
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
			JSONArray jsonArray = (JSONArray) jsonObject.get("marvel");// 문자열에 불과했던 json 표기법문자열을 실제 json 객체로 반환!!

			// 따라서 이 시점부터 마치 객체처럼 접근하여 사용이 가능하다!!
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);// 영화한편 반환!!
				String _url= (String) obj.get("url");
				String title = (String) obj.get("title");
				String phase = (String) obj.get("phase");
				String category_name = (String) obj.get("category_name");
				String release_year =  ((Long)obj.get("release_year")).toString();
				Movie thumbnail = new Movie(this, 45, 45, _url, title, phase, category_name, release_year);
				p_south.add(thumbnail);
//				p_south.updateUI(); // 모두 로드하고 생긴 패널들을 보여주기위해
				// 패널을 아예 생성하고 붙였으니까 updateUI()
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
 
	//상세내용 출력하기
	public void getDetail(Image big, String[] la_data) {
		//이미지처리
		this.big = big;
		p_can.repaint();
		
		//영화정보 출력
		for(int i=0;i<la_data.length;i++) {
			la[i].setText(la_title[i]+" : "+la_data[i]);
		}
	}
	public static void main(String[] args) {
		new JsonGallery();
	}
}
