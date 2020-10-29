package day1029.graphic.image;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePicker extends JFrame{
	JPanel p_north;
	CenterPanel p_center;
	ThumbPanel[] thumb = new ThumbPanel[7];
	String[] img = {"aa.jpg"
			, "ab.jpg"
			, "ax.jpg"
			, "bm.jpg"
			, "et.jpg"
			, "girl.jpg"
			,"kg.jpg"
			};
	String dir = "C:/java_workspace/workspace/res/travel/";
	public ImagePicker() {
		p_center =  new CenterPanel();
		p_north = new JPanel();
		for(int i=0;i<thumb.length;i++) {
			thumb[i] = new ThumbPanel(dir+img[i], p_center);
			p_north.add(thumb[i]);
		}
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		setSize(770, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new ImagePicker();
	}
}
