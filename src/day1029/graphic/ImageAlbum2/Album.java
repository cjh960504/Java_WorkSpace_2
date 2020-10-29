package day1029.graphic.ImageAlbum2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Album extends JFrame implements ActionListener{
	JLabel l_north;
	JPanel p_west, p_south;
	JButton bt_prev, bt_next;
	String path = "C:/java_workspace/workspace/res/travel/";
	String[] imgName = { "aa.jpg", "ab.jpg", "ax.jpg", "bm.jpg", "et.jpg", "girl.jpg", "kg.jpg" };
	CenterPanel p_center;
	JScrollPane sp_west;
	int index=0;
	
	public Album() {
		bt_prev=new JButton("이전");
		bt_next=new JButton("다음");
		l_north=new JLabel();
		p_west=new JPanel();
		p_west.setPreferredSize(new Dimension(200, 600));
		sp_west = new JScrollPane(p_west);
		p_center=new CenterPanel(l_north, path+imgName[index]);
		for(int i=0;i<imgName.length;i++) {
			ThumbPanel thumb = new ThumbPanel(path+imgName[i], p_center);
			p_west.add(thumb);
		}
		p_south=new JPanel();
		p_south.add(bt_prev);
		p_south.add(bt_next);
		add(p_center);
		add(p_south, BorderLayout.SOUTH);
		add(l_north, BorderLayout.NORTH);
		add(sp_west, BorderLayout.WEST);
		
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		setSize(1000, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bt_prev) {
			if (index > 0) {
				index-=1;
				p_center.setImage(path+imgName[index]);
			}
		} else if (obj == bt_next) {
			if (index < imgName.length - 1) {
				index+=1;
				p_center.setImage(path+imgName[index]);
			}
		}
		p_center.repaint();
		
	}
	public static void main(String[] args) {
		new Album();
	}
}
