package day1105.db;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EmpApp2 extends JFrame {
	JButton bt_connect, bt_load;
	JTextArea area;
	JScrollPane scroll;
	
	//JDBC기술은 DBMS종류에 상관없이 중립적인 코드를 작성할 수 있도록 지원한다...
	//하지만, 드라이버는 DBMS종류에 맞는 드라이버를 가지고 있어야한다!!!!
	String url = "jdbc:mysql://localhost:/db1105";
	String user = "root";
	String password = "1234";
	Connection con; // 접속 후, 그 정보를 가진 객체 따라서, 접속을 끊으려면 이 객체가 필요
	PreparedStatement pstmt; // 쿼리문 수행 객체, 인터페이스이므로 new로 생성하는 것이 아니라
	// 접속객체인 Connection 객체로부터 인스턴스를 얻올 수 있다.
	// why? 접속이 성공되어야 쿼리문을 수행할 수 있으므로, 접속객체에 의존적인것이 당연하다!!!
	ResultSet rs;// select 쿼리문 수행결과에 의해 표가 반환되는데, 이떄 이 표를 담는 객체

	public EmpApp2() {
		bt_load = new JButton("Load");
		bt_connect = new JButton("Connect");
		area = new JTextArea();
		scroll = new JScrollPane(area);

		// 스타일 적용
		area.setPreferredSize(new Dimension(780, 500));

		setLayout(new FlowLayout());

		add(bt_connect);
		add(bt_load);
		add(scroll);

		// 버튼과 리스너 연결
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();// 오라클 접속
			}
		});
		bt_load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();// select로 emp 가져오기
			}
		});

		setSize(800, 600);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE); DB를 닫지않고, 프로세스만 종료해버리니깐..
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(rs!=null) {
					try {
						rs.close();
						System.out.println("rs 연결해제");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(pstmt!=null) {
					try {
						pstmt.close();
						System.out.println("pstmt 연결해제");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(con!=null) {
					try {
						con.close();
						System.out.println("con 연결해제");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				//프로세스 종료
				System.exit(0);
			}
		});
		setLocationRelativeTo(null);
	}

	public void connect() {
		// 오라클 접속시도하기 (1. 드라이버 로드 2. 접속(DriverManager를 이용하여 Connection인스턴스 얻기))
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			area.append("드라이버 로드 성공\n");

			con = DriverManager.getConnection(url, user, password);
			if (con == null) {
				area.append("접속 실패\n");
			} else {
				area.append("접속 성공\n");
				bt_connect.setEnabled(false);
			}

		} catch (ClassNotFoundException e) {
			area.append("드라이버 로드 실패\n");
			e.printStackTrace();
		} catch (SQLException e) {
			area.append("접속 실패\n");
			e.printStackTrace();
		}
	}

	public void load() {
		// select 문을 실행해본다!
		String sql = "select * from emp";
		try {
			pstmt = con.prepareStatement(sql); // Connection객체로부터 PerparedStatement인스턴스얻기
			// pstmt.executeUpdate();//DML- insert, update, delete의 경우에만

			rs = pstmt.executeQuery();// select문의 경우엔 executeQuery() 이용해야한다..
			//rs에는 표가 들어있다!! 따라서 원하는 레코드로 커서를 옮기자!!
			
			area.setText("");
			area.append("EMPNO\tENAME\tJOB\tMGR\tHIREDATE\t\tSAL\tCOMM\tDEPTNO\n");
			area.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			while(rs.next()) { //데이터가 존재하면, 한칸 전진 후 true까지 반환
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				String hiredate = rs.getString("hiredate");
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");
				area.append(empno+"\t"+ename+"\t"+job+"\t"+mgr+"\t"+hiredate+"\t"+sal+"\t"+comm+"\t"+deptno+"\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new EmpApp2();
	}
}
