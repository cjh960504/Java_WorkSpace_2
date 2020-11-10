/*
	채팅 메시지를 보내지 않더라도, 채팅에 참여하는 모든 사람이 보낸 메시지를 수신하려면 
	무한루프로 실행되면서 스트림을 읽을 쓰레드가 필요하다!
*/
package day1110.network.multicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	BufferedReader buffr;
	BufferedWriter buffw;
	Socket socket;
	MultiClient client;
	boolean flag=true;
	
	public ClientThread(Socket socket, MultiClient client) {
		// 접속이 성공하면 스트림 생성
		this.socket = socket;
		this.client = client;
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		listen();
	}
	
	// 서버로부터 메시지 받기(입력)
	public void listen() {
		String msg = null;
		try {
			while (true) {
				msg = buffr.readLine();
	
				client.area.append(msg + "\n");// 대화기록하기
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 서버에 메시지 보내기(출력)
	public void send(String msg) {
		try {
			buffw.write(msg + "\n");
			buffw.flush(); // 버퍼에 남아있는 데이터없게, 버퍼비우기

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
