/*
	Swing 컴포넌트 중, 이차원 구조의 데이터(표)를 표현하기에 최적화된 JTable을 사용해보자!
	
*/
package day1105.db;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableApp extends JFrame{
	JTable table;
	String[] columnName = {"번호", "이름", "연락처", "주소", "성별"};
	String[][] rowData = {
			{"1", "Batman", "011", "고담시", "남"},
			{"2", "Superman", "017", "서울시", "남"},
			{"3", "Wonder", "019", "청주시", "여"}
			};
	JScrollPane scroll;
	public TableApp() {
		table=new JTable(rowData, columnName);//row , col 
		scroll=new JScrollPane(table);
		setLayout(new FlowLayout());
		add(scroll);//CENTER
		
		//마우스 이벤트 부여
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int selectedRow = table.getSelectedRow();//유저가 선택한 row (층수)
				int selectedCol = table.getSelectedColumn(); //유저가 선택한 index (호수)
			
				//좌표를 이용해 좌표의 값을 얻어온다 (문자열뿐만아니라 어떠한 데이터가 들어가도 가능하게 Object로 반환)
				String selectedValue = table.getValueAt(selectedRow, selectedCol).toString(); 
				System.out.println(selectedRow+"행 "+selectedCol+"열 선택 : " + selectedValue);
			}
		});
		setSize(500, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new TableApp();
	}
}
