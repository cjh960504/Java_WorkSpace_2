/*
	최종 목표는 채팅(멀티캐스팅 구현)이지만, 일단 기초인 Echo System을 먼저 학습!!
*/
package day1109.echo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	ServerSocket server; //대화용 소켓이 아닌, 접속자 감지용 서버측 소켓!!
	int port=9999; //port 1~1024번까지는 이미 시스템이 점유하므로, 점유불가
	public EchoServer() {
		//서버소켓을 이용하자, 접속자 받아보자!!
		try {
			server = new ServerSocket(port);//서버소켓 생성
			System.out.println("서버 소켓 생성완료!");
			
			Socket socket =server.accept(); //접속자가 발견될 때까지, Block상태로 기다림..
			System.out.println("접속자 발견");
			
			//반환받은 소켓을 이용하면, 현재 접속자에 대한 정보를 구할 수 있으면, 특히 ip를 조사해보자
			InetAddress inet = socket.getInetAddress();//인터넷 주소정보를 가진 객체
			String ip = inet.getHostAddress();//ip추출
			System.out.println("접속한 클라이언트의 아이피는 "+ip);
			
			//클라이언트가 보낸 메시지 받기!! 
			//(메시지를 받는 것은 실행중인 프로그램으로 데이터가 들어오는 것이므로
			//입력 스트림으로처리해야한다!!)
			//소켓으로부터 스트림을 뽑아낼 수 있다!
			InputStream inputStream = socket.getInputStream(); //바이트기반(한글깨짐, 영문만)
			int data=1;
			while(data!=10) {
				data = inputStream.read(); //1byte읽어들임...
				System.out.print((char)data);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EchoServer();
	}
}
