package day1110.network.echo;

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

public class EchoServer extends JFrame{
	JTextField t_port;
	JButton bt;
	JPanel p_north;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	int port=7777;
	
	Thread thread; //메인 쓰레드 대신, 접속자를 감지하게 될 쓰레드!!(accept() 메서드 때문에..)
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public EchoServer() {
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
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText())); //서버 소켓 생성
			area.append("[서버 준비]\n");
			
			//서버 가동하기
			//자바는 쓰레드기반이므로, 지금까지 메인실행부라 불렸던 실행주체도 사실 시스템에 의해 생성된 스레드였다!!
			//하지만, 메인쓰레드는 개발자가 생성하는 일반쓰레드와는 하는 역할에 차이가 있다..
			//메인쓰레드는 프로그램을 운영해주는 역할 특히, 그래픽처리/이벤트처리까지 담당!!하므로!!!!!
			//절대로 1)무한루프에 빠뜨리지 말 것!!! 2) 대기상태에 빠지게 해선 안된다!!!!(accept(), read())
			//참고로 안드로이드에서는 메인 쓰레드가 위 사항 시도자체를 에러로 본다!!!
			//결론) 별도의 쓰레드를 생성하여 처리해줘야한다!!!!!!!!
			Socket socket=server.accept(); //접속자 감지와 동시에 대화용 소켓 반환!
			area.append("[접속자발견]\n");
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			listen();//듣기시작

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//메세지 받기(청취)
	public void listen() {
		String msg=null;
		try {
			while(true) {
				msg = buffr.readLine();
				area.append(msg+"\n");
				
				send(msg);//클라이언트에게 다시 보내야 한다 (서버의 의무)
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//클라이언트에게 메시지 보내기
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}
