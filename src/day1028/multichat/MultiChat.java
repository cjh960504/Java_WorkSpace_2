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

public class MultiChat extends JFrame implements KeyListener , ActionListener{
	JTextArea area;
	JTextField t;
	JButton bt_send, bt_open;
	JPanel p_south;
	ArrayList<MultiChat2> otherChat;
	public MultiChat() {
		otherChat = new ArrayList<MultiChat2>();
		area = new JTextArea();
		t = new JTextField(10);
		t.addKeyListener(this);
		bt_send = new JButton("Send");
		bt_send.addActionListener(this);
		bt_open = new JButton("Open");
		bt_open.addActionListener(this);
		p_south = new JPanel(); 
		area.setBackground(Color.yellow);
		
		add(area);
		p_south.add(t);
		p_south.add(bt_send);
		p_south.add(bt_open);
		add(p_south, BorderLayout.SOUTH);
		
		setBounds(300, 400, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void send() {
		String str = t.getText();
		area.append(str+"\n");
		for(int i=0;i<otherChat.size();i++) {
			MultiChat2 mc = otherChat.get(i);
			mc.area.append(str+"\n");
		}
	
		t.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bt_send) {
			send();
		}if(obj==bt_open) {
			otherChat.add( new MultiChat2(this, otherChat));
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MultiChat();
	}

}
