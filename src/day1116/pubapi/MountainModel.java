package day1116.pubapi;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MountainModel extends AbstractTableModel {
	// 이제 이차원 배열이 아닌, Vector를 선언해서, 데이터와 컬럼명을 처리!!
	// 벡터는 컬렉션 프레임웍이니 배열처럼 생성 시 크기를 명시하지 않아도 됨!!
	Vector<Mountain> data=new Vector<Mountain>();
	 
	//	컬럼 정보를 담는 벡터
	Vector<String> columnName = new Vector<String>();
	
	//컬럼 제목을 구성할 벡터 요소는 생성자에서 채웁시다!!
	public MountainModel() {
		//컬럼 구성
		columnName.add("ID"); //산 아이디
		columnName.add("산 이름"); //산 이름
		columnName.add("소재지"); //산 소재지
		columnName.add("높이"); //산 높이
		
		//데이터 예시 구성(테스트용)
		Mountain mt = new Mountain();
		mt.setMntnid(14);
		mt.setMntnnm("속리산");
		mt.setMntninfopoflc("충청도");
		mt.setMntninfohght(4000);
		data.add(mt); // 산 1개를 벡터에 넣기
	}
	
	//레코드의 수(행의 수)는 벡터의 길이에서 가져오면 됨
	public int getColumnCount() {
		return columnName.size();
	}

	//이제 컬럼의 수는 벡터의 길이에서 가져오면 됨
	public int getRowCount() {
		return data.size();
	}
	
	//컬럼 제목을 출력하기 위한, 메서드 오버라이드
	public String getColumnName(int col) {
		return columnName.get(col);
	}
	
	//벡터에서 데이터는 VO이므로 [row]에 대한 접근은 가능하지만 [col]에 대한 접근은 불가
	public Object getValueAt(int row, int col) {
		//해결책 ) 조건문 사용
		//즉 col이 0일떄는 산 아이디, 1일때는 이름... 등등 우리가 조건 달기
		System.out.println("row 값= "+ row +" col 값 = "+ col);
		
		Mountain mt = data.get(row);
		String obj = null;
		
		if(col==0) { //아이디
			obj = Integer.toString(mt.getMntnid());
		}else if(col==1) {//산이름
			obj = mt.getMntnnm();
		}else if(col==2) {//산소재지
			obj = mt.getMntninfopoflc();
		}else if(col==3) {//높이
			obj = Integer.toString(mt.getMntninfohght());
		}
		return obj;
	}
	
}
