package day1117.tree;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class ProductTree extends JFrame {
	JTree tree;
	JScrollPane scroll;

	// 배열만 구성하면 트리 생성은 메서드가 알아서 해주는 코드...
	String[] category = new String[4]; // 상위카테고리 배열
	String[][] product = new String[4][4];

	ArrayList<String> topCategory = new ArrayList<String>();//상위카테고리
	ArrayList<ArrayList> subCategory = new ArrayList<ArrayList>();	//하위카테고리
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";
	Connection con;

	public ProductTree() {
		// DB연동하여 배열들의 데이터를 실제 DB데이터로 가져오자!!
		connect();

		getTopCategory();
		for(int i=0;i<topCategory.size();i++) {
			String name = topCategory.get(i);
			ArrayList subList = (ArrayList) getSubCategory(name);
			subCategory.add(subList);
		}
		
		// 노드 생성을 외부 메서드로부터 공수받자!!
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("상품정보");
		for (int i = 0; i < topCategory.size(); i++) {
			top.add(getCreatedNode(topCategory.get(i), subCategory.get(i)));
		}
		tree = new JTree(top);
		scroll = new JScrollPane(tree);

		add(scroll);

		setSize(400, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // db연동 시 주석 필요
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
		});
	}

	// 이 메서드를 호출하는 자가, 배열을 넘기면 배열의 길이만큼 노드를 구성해줄 것임
	public DefaultMutableTreeNode getCreatedNode(String parentName, ArrayList<String> childName) {
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName); // 부모노드 생성
		// 자식의 수만큼 노드 만들어 부모에 부착!
		for (String obj : childName) {
			parent.add(new DefaultMutableTreeNode(obj));
		}

		return parent;
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

	// 상위 카테고리 가져오기
	public void getTopCategory() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int index = 0;
		String sql = "select * from topcategory";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//배열은 반드시 크기를 명시해야한다... 유연하지못하다
			//차라리 컬렉션 객체 중 List를 추천
			//
			while (rs.next()) {
				topCategory.add(rs.getString("name"));
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

	// 하위카테고리 가져오기
	public List getSubCategory(String topCategory_name ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> subList=new ArrayList<String>();
		
		String sql = "select * from subcategory where topcategory_id=(select topcategory_id from topcategory where name=?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, topCategory_name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				subList.add(rs.getString("name"));
				System.out.println(rs.getString("name"));
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
		return subList;
	}

	public void connect() {
		try {
			Class.forName(driver);
			System.out.println("드라이버 로드 성공");

			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				this.setTitle(user + "로 접속..");
			} else {
				JOptionPane.showMessageDialog(this, user + "로 접속에 실패하였습니다.");
				System.exit(0);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new ProductTree();
	}
}
