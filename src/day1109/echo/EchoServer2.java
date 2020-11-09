package day1109.echo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer2 {
	ServerSocket server;
	int port = 9999;

	public EchoServer2() {
		try {
			server = new ServerSocket(port);
			System.out.println("서버 소켓 생성완료!");
			
			Socket socket = server.accept();
			System.out.println("접속자 발견!");
			
			//반환받은 소켓을 이용하면, 현재 접속자에 대한 정보를 구할 수 있다.
			InetAddress inet = server.getInetAddress();
			String ip = inet.getHostAddress();
			System.out.println(ip);
			
			//클라이언트가 보낸 메시지 받기!! 
			//(메시지를 받는 것은 실행중인 프로그램으로 데이터가 들어오는 것이므로
			//입력 스트림으로처리해야한다!!)
			//소켓으로부터 스트림을 뽑아낼 수 있다!
			InputStream is =socket.getInputStream();//바이트기반(한글깨짐, 영문만)
			
			int data=1;
			while(data!=10) {
				System.out.print((char)data);//1byte읽어들임...
			}
			socket.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new EchoServer2();
	}
}
