package day1029.graphic.ImageAlbum2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class ThumbPanel extends JPanel implements MouseListener{
	String src;
	CenterPanel p_center;
	Image img;
	Toolkit kit = Toolkit.getDefaultToolkit();
	
	public ThumbPanel(String src, CenterPanel p_center) {
		this.src = src;
		this.p_center = p_center;
		this.img = kit.getImage(src);
		img=img.getScaledInstance(200, 100, img.SCALE_SMOOTH);
		this.setPreferredSize(new Dimension(200, 100));
		this.addMouseListener(this);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
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
		this.p_center.setImage(src);
		this.p_center.repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
