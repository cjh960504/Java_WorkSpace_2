package day1109.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApp extends JFrame implements ActionListener{
	JPanel p_north;
	JPanel p_center;
	JButton[] btn = new JButton[4];
	String[] title= {"Home","게시판","로그인","회원가입"};
	Color[] color= {Color.red, Color.yellow,Color.orange,Color.green};
//	Home home;
//	Board board;
//	Login login;
//	Member member;
	//패널들을 반복문으로 처리하려면, 개성있는 이름이 아니라, 순번으로
	//부르기 위한 배열이 편한다
	JPanel[] pages = new JPanel[4];
	
	public MainApp() {
		p_north = new JPanel();
		//페이지 생성
		p_center=new JPanel();
//		home = new Home();
//		board = new Board();
//		login = new Login();
//		member= new Member();
//		
		pages[0]=new Home();
		pages[1]=new Board(); 
		pages[2]=new Login();
		pages[3]=new Member();
		
		for (int i=0;i<btn.length;i++) {
			btn[i]=new JButton(title[i]);
			btn[i].setBackground(color[i]);
			btn[i].addActionListener(this);
			p_north.add(btn[i]);
		}
//		p_north.setPreferredSize(new Dimension(600, 500));
		add(p_north, BorderLayout.NORTH);
	
		//페이지 스타일설정
		for(int i=0;i<pages.length;i++) {
			pages[i].setPreferredSize(new Dimension(580, 480));
			//센터영역에 4개의 준비된 페이지를 붙여넣자!!
			p_center.add(pages[i]);
		}
		add(p_center);
		
//		setSize(600, 500);
		pack();//내용물의 크기만큼 수축!
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new MainApp();
	}

	//모든 페이지를 대상으로 반복문을 실행하되, 보여질 페이지만 true로 
	//나머지는 false로..
	public void setPage(int num) {
		for(int i=0;i<pages.length;i++) {
			if(i==num) {
				pages[i].setVisible(true);			
			}else {
				pages[i].setVisible(false);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource();
		if(bt == btn[0]) { //Home
			setPage(0);
		}else if(bt == btn[1]) {//Board
			setPage(1);
		}else if(bt == btn[2]) {//Login
			setPage(2);
		}else if(bt == btn[3]) {//member
			setPage(3);
		}
	}
}
