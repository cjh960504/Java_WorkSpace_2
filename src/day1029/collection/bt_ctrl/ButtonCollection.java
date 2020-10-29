package day1029.collection.bt_ctrl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonCollection extends JFrame implements ActionListener{
	JPanel p_north;
	JPanel p_center;
	JButton bt_create;
	JButton bt_chageBg;
	//배열은 크기를 명시(고정)해야하므로, ArrayList가 적당
	ArrayList<JButton> btArray=new ArrayList<JButton>();
	
	public ButtonCollection() {
		super("버튼놀이");
		p_north = new JPanel();
		bt_create = new JButton("생성");
		bt_chageBg = new JButton("배경색");
		p_center = new JPanel();
		
		//부착
		p_north.add(bt_create);
		p_north.add(bt_chageBg);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		
		//버튼과 리스너 연결
		bt_create.addActionListener(this);
		bt_chageBg.addActionListener(this);
		
		setSize(700, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void create() {
		JButton bt = new CustomButton();
		btArray.add(bt);
		p_center.add(bt);
		bt.setText("버튼"+btArray.size());
		p_center.updateUI();
		//p_center에 버튼을 그린게 아니라, 버튼을 추가한 것이다..
		//따라서 이때는 p_center를 갱신하면 된다! updateUI()메서드 이용!!
		
	}
	public void setBg() {
		//btn리스트에 들어있는 모든 요소를 대상으로 배경색 바꾸기
		//ArrayList는 순서있는 집합이므로, for문을 사용할 수 있다.
		for(int i=0;i<btArray.size();i++) {
			btArray.get(i).setBackground(Color.RED);
			//p_center에 무언가를 그리거나 생성하는게아니라, 
			//현재 존재하는 컴포넌트의 속성을 바꿔주는 것이라서 
			//repaint나 updateUI가 필요없다.
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bt_create) {
			create();
		}else if(obj==bt_chageBg) {
			setBg();
		}
	
//		p_center.repaint(); => 그렸을때 즉, JPanel에 paint를이용해 버튼을
		//그렸을때!
	}
	
	public static void main(String[] args) {
		new ButtonCollection();
	}
}
