package day1030.album;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class GalleryApp extends JFrame implements ActionListener{
	JPanel p_west, p_center;
	JScrollPane scroll;
	JLabel la_name;//상단 제목 라벨
	XCanvas can;//센터에 크게 나올 이미지를 그릴 캔버스
	JPanel p_south;
	JButton bt_prev, bt_next;

	ArrayList<Thumb> list = new ArrayList<Thumb>();// 썸네일 배열 선언
	String dir = "C:/java_workspace/workspace/res/travel/";
	String[] src = { "aa.jpg", "ab.jpg", "ax.jpg", "bm.jpg", "et.jpg", "girl.jpg", "kg.jpg", "mx.jpg", "pk.jpg",
			"ub.jpg" };
	int index=0;
	
	public GalleryApp() {
		super("자바 갤러리");
		// 생성
		p_west = new JPanel();
		p_center = new JPanel();
		scroll = new JScrollPane(p_west);
		la_name = new JLabel(src[index]+"("+(index+1)+"/"+src.length+")", SwingConstants.CENTER);
		can = new XCanvas(dir+src[index]);
		p_south = new JPanel();
		bt_prev = new JButton("이전");
		bt_next = new JButton("다음");
		
		// 썸네일 생성
		for (int i = 0; i < 10; i++) {
			Thumb thumb = null;
			list.add(thumb = new Thumb(dir+src[i], this));
			p_west.add(thumb);
		}

		// 스타일
		la_name.setPreferredSize(new Dimension(700, 50));
		la_name.setFont(new Font("Verdana", Font.BOLD, 25));
		p_west.setPreferredSize(new Dimension(100, 600));
		p_center.setPreferredSize(new Dimension(700, 600));
		p_center.setBackground(Color.GREEN);
		

		// 조립
		p_south.add(bt_prev);
		p_south.add(bt_next);
		p_center.add(la_name);
		p_center.add(can);
		p_center.add(p_south);		
		add(scroll, BorderLayout.WEST);
		add(p_center);
		
		//버튼과 리스너 연결
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		// 윈도우를 화면 중앙에 띄우는 법
		// null값을 넣어주면 화면의 중앙으로
		setVisible(true);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();//이벤트일으킨 컴포넌트 
		if(obj==bt_prev) {
			prev();
		}else if(obj.equals(bt_next)) {
			next();
		}
	}
	public void updateData() {
		can.setSrc(dir+src[index]);
		can.repaint(); //update() -> paint()		
		//제목변경
		la_name.setText(src[index]+"("+(index+1)+"/"+src.length+")");
		System.out.println("현재"+index);
	}
	public void next() {
		//그림은 XCanvas가 담당하므로, 그려질 img를 변경시켜주고, 다시 그리라고 하면 됨...
		if(index<src.length-1) {
			index++;
			updateData();
		}else {
			//public static void showMessageDialog(Component parentComponent,
            //Object message) => Component parentComponent는 부모컴포넌트가아니라
			//바깥쪽 컴포넌트! = window창 즉, JFrame!
			JOptionPane.showMessageDialog(this, "마지막 이미지입니다!");
		}
	}
	public void prev() {
		if(index>0) {
			index--;
			updateData();
		}else {
			JOptionPane.showMessageDialog(this, "첫번째 이미지입니다!");
		}
	}
	public static void main(String[] args) {
		new GalleryApp();
	}
}
