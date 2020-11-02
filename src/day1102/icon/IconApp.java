package day1102.icon;

import java.awt.FlowLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class IconApp extends JFrame{
	JButton bt1;
	
	ImageIcon icon1;
	public IconApp() {
		//os에 의존적인 경로를 사용하지않고, 클래스패스를 기준으로 한 경로로 자원을 이용해보자!
//		icon1 = new ImageIcon("/res/github.png");
		URL url =this.getClass().getClassLoader().getResource("res/github.png");
		System.out.println(url);
		icon1 = new ImageIcon(url);
		Image img = icon1.getImage();
		img = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		icon1.setImage(img);
		bt1=new JButton(icon1);
		
		add(bt1);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		new IconApp();
	}
}
