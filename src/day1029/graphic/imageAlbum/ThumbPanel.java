package day1029.graphic.imageAlbum;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThumbPanel extends JPanel implements MouseListener{
	Toolkit kit = Toolkit.getDefaultToolkit();
	Image img;
	ImagePanel p_center;
	JLabel imgName;
	String src;
	
	public ThumbPanel(String src, ImagePanel p_center, JLabel imgName) {
		this.src=src;
		this.p_center=p_center; 
		this.imgName = imgName;
		img=kit.getImage(src);
		img=img.getScaledInstance(80, 100,  img.SCALE_SMOOTH);
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
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		imgName.setText(src);
		p_center.img=img.getScaledInstance(600, 500, img.SCALE_SMOOTH);
		p_center.repaint();
	}
}
