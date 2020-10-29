package day1028.multichat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiChat2 extends JFrame implements KeyListener , ActionListener{
	JTextArea area;
	JTextField t;
	JButton bt_send, bt_open;
	JPanel p_south;
	MultiChat multiChat;
	ArrayList<MultiChat2> otherChat;
	public MultiChat2(MultiChat multiChat, ArrayList<MultiChat2> otherChat) {
		this.multiChat= multiChat; 
		this.otherChat=otherChat;
		area = new JTextArea();
		t = new JTextField(10);
		t.addKeyListener(this);
		bt_send = new JButton("Send");
		bt_send.addActionListener(this);
		p_south = new JPanel(); 
		area.setBackground(Color.yellow);
		
		add(area);
		p_south.add(t);
		p_south.add(bt_send);
		add(p_south, BorderLayout.SOUTH);
		
		setBounds(600, 400, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void send() {
		String str = t.getText();
		area.append(str+"\n");
		multiChat.area.append(str+"\n");
		for(int i=0;i<otherChat.size();i++) {
			MultiChat2 mc = otherChat.get(i);
			if(this!=mc) {
				mc.area.append(str+"\n");							
			}
		}
		t.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bt_send) {
			send();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if(key==10) {
			send();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
