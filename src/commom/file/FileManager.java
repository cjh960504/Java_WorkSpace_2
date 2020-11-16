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
	
	//확장자 구하기 업데이트 : 마지막 점을 기준으로 가져와야 문제가 없다..
	public static String getExtend2(String filename) {
		//마지막 점의 위치 구하기!! lastIndexOf 메서드 이용
		int lastIndex = filename.lastIndexOf(".");
		
		//마지막 '.' 다음 문자부터 가져와야하므로 +1을 더한다
		return filename.substring(lastIndex+1, filename.length());
	}
//	
//	//단위테스트를 위해 메인메서드 풀어놓자!!!
//	public static void main(String[] args) {
//		String filename = getFileName("https://images-na.ssl-images-amazon.com/images/I/51xEeVYLqrL._SX342_.jpg");
//		System.out.println(getFileName(filename));
//		System.out.println(getExtend2(filename));
//	}
}
