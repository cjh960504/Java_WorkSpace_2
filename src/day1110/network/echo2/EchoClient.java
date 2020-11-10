package day1110.network.echo2;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame {
	Choice ch_ip;
	JTextField t_port, t_input;
	JButton bt_connect, bt_send;
	JTextArea area;
	JPanel p_north, p_south;
	Socket socket;
	BufferedReader reader;
	BufferedWriter writer;
	Thread clientThread;
	
	public EchoClient() {
		ch_ip = new Choice();
		t_port = new JTextField("8484", 10);
		bt_connect = new JButton("연결");
		p_north = new JPanel();
		area = new JTextArea();
		t_input = new JTextField(15);
		bt_send = new JButton("전송");
		p_south = new JPanel();
		
		ch_ip.addItem("localhost");
		p_north.add(ch_ip);
		p_north.add(t_port);
		p_north.add(bt_connect);
		p_south.add(t_input);
		p_south.add(bt_send);
		add(p_north, BorderLayout.NORTH);
		add(area);
		add(p_south, BorderLayout.SOUTH);

		bt_connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientThread = new Thread() {
					public void run() {
						connect();
					}
				};
				clientThread.start();
			}
		});
		
		bt_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				send();
	
			}
		});
		setBounds(300, 300, 300, 400);
		setVisible(true);
	}

	public void connect() {
		try {
			socket = new Socket(ch_ip.getSelectedItem(), Integer.parseInt(t_port.getText())); //대기, 무한루프는 스레드로 뺴자!!
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			area.append("접속 성공");

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listen() {
		String msg = null;
		try {
			msg = reader.readLine();
			area.append(msg + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send() {
		try {
			writer.write(t_input.getText());
			writer.flush();
			listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new EchoClient();
	}
}
