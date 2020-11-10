package day1110.network.echo;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame{
	JPanel p_north;
	Choice ch_ip;
	JTextField t_port;
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt_send;
	Socket socket;
	Thread thread;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public EchoClient() {
	  ch_ip = new Choice();
	  t_port = new JTextField("7777",5);
	  bt_connect= new JButton("접속");
	  area = new JTextArea();
	  scroll=new JScrollPane(area);
	  t_input = new JTextField(15);
	  bt_send = new JButton("전송");
	  p_north=new JPanel();
	  p_south=new JPanel();
	  
	  ch_ip.add("localhost");
	  p_north.add(ch_ip);
	  p_north.add(t_port);
	  p_north.add(bt_connect);
	  
	  p_south.add(t_input);
	  p_south.add(bt_send);
	  
	  add(p_north, BorderLayout.NORTH);
	  add(scroll);
	  add(p_south, BorderLayout.SOUTH);
	  
	  //접속버튼과 리스너연결
	  bt_connect.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			thread=new Thread() {
				@Override
				public void run() {
					connect();
				}
			};
			thread.start();
//			connect()
		}
	});
	  
	  //접속버튼과 리스너연결
	  bt_send.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			send();
		}
	});
	  
	  setBounds(300,200,300, 400);
	  setVisible(true);
//	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//에코 서버에 접속한다!
	public void connect() {
		try {
			socket = new Socket(ch_ip.getSelectedItem(), Integer.parseInt(t_port.getText()));
			area.append("[서버에 접속]\n");
			
			//접속이 성공하면 스트림 생성
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버에 메시지 보내기(출력)
	public void send() {
		String str=t_input.getText();//유저가 입력한 텍스트박스 메시지
		try {
			buffw.write(str+"\n");
			buffw.flush(); //버퍼에 남아있는 데이터없게, 버퍼비우기
			listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버로부터 메시지 받기(입력)
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine();
			area.append(msg+"\n");//대화기록하기
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new EchoClient();
	}
}
