package day1028.graphic.photoalbum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PhotoAlbum extends JFrame implements ActionListener{
	AlbumPanel p; //페인트 메서드를 재정의하려면, 클래스로 정의해야 한다..
	JPanel p_south; //버튼 2개를 얹힐 패널
	JButton bt_prev, bt_next; //이전, 다음 버튼
	int count=0;
	
	public PhotoAlbum() {
		super("이미지 앨범");
		p = new AlbumPanel();
		p_south= new JPanel();
		bt_prev = new JButton("이전사진");
		bt_next = new JButton("다음사진");
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		p.setBackground(Color.black);
		
		add(p);
		p_south.add(bt_prev);
		p_south.add(bt_next);
		add(p_south, BorderLayout.SOUTH);
		setSize(500, 450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		//이벤트소스란? 이벤트를 일으킨 컴포넌트를 의미
		if(obj==bt_prev) {
			if(count > 0) {
				count -= 1;	
				p.setCount(count);
			}
		}else if(obj==bt_next) {
			if(count < 9) {
				count += 1;
				p.setCount(count);
			}
		}		
		//화면갱신 (직접 AlbumPanel의 paint() 호출불가)
		//갱신하도록 요청!!
		p.repaint();///다시 그려주세요! update() -> paint()
	}
	public static void main(String[] args) {
		new PhotoAlbum();
	}
	
}
