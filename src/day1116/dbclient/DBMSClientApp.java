/*
	DBeaver 수준은 아니어도, 딕셔너리를 학습하기 위해 데이터베이스 접속 클라이언트를 자바로 만들어본다
	실무에서는, SQLPlus를 잘 사용하지 않음.. 이유) 업무효율성이 떨어지기 때문임
	그럼 언제쓰나? 실무현장에서는 개발자의 pc에는 각종 개발툴들이 있지만, 실제적인 운영 서버에는
	보안상 아무것도 설치해서는 안된다. 따라서, 서버에는 툴들이 없기때문에 만일 오라클을 유지보수하러 파견을 나간 경우,
	콘솔창 기반으로 쿼리를 다뤄야할 경우가 종종있다.. 이떄 SQLPlus를 써야함!
	
	개발자들이 개발할때 데이터베이스를 다루는 툴을 "데이버테이스 접속 클라이언트"라고 부른다!
	이러한 툴들 중 꽤 유명한 제품은 Toad, 등이 있다..
	Toad는 DBeaver에 비해 기능이 막강하지만, 유료이기에 우리는 DBeaver 사용
*/
package day1116.dbclient;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DBMSClientApp extends JFrame {
	JPanel p_west; // 서쪽 영역 패널
	Choice ch_users; // 유저명이 출력될 초이스 컴포넌트
	JPasswordField t_pass;// 비밀번호 텍스트 필드
	JButton bt_login; // 접속버튼

	JPanel p_center; // 그리드가 적용될 패널
	JTable t_tables;// 유저의 테이블 정보를 출력할 JTable
	JTable t_seq;// 유저의 시퀀스 정보를 출력할 JTable
	JScrollPane scroll1, scroll2;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "system";
	String password = "1234";
	Connection con;

	//테이블을 출력할 벡터 및 컬럼
	Vector tableList = new Vector(); //이 벡터 안에는 추후 또다른 벡터가 들어갈 예정-> 2차원배열과 비슷
												//단, 벡터는 공간의 제약이 없으므로 코딩 시 편리
	Vector<String> columnList = new Vector<String>();
	
	
	public DBMSClientApp() {
		// 생성
		p_west = new JPanel();
		ch_users = new Choice();
		t_pass = new JPasswordField();
		bt_login = new JButton("접속");

		p_center = new JPanel();
		p_center.setLayout(new GridLayout(2, 1));
		t_seq = new JTable();
		scroll1 = new JScrollPane(t_tables);
		scroll2 = new JScrollPane(t_seq);
		//컬럼정보 초기화 하기
		columnList.add("table_name");
		columnList.add("tablespace_name");
		t_tables = new JTable(tableList, columnList);

		// 스타일
		p_west.setPreferredSize(new Dimension(150, 350));
		ch_users.setPreferredSize(new Dimension(145, 30));
		t_pass.setPreferredSize(new Dimension(145, 30));
		bt_login.setPreferredSize(new Dimension(145, 30));

		// 조립
		p_west.add(ch_users);
		p_west.add(t_pass);
		p_west.add(bt_login);
		p_center.add(scroll1);
		p_center.add(scroll2);
		add(p_west, BorderLayout.WEST);
		add(p_center);

		setSize(700, 350);
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
		connect();
		getUserList();
		
	
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
				System.out.println(rs.getString("table_name"));
				tableList.add(vec); //멤버변수 벡터에 벡터를 담았으니, 이제 멤버변수 벡터가 이차원 벡터가 됨
			}
			t_tables.updateUI();
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
		new DBMSClientApp();
	}

}
