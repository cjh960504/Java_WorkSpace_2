package day1030.album;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

//썸네일 하나를 정의한다!
public class Thumb extends JPanel implements MouseListener{
	//플랫폼에 의존적인 경로를 이용할 때 툴킷이 필요 window: c://...
	Toolkit kit=Toolkit.getDefaultToolkit();
	Image img;
	public static final int WIDTH=75;
	public static final int HEIGHT=55;
	GalleryApp app;
	String src;
	
	public Thumb(String src, GalleryApp app) {
		this.app =app;
		this.src = src;
		
		img = kit.getImage(src);
		img = img.getScaledInstance(WIDTH, HEIGHT, img.SCALE_SMOOTH);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.black);
		
		//리스너와 연결
		this.addMouseListener(this);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//index를 바꾸자 지금나의 index값으로
		//현재 패널이 ArrayList 내에서의 몇번째 인덱스에 들어있는지 [[역추적]]!!
		app.index = app.list.indexOf(this);
		app.updateData();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
