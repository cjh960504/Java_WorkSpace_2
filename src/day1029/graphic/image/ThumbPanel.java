package day1029.graphic.image;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class ThumbPanel extends Canvas implements MouseListener{
	String url;
	Toolkit kit = Toolkit.getDefaultToolkit();
	Image img;
	CenterPanel p;
	
	public ThumbPanel(String url, CenterPanel p) {
		this.p = p;
		this.setPreferredSize(new Dimension(100, 100));
		this.url= url;
		img = kit.getImage(url);
		img = img.getScaledInstance(100, 100, img.SCALE_SMOOTH);
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		p.setImage(img);
		p.repaint();
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

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
