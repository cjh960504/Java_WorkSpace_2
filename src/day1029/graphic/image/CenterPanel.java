package day1029.graphic.image;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class CenterPanel extends JPanel{
	Image img;
	
	public void setImage(Image img) {
		this.img = img;
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
}
