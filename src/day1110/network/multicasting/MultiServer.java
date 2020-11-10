package day1110.network.multicasting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiServer extends JFrame {
	JTextField t_port;
	JButton bt;
	JPanel p_north;
	JTextArea area;
	JScrollPane scroll;

	ServerSocket server;
	int port = 7777;

	Thread thread; // 메인 쓰레드 대신, 접속자를 감지하게 될 쓰레드!!(accept() 메서드 때문에..)

	// 자바의 컬렉션 프레임웍 중 순서있는 데이터를 처리해주는 객체
	Vector<MessageThread> clientList = new Vector<MessageThread>();

	public MultiServer() {
		t_port = new JTextField(Integer.toString(port), 10);
		bt = new JButton("서버가동");
		p_north = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);

		// 조립
		p_north.add(t_port);
		p_north.add(bt);

		add(p_north, BorderLayout.NORTH);
		add(scroll);

		// 가동버튼과 리스너 연결
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
		setBounds(600, 200, 300, 400); // x, y, width, height
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// 실행부 -> 메인쓰레드 : 자바는 쓰레드 기반
	// 메인스레드는 프로그램을 운영해주는 사명을 갖고 태어난다!(그래픽 ,이벤트,....)
	// 메인스레드에게 루프/대기와 같은 상태를 맡기면 안된다!!!

	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText())); // 서버 소켓 생성
			area.append("[서버 준비]\n");
			while (true) {

				Socket socket = server.accept(); // 접속자 감지와 동시에 대화용 소켓 반환!
				area.append("[접속자발견]\n");
				// 다수 접속자 수 정보를 어딘가에 저장해놓자!!
				// 쓰레드~
				// 대화용 쓰레드를 생성하고 소켓을 넘겨주자!!
				MessageThread thread = new MessageThread(socket, this);
				thread.start();// Runnable로 진입!!
				clientList.add(thread); // 지금 접속한 클라이언트와 쌍을 이루는 서버측 대화쓰레드를
				// 벡터에 담아둔다, 총 몇명이 들어왔고 누가 들어왔는지를 누적할 수 있게 된다...
				area.append("현재까지 접속자는 " + clientList.size() + "\n");
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	


	public static void main(String[] args) {
		new MultiServer();
	}
}
