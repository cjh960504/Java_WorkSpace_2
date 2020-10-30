package day1030.album;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class XCanvas extends Canvas{
	public static final int WIDTH=660;
	public static final int HEIGHT=450;
	private Toolkit kit=Toolkit.getDefaultToolkit();
	private Image img;
	private String src;
	
	public XCanvas(String src) {
		img=kit.getImage(src);
		img=img.getScaledInstance(WIDTH, HEIGHT, img.SCALE_SMOOTH);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.cyan);
	}

	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
		img=kit.getImage(src); 
		img=img.getScaledInstance(WIDTH, HEIGHT, img.SCALE_SMOOTH);
}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
}
