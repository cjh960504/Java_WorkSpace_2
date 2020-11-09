package commom.file;

public class FileManager {
	//파일명 구하기 : 매개변수로 파일의 경로를 넘겨받아 파일명만 추출한다.
	public static String getFileName(String path) {
		int lastIndex=path.lastIndexOf("/"); //마지막에 위치한 /의 인덱스 구하기
		return path.substring(lastIndex+1, path.length());
	}
	//확장자 구하기
	public static String getExtend(String fileName) {
		String[] str = fileName.split("\\."); // 점을 기준으로 문자열 분리.. 분리 후에는 배열이 반환됨!
		return str[1];					//.은 특수문자라 escaping필요(\\)
	}
	
//	public static void main(String[] args) {
//		String fileName = getFileName("https://img.theqoo.net/img/Aeqlc.jpg");
//		System.out.println(fileName);
//		System.out.println(getExtend(fileName));
//		
//	}
}
