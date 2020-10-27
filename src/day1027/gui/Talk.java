package day1027.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Talk extends JFrame {
	JTextArea area;
	JPanel p;
	JButton bt;
	JTextField f;
	JScrollPane sp;
	public Talk() {
		// TODO Auto-generated constructor stub
		
		area = new JTextArea();
		bt = new JButton("전송");
		f = new JTextField(15);
		p = new JPanel(new FlowLayout());
		sp = new JScrollPane(area);
		p.add(f);
		p.add(bt);

		setLayout(new BorderLayout());
		area.setBackground(Color.yellow);
		area.setFont(new Font("돋움체", Font.BOLD, 20));
		
		add(sp);
		add(p, BorderLayout.SOUTH);
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Talk();
	}
}
