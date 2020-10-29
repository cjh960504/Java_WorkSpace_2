package day1029.graphic.imageAlbum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ImageAlbum extends JFrame implements ActionListener {
	JLabel l_imgName;
	JPanel p_west, p_south;
	ImagePanel p_center;
	JButton bt_prev, bt_next;
	String path = "C:/java_workspace/workspace/res/travel/";
	String[] imgName = { "aa.jpg", "ab.jpg", "ax.jpg", "bm.jpg", "et.jpg", "girl.jpg", "kg.jpg" };
	JScrollPane sp_west;
	int index = 0;

	public ImageAlbum() {
		l_imgName = new JLabel(imgName[index]);
		bt_prev = new JButton("이전");
		bt_next = new JButton("다음");
		p_center = new ImagePanel(path + imgName[index]);
		p_west = new JPanel();
		p_west.setBackground(Color.red);
		p_west.setPreferredSize(new Dimension(110, 500));
		sp_west = new JScrollPane(p_west);
//		sp_west.setPreferredSize(new Dimension(110, 500));
		for (int i = 0; i < imgName.length; i++) {
			ThumbPanel thumb = new ThumbPanel(path + imgName[i], p_center, l_imgName);
			thumb.setPreferredSize(new Dimension(100, 100));
			p_west.add(thumb);
		}
		p_south = new JPanel();
		p_south.add(bt_prev);
		p_south.add(bt_next);
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);

		add(l_imgName, BorderLayout.NORTH);
		add(sp_west, BorderLayout.WEST);
		add(p_south, BorderLayout.SOUTH);
		add(p_center, BorderLayout.CENTER);
		setSize(750, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bt_prev) {
			if (index > 0) {
				index -= 1;
				l_imgName.setText(path + imgName[index]);
				p_center.setImage(path + imgName[index]);
			}
		} else if (obj == bt_next) {
			if (index < imgName.length - 1) {
				index += 1;
				l_imgName.setText(path + imgName[index]);
				p_center.setImage(path + imgName[index]);
			}
		}
	}

	public static void main(String[] args) {
		new ImageAlbum();
	}
}
