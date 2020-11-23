/*
	day1116일차에 구현했던 데이터베이스 클라이언트 프로그램에서 JTable 생성자의 Vector방식을 이용하면 
	동적인 테이블 선택 시 유지보수성이 거의 불가능한 수준이므로, 이를 개선해본다.
	즉, 유저가 어떤 테이블을 선택할지 모르기 때문에, 선택한 테이블의 컬럼 수, 구성등을 에측할 수 없는 상황에\
	대처해본다!
*/
package day1117.dbclient;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class DBMSClientApp2 extends JFrame {
	JPanel p_west; // 서쪽 영역 패널
	Choice ch_users; // 유저명이 출력될 초이스 컴포넌트
	JPasswordField t_pass;// 비밀번호 텍스트 필드
	JButton bt_login; // 접속버튼

	JPanel p_center; // 그리드가 적용될 패널
	JPanel p_upper;//테이블과 시퀀스를 포함할 패널(그리드 레이아웃 예정)
	JPanel p_middle;//SQL 편집기가 위치할 미들패널(FlowLayout)
	JPanel p_south;
	JTable t_tables;// 유저의 테이블 정보를 출력할 JTable
	JTable t_seq;// 유저의 시퀀스 정보를 출력할 JTable
	JTable t_record; //유저가 선택한 테이블의 레코드를 출력할 JTable
	JTable t_column;
	JScrollPane scroll1, scroll2, scroll3, scroll4, scroll5;
	JTextArea area;
	JButton bt_execute;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "system";
	String password = "1234";
	Connection con;

	//테이블을 출력할 벡터 및 컬럼
	Vector tableList = new Vector(); //이 벡터 안에는 추후 또다른 벡터가 들어갈 예정-> 2차원배열과 비슷
												//단, 벡터는 공간의 제약이 없으므로 코딩 시 편리
	Vector<String> tableColumn = new Vector<String>();
	
	//시퀀스를 출력할 벡터 및 컬럼
	Vector seqList = new Vector(); //2차원 벡터의 층을 담당, 반복문돌때 층의 벡터들을 add할 예정
	Vector<String> seqColumn = new Vector<String>();
	
	//TableModel 보유
	MyTableModel model;
	MyTableModel columnModel;
	
	//컬럼정보를 출력할 벡터 및 컬럼
	Vector columnList = new Vector();
	Vector<String> columnCol = new Vector<String>();
	
	public DBMSClientApp2() {
		// 생성
		p_west = new JPanel();
		ch_users = new Choice();
		t_pass = new JPasswordField();
		bt_login = new JButton("접속");

		p_center = new JPanel();
		p_upper = new JPanel();
		p_south = new JPanel();
		p_middle = new JPanel(new BorderLayout());
		area = new JTextArea();
		bt_execute = new JButton("SQL문 실행");
		p_center.setLayout(new GridLayout(3, 1)); //2층에 1호수
		p_upper.setLayout(new GridLayout(1, 2));//1층에 2호수
		p_south.setLayout(new GridLayout(1, 2));
		
		//테이블의 컬럼정보 초기화 하기
		tableColumn.add("table_name");
		tableColumn.add("tablespace_name");
		t_tables = new JTable(tableList, tableColumn);
		
		//시퀀스의 컬럼정보 초기화 하기
		seqColumn.add("sequence_name");
		seqColumn.add("last_number");
		t_seq = new JTable(seqList, seqColumn);
		
		//선택된 테이블의 레코드 보여줄 테이블
		t_record = new JTable(null);
		
		//선택된 테이블의 컬럼정보 보여줄 테이블
		columnCol.add("column_name");
		columnCol.add("column_type");
		t_column= new JTable(columnList, columnCol);
		
		scroll1 = new JScrollPane(t_tables);
		scroll2 = new JScrollPane(t_seq);
		scroll3 = new JScrollPane(area);
		scroll4 = new JScrollPane(t_record);
		scroll5 = new JScrollPane(t_column);
		
		// 스타일
		p_west.setPreferredSize(new Dimension(150, 350));
		ch_users.setPreferredSize(new Dimension(145, 30));
		t_pass.setPreferredSize(new Dimension(145, 30));
		bt_login.setPreferredSize(new Dimension(145, 30));
		area.setFont(new Font("Arial Black", Font.BOLD, 20));
		
		// 조립
		p_west.add(ch_users);
		p_west.add(t_pass);
		p_west.add(bt_login);
		p_upper.add(scroll1);
		p_upper.add(scroll2);
		p_middle.add(scroll3);
		p_middle.add(bt_execute, BorderLayout.SOUTH);		
		p_south.add(scroll4);
		p_south.add(scroll5);
		p_center.add(p_upper);//그리드의 1층
		p_center.add(p_middle);//그리드의 2층
		p_center.add(p_south);//그리드의 3층
		
		
		add(p_west, BorderLayout.WEST);
		add(p_center);

		setSize(900, 750);
		setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE); //오라클, 스트림 닫는 처리를 하고 꺼야댐
		setLocationRelativeTo(null);
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disConnect(); // 시스템 종료 전 닫을 자원이 있을 때 먼저 닫을려고 호출!
				System.exit(0);
			}
		});
		bt_login.addActionListener((e)->{
			login();
		});
		t_tables.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				//선택한 좌표의 테이블명 얻기!!
				int row = t_tables.getSelectedRow();//선택한 row 구하기
				int col = t_tables.getSelectedColumn();//선택한 column 구하기
//				System.out.println(t_tables.getValueAt(row, col));
				
				String tableName = (String)t_tables.getValueAt(row, col);
				tableName = tableName.toLowerCase();
				select(tableName);
//				getColumnList(tableName);
				t_record.updateUI();
				t_column.updateUI();
//				System.out.println(t_record.getColumnCount());
//				System.out.println(t_record.getRowCount());
			}
		});
		
		bt_execute.addActionListener((e)->{
			select(null);
		});
		connect();
		getUserList();
		
	
	}
	//유저가 선택한 테이블의 정보가져오기
	//피곤하게 살지않기위해 meta데이터 매개변수
	public void getColumnType(ResultSetMetaData meta) {
		try {
			//멤버변수로 선언된 벡터의 데이터 누적을 피하기 위해
			//for문 시작전 싹~ 비우자
			columnList.removeAll(columnList);
			int total= meta.getColumnCount(); //총 컬럼 수
			for(int i=1;i<=total;i++) {
//				System.out.println("컬럼명 " + meta.getColumnName(i) + "("+meta.getColumnTypeName(i)+")");
				Vector vec = new Vector();
				vec.add(meta.getColumnName(i));
				vec.add(meta.getColumnTypeName(i));
				
				columnList.add(vec);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	//컬럼 정보 가져오기
//	public void getColumnList(String tableName) {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "select * from "+ tableName;
//		Vector<String> column = new Vector<String>();
//		try {
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			ResultSetMetaData meta= rs.getMetaData();
////			for(int i=1;i<=meta.getColumnCount();i++) {
//////				System.out.println(meta.getColumnName(i));
////				column.add(meta.getColumnTypeName(i));
////			}
//			column.add("column_name");
//			column.add("column_type");
//			
//			Vector record = new Vector();
////			while(rs.next()) {
////			}
//			for(int i=1;i<=meta.getColumnCount();i++) {
//				Vector vec = new Vector();
//				vec.add(meta.getColumnName(i));		
//				vec.add(meta.getColumnTypeName(i));		
//				record.add(vec); 
//			}
//			columnModel = new MyTableModel(record, column);
//			t_column.setModel(columnModel);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
	//시퀀스 정보 가져오기
	public void getSeqList() {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql = "select sequence_name,last_number from user_sequences";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector vec = new Vector();
				vec.add(rs.getString("sequence_name"));
				vec.add(rs.getString("last_number"));
				seqList.add(vec);//기존의 시퀀스 벡터에 추가해서 이차원벡터로 만들자!
			}
			t_seq.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//현재 접속 유저의 테이블 목록 가져오기
	public void getTableList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select table_name, tablespace_name from user_tables";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs  = pstmt.executeQuery();
			while(rs.next()) {
				Vector vec = new Vector(); //tableList벡터에 들어갈 벡터
				vec.add(rs.getString("table_name"));
				vec.add(rs.getString("tablespace_name"));
//				System.out.println(rs.getString("table_name"));
				tableList.add(vec); //멤버변수 벡터에 벡터를 담았으니, 이제 멤버변수 벡터가 이차원 벡터가 됨
			}
			t_tables.updateUI();
//			System.out.println(t_tables.getColumnCount());
//			System.out.println(t_tables.getRowCount());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//유저가 선택한 테이블의 레코드 가져오기
	//이 메서드를 호출하는 자는 select 문의 매개변수로 테이블명을 넘겨야한다!!
	public void select(String tableName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=null;
		
		if(tableName!=null) {
			sql="select * from "+tableName;			
		}else {
			sql=area.getText();
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//----------------------------------------------------------
			//컬럼 정보 만들기 위한 코드
			//동적으로 MyTableModel이 보유한 컬럼정보 벡터에 정보를 채워넣자
			//----------------------------------------------------------
			Vector<String> column = new Vector<String>();
			java.sql.ResultSetMetaData meta =rs.getMetaData();
			for(int i=1;i<=meta.getColumnCount();i++) {
//				System.out.println(meta.getColumnName(i));
				column.add(meta.getColumnName(i));
			}

			Vector record = new Vector();
			while(rs.next()) {
				Vector vec = new Vector();//비어있는 일차원 벡터(여기에 레코드 1건이 담겨질 예정)
				
				//rs도 일정의 배열이므로, index로 접근가능, 1부터 시작
				//문제점) 1부터 몇까지 컬럼이 존재하는지 알수가 없다!
				//알수 있는 방법은!!? 
				//- 테이블에 대한 메타정보를 가져오면 된다!!!!
				for(int i=1;i<=meta.getColumnCount();i++) {
					vec.add(rs.getString(i));		
//					System.out.println(rs.getString(i));
				}
				record.add(vec);
			}
			
			//데이터를 담은 이차원 벡터와 컬럼을 담은 일차원 벡터를 새로운 모델객체에 생성하면서 전달하자!
			model = new MyTableModel(record, column);
			
			//테이블에 모델을 생성자가 아닌, 메서드로 적용하자!!
			t_record.setModel(model);
			
			getColumnType(meta);
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally{
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//선택한 유저로 로그인시도하기!!
	public void login() {
		//현재 유지되고 있는 접속 객체인 Connection을 끊고, 다른 유저로 접속을 시도한다!!
		if(con!=null) {
			disConnect();//접속끊기
		}
			user = ch_users.getSelectedItem();
			password = new String(t_pass.getPassword());
			connect(); // 접속하기 (초이스값으로)			
			getTableList();
			getSeqList();
		
	}
	
	//유저목록 가져오기
	public void getUserList() {
		//pstmt와 result는 소모품이므로 매 쿼리문마다 1개씩 대응
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = "select username from dba_users";
		
		try {
			pstmt = con.prepareStatement(sql); //쿼리문 준비
			rs = pstmt.executeQuery(); //반환형을 먼저 적으면 이클립스가 알맞는 메서드 찾아줌!
			while(rs.next()) {//rs에 들어있는 유저정보를 Choice에 출력
				String username = rs.getString("username");
				ch_users.add(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 오라클에 접속하기
	public void connect() {
		
		try {
			Class.forName(driver); // 드라이버 로드
			con = DriverManager.getConnection(url, user, password);
			if (con==null) {
				JOptionPane.showMessageDialog(this, user+"계정의 접속을 실패하였습니다.");
			} else {
				this.setTitle(user+" 계정으로 접속 중...");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 오라클과의 접속끊기
	public void disConnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new DBMSClientApp2();
	}

}
