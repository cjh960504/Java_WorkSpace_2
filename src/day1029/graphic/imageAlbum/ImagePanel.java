package day1029.graphic.imageAlbum;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	Toolkit kit = Toolkit.getDefaultToolkit();
	Image img;
	public ImagePanel(String src) {
		setImage(src);
	}	
	public void setImage(String src) {
		img = kit.getImage(src);
		img = img.getScaledInstance(600, 500, img.SCALE_SMOOTH);
		System.out.println(img);
		this.repaint();
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
}
