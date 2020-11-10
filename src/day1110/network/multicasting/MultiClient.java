package day1110.network.multicasting;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiClient extends JFrame {
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
	ClientThread clientThread;
	
	public MultiClient() {
		ch_ip = new Choice();
		t_port = new JTextField("7777", 5);
		bt_connect = new JButton("접속");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		t_input = new JTextField(15);
		bt_send = new JButton("전송");
		p_north = new JPanel();
		p_south = new JPanel();

		ch_ip.add("localhost");
		p_north.add(ch_ip);
		p_north.add(t_port);
		p_north.add(bt_connect);

		p_south.add(t_input);
		p_south.add(bt_send);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		add(p_south, BorderLayout.SOUTH);

		// 접속버튼과 리스너연결
		bt_connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				connect();
			}
		});

		// 접속버튼과 리스너연결
		bt_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientThread.send(t_input.getText());
				t_input.setText("");
			}
		});

		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					clientThread.send(t_input.getText());
//					listen();
					t_input.setText("");
				}
			}
		});
		
		//현재 프레임과 윈도우 리스너 연결
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//나간다는 의사를 서버에 전송하자!!
				clientThread.send("exit");
				clientThread.flag=false;
				System.exit(0);//클라이언트 프로세스 종료!
			}
		});
		setBounds(300, 200, 300, 400);
		setVisible(true);
//	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// 에코 서버에 접속한다!
	public void connect() {
		try {
			socket = new Socket(ch_ip.getSelectedItem(), Integer.parseInt(t_port.getText()));
			area.append("[서버에 접속]\n");
			
			//무한루프로 메시지를 받고, 메시지를 보내줄 쓰레드 생성
			 clientThread = new ClientThread(socket, this);
			clientThread.start();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	



	public static void main(String[] args) {
		new MultiClient();
	}
}
