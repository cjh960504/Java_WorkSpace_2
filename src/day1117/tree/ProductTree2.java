package day1117.tree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class ProductTree2 extends JFrame {
	JTree tree;
	JScrollPane scroll;

	// 배열만 구성하면 트리 생성은 메서드가 알아서 해주는 코드...
	String[] category = new String[4]; // 상위카테고리 배열
	String[][] product = { { "티셔츠", "점퍼", "니트", "가디건" }, { "청바지", "반바지", "면바지", "핫바지" }, { "귀걸리", "목걸이", "반지", "팔찌" },
			{ "구두", "운동화", "스니커즈", "샌들" } };
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="user1104";
	String password="user1104";	
	Connection con;
	
	public ProductTree2() {
		//DB연동하여 배열들의 데이터를 실제 DB데이터로 가져오자!!
		connect();
		getTopCategory();
		// 노드 생성을 외부 메서드로부터 공수받자!!
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("상품정보");
		for (int i = 0; i < category.length; i++) {
			top.add(getCreatedNode(category[i], product[i]));
		}
		tree = new JTree(top);
		scroll = new JScrollPane(tree);

		add(scroll);

		setSize(400, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // db연동 시 주석 필요
		setLocationRelativeTo(null);
	}

	// 이 메서드를 호출하는 자가, 배열을 넘기면 배열의 길이만큼 노드를 구성해줄 것임
	public DefaultMutableTreeNode getCreatedNode(String parentName, String[] childName) {
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName); // 부모노드 생성
		// 자식의 수만큼 노드 만들어 부모에 부착!
		for (String obj : childName) {
			parent.add(new DefaultMutableTreeNode(obj));
		}

		return parent;
	}
	
	public void connect() {
		try {
			Class.forName(driver);
			System.out.println("드라이버 로드 성공");
			
			con = DriverManager.getConnection(url, user, password);
			if(con!=null) {
				JOptionPane.showMessageDialog(this, "DB 연결 성공!");
			}else {
				JOptionPane.showMessageDialog(this, "DB 연결 실패!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void getTopCategory() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int index=0;
		String sql = "select * from topcategory";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				category[index++] = rs.getString("name");
//				System.out.println(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getSubCategory() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="";
	}

	public static void main(String[] args) {
		new ProductTree2();
	}
}
