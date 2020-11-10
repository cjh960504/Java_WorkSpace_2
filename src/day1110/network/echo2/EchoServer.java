package day1110.network.echo2;

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

public class EchoServer extends JFrame {
	ServerSocket server;
	JPanel p_north;
	JTextArea area;
	JTextField t_port;
	JButton bt_open;
	JScrollPane scroll;
	BufferedReader reader;
	BufferedWriter writer;
	int port = 8484;
	Thread serverThread ;

	public EchoServer() {
		p_north = new JPanel();
		t_port = new JTextField(Integer.toString(port), 10);
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt_open = new JButton("오픈");
		serverThread = new Thread() {
			@Override
			public void run() {
				startServer();
			}
		};
		p_north.add(t_port);
		p_north.add(bt_open);
		add(p_north, BorderLayout.NORTH);
		add(scroll);

		bt_open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 serverThread = new Thread() {
					@Override
					public void run() {
						startServer();
					}
				};
				serverThread.start();
			}
		});

		setBounds(600, 300, 300, 400);
		setVisible(true);
	}

	public void startServer() {
		try {
			server = new ServerSocket(port);
			area.append("[서버 준비]\n");
			Socket socket = server.accept();
			area.append("접속자 발견\n");
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void listen() {
		String msg = null;
		try {
			while(true) {
				msg = reader.readLine();
				area.append(msg + "\n");
				send(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void send(String msg) {
		try {
			writer.write(msg+"\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new EchoServer();
	}
}
