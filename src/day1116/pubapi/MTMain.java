package day1116.pubapi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MTMain extends JFrame {
	JPanel p_west, p_east;
	JTextField t_name;
	JTextField t_op1;
	JTextField t_op2;
	JTextField t_op3;
	JButton bt;
	JTable table;
	MountainModel model;
	JScrollPane scroll;

	/*
	 * JTable에서 데이터 연동 시 지금까지는 이차원배열만 써왔는데, 사실 이차원 배열은 생성 시 크기를 정해야하기 떄문에 불편한 점이 많다.
	 * (불편했던 예: rs.last() 후 rs.getRow()로 데이터 총 수 구하고, 다시 커서를 원상복귀 시킴;;) 따라서 컬렉션
	 * 프레임웍중 Vector를 지원하는 생성자를 이용해보자!
	 */
	// 이제 이차원 배열이 아닌, Vector를 선언해서, 데이터와 컬럼명을 처리!!
	// 벡터는 컬렉션 프레임웍이니 배열처럼 생성 시 크기를 명시하지 않아도 됨!!
	Vector data = new Vector();

	// 컬럼 정보를 담는 벡터
	Vector<String> columnName = new Vector<String>();

	public MTMain() {
		init(); // 데이터 채우기!
		
		p_west = new JPanel();
		p_east = new JPanel();
		t_name = new JTextField(15);
		t_op1 = new JTextField(15);
		t_op2 = new JTextField(15);
		t_op3 = new JTextField(15);
		bt = new JButton("검색");
		table = new JTable(data, columnName);//매개변수로 벡터 넣기
		scroll = new JScrollPane(table);

		p_west.setPreferredSize(new Dimension(200, 600));
		scroll.setPreferredSize(new Dimension(650, 530));
		p_west.add(t_name);
		p_west.add(t_op1);
		p_west.add(t_op2);
		p_west.add(t_op3);
		p_west.add(bt);
		p_east.add(scroll);

		add(p_west, BorderLayout.WEST);
		add(p_east, BorderLayout.EAST);

		setSize(900, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	//벡터값을 초기화 
	public void init() {
		//데이터 1건 넣어보기
		Vector v= new Vector();
		v.add("1");
		v.add("설악산");
		v.add("강원도");
		v.add("3000");
		data.add(v); //벡터안에 벡터를 넣게 되는 것이라서 이차원배열과 다를게 없다!!
		
		//컬럼정보 채우고 
		columnName.add("ID"); //산 아이디
		columnName.add("산 이름"); //산 이름
		columnName.add("소재지"); //산 소재지
		columnName.add("높이"); //산 높이
	}
	
	public static void main(String[] args) {
		new MTMain();
	}
}
