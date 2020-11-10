package day1110.network.unicasting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UniServer extends JFrame{
	JTextField t_port;
	JButton bt;
	JPanel p_north;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	int port=7777;
	
	Thread thread; //메인 쓰레드 대신, 접속자를 감지하게 될 쓰레드!!(accept() 메서드 때문에..)
	
	public UniServer() {
		t_port=new JTextField(Integer.toString(port), 10);
		bt=new JButton("서버가동");
		p_north=new JPanel();
		area=new JTextArea();
		scroll=new JScrollPane(area);
		
		//조립
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		//가동버튼과 리스너 연결
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thread = new Thread() {
					@Override
					public void run() {
						startServer();
					}
				};
				thread.start();
			}		
		});
		setVisible(true);
		setBounds(600, 200, 300, 400); //x, y, width, height
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//실행부 -> 메인쓰레드 : 자바는 쓰레드 기반
	//메인스레드는 프로그램을 운영해주는 사명을 갖고 태어난다!(그래픽 ,이벤트,....)
	//메인스레드에게 루프/대기와 같은 상태를 맡기면 안된다!!!
	
	public void startServer() {
		String msg=null;
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText())); //서버 소켓 생성
			area.append("[서버 준비]\n");
			while(true) {			
				
				Socket socket=server.accept(); //접속자 감지와 동시에 대화용 소켓 반환!
				area.append("[접속자발견]\n");
				//쓰레드~
				//대화용 쓰레드를 생성하고 소켓을 넘겨주자!!
				MessageThread thread = new MessageThread(socket, this);
				thread.start();//Runnable로 진입!!
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		new UniServer();
	}
}
