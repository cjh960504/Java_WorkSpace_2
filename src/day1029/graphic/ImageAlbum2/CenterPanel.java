package day1029.graphic.ImageAlbum2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CenterPanel extends JPanel{
	Toolkit kit = Toolkit.getDefaultToolkit();
	String src;
	JLabel name;
	Image img;
	
	public CenterPanel(JLabel name, String src) {
		this.name=name;
		this.src=src;
		this.name.setText(src);
		setImage(src);
	}
	
	public void setImage(String src) {
		name.setText(src);
		img = kit.getImage(src);
		img = img.getScaledInstance(700, 700, img.SCALE_SMOOTH);	
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
}
