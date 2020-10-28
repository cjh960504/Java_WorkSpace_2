package day1028.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LineMaker extends JFrame implements ActionListener{
	JLabel[] l_Array = {new JLabel("x1"), new JLabel("x2"), new JLabel("y1"), new JLabel("y2")};
	JTextField[] t_Array = {new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5)};
	JPanel p;
	MyCanvas2 can;
	JButton bt;
	public LineMaker() {
		p = new JPanel();
		bt = new JButton("OK");
		can = new MyCanvas2();
		can.setBackground(Color.BLUE);
		bt.addActionListener(this);
		
		for(int i=0 ;i<l_Array.length;i++) {
			p.add(l_Array[i]);
			p.add(t_Array[i]);
		}
		p.add(bt);
		add(p, BorderLayout.NORTH);
		add(can);
		
		setSize(700, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int[] point = new int[4];

		for(int i=0;i<point.length;i++) {
			point[i]=Integer.parseInt(t_Array[i].getText());
		}
		can.setPoint(point[0], point[1], point[2], point[3]);
		can.paint(getGraphics());
	}
	
	public static void main(String[] args) {
		new LineMaker();
	}
}
