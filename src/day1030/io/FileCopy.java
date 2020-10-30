package day1030.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	//String ori = "C:/java_workspace/workspace/res/data/memo.txt";// 원본의 위치
//	String dest = "C:/java_workspace/workspace/res/data/memo_copy.txt";// 복사될 경로의 위치
	String ori = "C:/java_workspace/workspace/res/travel/girl.jpg";// 원본의 위치
	String dest = "C:/java_workspace/workspace/res/data/photo.jpg";// 복사될 경로의 위치

	FileInputStream fis; // 파일을 대상으로한 입력스트리
	FileOutputStream fos; // 파일을 대상으로한 출력스트림

	public FileCopy() {
		// 아래의 코드는 문법상 문제는 없다, 단 실행 시 파일이 없을 경우 에러가 나면서
		// 프로그램이 비정상종료될 수 있는 우려가 있다. sun에서는 우려에 대한 처리를
		// 예외처리로 강제하고 있다...
		try {
			fis = new FileInputStream(ori);
			fos = new FileOutputStream(dest);//파일 출력스트림은, 지정한 경로로 
			System.out.println("스트림 생성 성공!");

			// 스트림 생성이 성공되었으므로 데이터를 1byte씩 읽어서 다른
			// 비어있는 파일에 출력해보자!!
			int data;
			while(true) {
				data = fis.read(); //1byte read
//				System.out.println(data);
				if(data==-1) break; //End Of File
				fos.write(data); //1byte write
				//비어있는 파일을 생성해줌
			}
			System.out.println("복사가 완료되었습니다!");
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다!");// 사용자에게 보영주는
			e.printStackTrace();// 개발자의 에러원인 분석용
		} catch (IOException e) {
			System.out.println("파일을 읽을 수 없습니다!");
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new FileCopy();
	}
}
