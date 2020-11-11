package day1111.json;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Movie extends JPanel implements Runnable {
	Image big; //전달할 큰 이미지
	Image img; // 썸네일로 그려질 이미지
	BufferedImage bufferedImg;
	int width, height;
	Thread thread;
	JsonGallery jsonGallery;
	
	//이 객체는 한편의 영화를 표현하는 클래스이다!!! 따라서 영화에 대한 정보를 담고 있어야한다!!
	String title;
	String url;
	String phase;
	String category_name;
	String release_year;

	public Movie(JsonGallery jsonGallery, int width, int height, String url, String title, String phase, String category_name, String release_year) {
		this.width = width;
		this.height = height;
		this.url = url;
		this.title = title;
		this.phase = phase;
		this.category_name = category_name;
		this.release_year=release_year;
		this.jsonGallery = jsonGallery;
		this.setPreferredSize(new Dimension(width, height));
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("클릭한 저의 영화제목은 "+title);
				String[] la_data = {title, phase, category_name, release_year};
				jsonGallery.getDetail(big, la_data);
			}
		});
		thread = new Thread(this); // Runnable을 구현한 객체를 인수로 넣어준다.
		thread.start();
	}

	public void getErrorImage() {
		URL url = this.getClass().getClassLoader().getResource("res/error404.png");
		try {
			BufferedImage buffImg = ImageIO.read(url);
			big = buffImg.getScaledInstance(400, 550, Image.SCALE_SMOOTH);
			img = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 썸네일 그림 그리기
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void run() {
		// 1.이미지가 로컬 하드에 있을 경우 Toolkit을 사용!

		// 2.이미지가 클래스패스상 즉, 패키지에 있을 경우 ClassLoader()로 이용

		// 3.BufferedImage > ImageIO
		try {
			URL url = new URL(this.url);
			bufferedImg = ImageIO.read(url);
			big = bufferedImg.getScaledInstance(400, 550, Image.SCALE_SMOOTH); //큰이미지(전달용)
			img = bufferedImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);//작은이미지(썸네일용)
			jsonGallery.p_south.updateUI();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("이미지를 찾을 수 없네용");
			getErrorImage();
		}
	}

 
}
