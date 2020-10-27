package day1027.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements KeyListener ,ActionListener{
	private JTextArea area;
	private JScrollPane scroll;
	private JPanel p_south;
	private JTextField t_input;
	private JButton bt;
	
	public ChatClient() {
		//생성
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(15);
		bt = new JButton("Send");
		
		//패널 조립 (패널은 디폴트가 FlowLayout)
		p_south.add(t_input);		
		p_south.add(bt);
		
		add(p_south, BorderLayout.SOUTH);//남쪽에 패널부착
		add(scroll);
		
		//스타일 적용
		area.setBackground(Color.YELLOW);
		t_input.setBackground(Color.blue);
		t_input.setForeground(Color.white);
		bt.setBackground(Color.black);
		bt.setForeground(Color.white);
		
		t_input.setPreferredSize(new Dimension(265, 30));
		area.setEditable(false);
		//보여주기 전에 미리 연결해놓자(컴포넌트와 리스너 연결)
		bt.addActionListener(new MyActionListener(t_input, area));
		t_input.addKeyListener(this);//프레임이 곧 리스너다!!
		
		setLocation(800, 400);
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	//어노테이션, java5부터 지원되는 일종의 주석.. 일반적인 주석은 프로그램에관여 X
	//하지만, 어노테이션은 프로그래밍에서 어떤 표시를 하기 위함, 자주 사용
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {
		//엔터키를 누르면, area에 입력 데이터를 반영하자~~~~ (누적시키자~~~)
		//System.out.println("나 엔터 쳣어?");
		//event.keyCode
		int key = e.getKeyCode();
//		System.out.println(key);
		if(key==10) {
//			System.out.println("엔터 쳤네?");
			String msg = t_input.getText();
			if(!msg.equals("")) {
				area.append(msg+"\n");
				t_input.setText("");				
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	public static void main(String[] args) {
		new ChatClient();
	}
}
