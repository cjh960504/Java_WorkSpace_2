/*
	쇼핑몰 상품관리 프로그램 제작하기
*/
package day1106.db.shopping;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import commom.image.ImageUtil;

public class ShoppingApp extends JFrame {
	JPanel p_west, p_east, p_center; // 큰 패널들
	JPanel c_north, c_center; // 가운데 들어갈 패널들

	// 등록 폼 관련(p_west에 add)
	Choice ch_top, ch_sub;
	JTextField t_name, t_price, t_brand;
	JButton bt_find; // 이미지 찾아보기
	JPanel can; // 이지미 미리보기 그려질 곳
	JButton bt_regist;
	// ---------------------------------------------

	// 센터영역 (p_center)----------------------------
	// 검색관련(c_north)
	Choice ch_category; // 검색 카테고리
	JTextField t_keyword; // 검색어
	JButton bt_search; // 검색 버튼
	// ---------------------------------------------
	// 컨텐츠(테이블)관련 (c_center)
	JPanel content; // 테이블 나올 영역
	JTable table;
	JScrollPane scroll;
	// ---------------------------------------------
	// 센터영역 end-----------------------------------

	// 수정,삭제 폼 관련(p_east에 add)
	Choice ch_top2, ch_sub2;
	JTextField t_name2, t_price2, t_brand2;
	JButton bt_find2; // 이미지 찾아보기
	JPanel can2; // 이지미 미리보기 그려질 곳
	JButton bt_edit, bt_del;
	// ---------------------------------------------
	Connection con; // 접속이 성공이되면, 그 정보를 가진 인터페이스
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";

	HashMap<String, Integer> map = new HashMap<String, Integer>(); // 컬렉션 프레임워크중 , key-value의 쌍으로 객체를 관리해주는 객체
	HashMap<String, Integer> map2 = new HashMap<String, Integer>(); // 컬렉션 프레임워크중 , key-value의 쌍으로 객체를 관리해주는 객체
	JFileChooser chooser = new JFileChooser("C:/java_workspace/workspace/res/travel");
	Toolkit kit = Toolkit.getDefaultToolkit();// 플랫폼 종속적인 경로를 가져올 때는 툴킷 쓰자
	Image img;
	File file;
	ProductController productController; // 내가 만든 TableModel

	public ShoppingApp() {
		// 등록(서쪽) 폼 관련 생성
		p_west = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		t_name = new JTextField();
		t_price = new JTextField();
		t_brand = new JTextField();
		bt_find = new JButton("이미지찾기");
		can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, can);
			}
		};
		bt_regist = new JButton("등록");

		ch_top.add("Choose Category");

		// 서쪽 폼 조립
		p_west.add(ch_top);
		p_west.add(ch_sub);
		p_west.add(t_name);
		p_west.add(t_brand);
		p_west.add(t_price);
		p_west.add(bt_find);
		p_west.add(can);
		p_west.add(bt_regist);

		// 서쪽 스타일 적용
		ch_top.setPreferredSize(new Dimension(135, 40));
		ch_sub.setPreferredSize(new Dimension(135, 40));
		t_name.setPreferredSize(new Dimension(135, 30));
		t_brand.setPreferredSize(new Dimension(135, 30));
		t_price.setPreferredSize(new Dimension(135, 30));
		bt_find.setPreferredSize(new Dimension(135, 30));
		can.setPreferredSize(new Dimension(135, 115));
		bt_regist.setPreferredSize(new Dimension(135, 30));
		p_west.setPreferredSize(new Dimension(150, 600));

		// 프레임에 서쪽 영역 붙이기
		add(p_west, BorderLayout.WEST);

		// 가운데 영역 생성
		p_center = new JPanel();
		c_north = new JPanel();
		c_center = new JPanel();
		ch_category = new Choice();
		t_keyword = new JTextField();
		bt_search = new JButton("검색");
		table = new JTable(productController = new ProductController());
		scroll = new JScrollPane(table);

		// 스타일 적용
		c_north.setBackground(Color.pink);
		ch_category.setPreferredSize(new Dimension(130, 30));
		t_keyword.setPreferredSize(new Dimension(400, 30));
		bt_search.setPreferredSize(new Dimension(70, 30));

		// 가운데-검색 영역 조립
		c_north.add(ch_category);
		c_north.add(t_keyword);
		c_north.add(bt_search);

		// 가운데-테이블 영역 조립
		c_center.setLayout(new BorderLayout());
		c_center.add(scroll);

		// 가운데 영역의 전체 패널에 두 패널 부착
		p_center.setLayout(new BorderLayout());
		p_center.add(c_north, BorderLayout.NORTH);
		p_center.add(c_center, BorderLayout.CENTER);

		// 프레임에 가운데 영역 붙이기
		add(p_center);

		// 동쪽 영역 생성
		p_east = new JPanel();
		ch_top2 = new Choice();
		ch_sub2 = new Choice();
		t_name2 = new JTextField();
		t_price2 = new JTextField();
		t_brand2 = new JTextField();
		bt_find2 = new JButton("이미지찾기");
		can2 = new JPanel();
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");

		// 동쪽 조립
		p_east.add(ch_top2);
		p_east.add(ch_sub2);
		p_east.add(t_name2);
		p_east.add(t_brand2);
		p_east.add(t_price2);
		p_east.add(bt_find2);
		p_east.add(can2);
		p_east.add(bt_edit);
		p_east.add(bt_del);

		// 동쪽 스타일 적용
		ch_top2.setPreferredSize(new Dimension(135, 40));
		ch_sub2.setPreferredSize(new Dimension(135, 40));
		t_name2.setPreferredSize(new Dimension(135, 30));
		t_brand2.setPreferredSize(new Dimension(135, 30));
		t_price2.setPreferredSize(new Dimension(135, 30));
		bt_find2.setPreferredSize(new Dimension(135, 30));
		can2.setPreferredSize(new Dimension(135, 115));
		bt_edit.setPreferredSize(new Dimension(65, 30));
		bt_del.setPreferredSize(new Dimension(65, 30));
		p_east.setPreferredSize(new Dimension(150, 600));

		// 프레임에 동쪽 영역 붙이기
		add(p_east, BorderLayout.EAST);

		connect();// 접속
		getTopList();// 최상위 가져오기

		// 윈도우 창 닫을 시, 모든 연결을 해제 및 프로세스 종료
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				disconnect(); // 접속 해제!
				System.exit(0);
			}
		});

		// ch_top에 아이템 리스너 연결
		ch_top.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// System.out.println("바꿧니");
				// 바뀐 정보를 이용하여 하위 카테고리를 가져오자!!
				// 해시맵으로부터 key값을 이용하여, value를 추출한다!!
				if (ch_top.getSelectedIndex() > 0) {
					int topcategory_Id = map.get(ch_top.getSelectedItem()); // Choice객체의 선택한 Item 가져오기
//					System.out.println("추출된 value는 " + topcategory_Id);
					getSubList(topcategory_Id);
				} else {
					ch_sub.removeAll();
				}
			}
		});

		// 파일 찾기 버튼과 리스너 연결
		bt_find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findImage();// 쇼핑몰에 사용할 상품이지미 선택!!
				preview();// 이미지 미리보기 처리
			}
		});

		// 등록 버튼과 리스너 연결
		bt_regist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);// db연동 시 없앨 예정
	}

	// 오라클 접속
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 드라이버 로드!
			con = DriverManager.getConnection(url, user, password);// 접속
			if (con == null) {
				JOptionPane.showMessageDialog(this, "접속실패ㅋㅋ");
				System.exit(0);
			} else {
				// 성공 시, 윈도우 창에 유저명으로 접속했다는 메시지!!
				this.setTitle(user + "로 접속 중");
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, "드라이버를 찾을 수 없습니다!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 상위카테고리 가져오기
	public void getTopList() {
		String sql = "select * from topcategory";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 쿼리문을 수행하는 JDBC객체는?
			pstmt = con.prepareStatement(sql);// 쿼리문 준비!!

			// 결과집합을 담는 JDBC 객체는? (select문 수행 후 그 결과를 담는 객체)
			rs = pstmt.executeQuery(); // 쿼리 실행

			while (rs.next()) { // 커서 1칸 전진
				String name = rs.getString("name");
				int id = rs.getInt("topcategory_id");
				ch_top.add(name); // 사용자들이 보게 될 아이템
				map.put(name, id); // Integer-int : boxing함으로 NO상관
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 하위카테고리 가져오기
	public void getSubList(int topcategory_Id) {
		String sql = "select * from subcategory where topcategory_id=" + topcategory_Id;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 채우기 전에 모두 지우기!! (초기화)
			ch_sub.removeAll();
			ch_sub.add("Choose Category");
			// 서브카테고리 채우기
			while (rs.next()) {
				ch_sub.add(rs.getString("name"));
				map2.put(rs.getString("name"), rs.getInt("subcategory_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 상품이미지 선택 후, 미리보기 기능 구현
	public void findImage() {
		// OK버튼을 눌렀다면
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			// 파일정보를 구한다!!
			file = chooser.getSelectedFile();
			System.out.println("당신이 선택한 파일의 경로 : " + file.getAbsolutePath());
			img = kit.getImage(file.getAbsolutePath()); // 멤버 변수 img값을 구한다!
			img = ImageUtil.getCustomSize(img, 135, 115);
		}
	}

	// 미리보기 구현
	// paint로 그림 처리
	public void preview() {
		can.repaint();
	}

	// 등록 구현
	public void regist() {
		String sql = "insert into product(product_id, subcategory_id, product_name, brand, price, filename)";
		sql += " values(seq_product.nextval, ?, ?, ?, ?, ?)";
		// 물음표 값 정의하기
		int subcategory_id = map2.get(ch_sub.getSelectedItem());
		String product_name = t_name.getText();
		String brand = t_brand.getText();
		int price = Integer.parseInt(t_price.getText());
		String filename = file.getName();// 풀경로말고 이미지명만
		// System.out.println("subcategory_id : "+subcategory_id);
		// System.out.println("product_name : "+product_name);
		// System.out.println("brand: "+brand);
		// System.out.println("price: "+price);
		// System.out.println("filename : "+filename);
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, subcategory_id);
			pstmt.setString(2, product_name);
			pstmt.setString(3, brand);
			pstmt.setInt(4, price);
			pstmt.setString(5, filename);

			// 아래의 메서드의 반환값? 이 쿼리문에 의해 영향받는 레코드 수를 반환, 따라서 insert 경우엔 성공인 언제나 1
			// update, delete실패인 경우 0, 성공이면 1
			int result = pstmt.executeUpdate();
			if (result == 0) {
				JOptionPane.showMessageDialog(this, "등록 실패!");
			} else {
				JOptionPane.showMessageDialog(this, "등록 성공!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// product 테이블의 레코드 가져오기
	public void getProductList() {
		String sql = "select * from product";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//ArrayList<String[]> list = new ArrayList<String[]>();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // select문 수행 후 결과표를 rs에 대입
			// rs의 표 데이터를 ProductController가 보유한 data 이차원 배열에 대입!
			
			while (rs.next()) {
				String[] record = new String[6];
				record[0] = rs.getString("product_id");
				record[1] = rs.getString("subcategory_id");
				record[2] = rs.getString("product_name");
				record[3] = rs.getString("brand");
				record[4] = rs.getString("price");
				record[5] = rs.getString("filename");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 접속해제 메서드
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
		new ShoppingApp();
	}
}
