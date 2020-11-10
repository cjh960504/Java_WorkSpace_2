package day1110.network.unicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//이 쓰레드는, 대화용 쓰레드이므로 입출력 스트림을 각각의 인스턴스가 보유해야한다!!
//아래의 클래스를 쓰레드로 선언하는 순간부터, 이 인스턴스들은 서로 비동기적(Asynchronous)으로 동작할 수 있다!!!
public class MessageThread extends Thread {
	BufferedReader buffr;
	BufferedWriter buffw;
	Socket socket; // 각각의 스레드는 대화용 소켓을 보유해야 스트림을 뽑을 수 있으므로, 접속자가 감지되면
	// 해당 소켓을 인수로 넘겨받자
	UniServer server;

	public MessageThread(Socket socket, UniServer server) {
		this.socket = socket;
		this.server = server;
		try {
			buffr = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		listen(); //끝나지 않는 무한루프, 메서드안에 무한루프가 존재, 메서드안에 send()도 있어서 듣고 말하기 반복가능
	}
	// 메세지 받기(청취)
	public void listen() {
		String msg = null;
		try {
			while (true) {
				msg = buffr.readLine();
				server.area.append(msg + "\n");

				send(msg);// 클라이언트에게 다시 보내야 한다 (서버의 의무)
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 클라이언트에게 메시지 보내기
	public void send(String msg) {
		try {
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
