package day1030.chat;

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

public class ChatC extends JFrame implements KeyListener ,ActionListener{
	JTextArea area;
	private JScrollPane scroll;
	private JPanel p_south;
	private JTextField t_input;
	private JButton bt;
	private JButton bt_open; //대화 상대방을 띄우는 버튼
	private ChatA chatA;
	private ChatB chatB;
	
	public ChatC() {
		//생성
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(10);
		bt = new JButton("Send");
		bt_open = new JButton("Open");
		
		//패널 조립 (패널은 디폴트가 FlowLayout)
		p_south.add(t_input);		
		p_south.add(bt);
		p_south.add(bt_open);
		
		add(p_south, BorderLayout.SOUTH);//남쪽에 패널부착
		add(scroll);
		
		//스타일 적용
		area.setBackground(Color.YELLOW);
		t_input.setBackground(Color.blue);
		t_input.setForeground(Color.white);
		bt.setBackground(Color.black);
		bt.setForeground(Color.white);
		
		t_input.setPreferredSize(new Dimension(200, 30));
		area.setEditable(false);
		//보여주기 전에 미리 연결해놓자(컴포넌트와 리스너 연결)
		t_input.addKeyListener(this);//프레임이 곧 리스너다!!
		//send 버튼에 리스너연결
	 	bt.addActionListener(this);
		//open 버튼에 리스너연결
		bt_open.addActionListener(this);
		
		setTitle("내 채팅");
//		setLocation(800, 400);
//		setSize(300, 400);
		setBounds(500, 550, 300, 400);
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
			send();			
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		//주소값을 비교
		if(obj == bt) {
//			System.out.println("send 버튼 눌렀니");
			send();
		}else if(obj.equals(bt_open)) {
//			System.out.println("open 버튼 눌렀니");			
			open();
		}
	}
	//메세지 창에 대화내용 누적하기!
	public void send() {
		//나에 대한 채팅 처리..
		String msg = t_input.getText();
		if(!msg.equals("")) {
			area.append(msg+"\n");
			chatA.area.append(msg+"\n");
			chatB.area.append(msg+"\n");
			t_input.setText("");	
		}
		
		//너에 대한 채팅처리..
//		msg = ch2.t_input.getText();
//		if(!msg.equals("")) {
//			ch2.area.append(msg+"\n");
//			ch2.t_input.setText("");	
//		}
	}
	//대화할 상대방 윈도우 띄우기!!
	public void open() {

	}
	
	public void setChatA(ChatA chatA) {
		this.chatA = chatA;
	}
	public void setChatB(ChatB chatB) {
		this.chatB = chatB;
	}

}
