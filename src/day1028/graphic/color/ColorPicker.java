package day1028.graphic.color;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPicker extends JFrame implements MouseListener{

	JPanel p_north;
	JPanel p_1, p_2, p_3, p_4, p_5, p_6, p_7;
	JPanel p_center;
	public ColorPicker() {
		super("ColorPicker");
		p_1 = new JPanel();
		p_1.addMouseListener(this);
		p_2 = new JPanel();
		p_2.addMouseListener(this);
		p_3 = new JPanel();
		p_3.addMouseListener(this);
		p_4 = new JPanel();
		p_4.addMouseListener(this);
		p_5 = new JPanel();
		p_5.addMouseListener(this);
		p_6 = new JPanel();
		p_6.addMouseListener(this);
		p_7 = new JPanel();
		p_7.addMouseListener(this);
		p_north = new JPanel();
		p_center = new JPanel();
		
		p_north.setLayout(new GridLayout(1, 7));
		p_north.setPreferredSize(new Dimension(700, 100));
		p_1.setBackground(Color.red);
		p_2.setBackground(Color.orange);
		p_3.setBackground(Color.yellow);
		p_4.setBackground(Color.green);
		p_5.setBackground(Color.cyan);
		p_6.setBackground(Color.blue);
		p_7.setBackground(Color.pink);
		p_center.setBackground(Color.black);
		
		p_north.add(p_1);
		p_north.add(p_2);
		p_north.add(p_3);
		p_north.add(p_4);
		p_north.add(p_5);
		p_north.add(p_6);
		p_north.add(p_7);
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		setSize(700, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		JPanel tmp =  (JPanel)e.getSource();
		p_center.setBackground(tmp.getBackground());
	}
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {
//		JPanel tmp =  (JPanel)e.getSource();
//		p_center.setBackground(tmp.getBackground());
	}
	@Override
	public void mouseExited(MouseEvent e) {}

	public static void main(String[] args) {
		new ColorPicker();
	}

}
