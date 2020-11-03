package day1103.gallery;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import commom.image.ImageUtil;

public class AutoGallery extends JFrame {
	JPanel can;
	JButton bt_prev, bt_pause, bt_auto, bt_next;
	URL url;
	// os의 종속된 경로로 가져올때는 Toolkit을 이용한다
	Toolkit kit;
	Image[] img = new Image[10];
	int index = 0;
	Thread autoThread; // 자동사용넘기기용 Thread
	boolean flag=false;
	
	public AutoGallery() {
		kit = Toolkit.getDefaultToolkit();
		img[0] = ImageUtil.getCustomSize(kit.getImage("C:/java_workspace/workspace/res/travel/aa.jpg"), 680, 500);
		img[1] = ImageUtil.getCustomSize(kit.getImage("C:/java_workspace/workspace/res/travel/ab.jpg"), 680, 500);
		img[2] = ImageUtil.getCustomSize(kit.getImage("C:/java_workspace/workspace/res/travel/ax.jpg"), 680, 500);
		img[3] = ImageUtil.getCustomSize(kit.getImage("C:/java_workspace/workspace/res/travel/bm.jpg"), 680, 500);
		img[4] = ImageUtil.getCustomSize(kit.getImage("C:/java_workspace/workspace/res/travel/et.jpg"), 680, 500);
		img[5] = ImageUtil.getCustomSize(kit.getImage("C:/java_workspace/workspace/res/travel/girl.jpg"), 680, 500);
		img[6] = ImageUtil.getCustomSize(kit.getImage("C:/java_workspace/workspace/res/travel/kg.jpg"), 680, 500);
		img[7] = ImageUtil.getCustomSize(kit.getImage("C:/java_workspace/workspace/res/travel/mx.jpg"), 680, 500);
		img[8] = ImageUtil.getCustomSize(kit.getImage("C:/java_workspace/workspace/res/travel/pk.jpg"), 680, 500);
		img[9] = ImageUtil.getCustomSize(kit.getImage("C:/java_workspace/workspace/res/travel/ub.jpg"), 680, 500);

		can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img[index], 0, 0, this);
			}
		};
		bt_prev = new JButton(ImageUtil.getIcon(this.getClass(), "res/prev.png", 45, 45));
		bt_pause = new JButton(ImageUtil.getIcon(this.getClass(), "res/pause.png", 45, 45));
		bt_auto = new JButton(ImageUtil.getIcon(this.getClass(), "res/auto.png", 45, 45));
		bt_next = new JButton(ImageUtil.getIcon(this.getClass(), "res/next.png", 45, 45));
		
		//미리 스레드를 만들어서 flag값으로만 스레드의 동작상태를 조절하기 위해!!
		//버튼은 flag의 값만 바꿔주면 된다~
		autoThread = new Thread() {
			public void run() {
				while (index < img.length - 1) {
					if(flag) {
						next();
						}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		autoThread.start();
		
		// 버튼과 리스너 연결
		bt_prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});
		bt_pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				flag=false;
			}
		});
		bt_auto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				flag=true;
			}
		});
		bt_next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});

		// 스타일적용
		can.setPreferredSize(new Dimension(680, 500));
		can.setBackground(Color.black);
		bt_prev.setPreferredSize(new Dimension(45, 45));
		bt_pause.setPreferredSize(new Dimension(45, 45));
		bt_auto.setPreferredSize(new Dimension(45, 45));
		bt_next.setPreferredSize(new Dimension(45, 45));
		setLayout(new FlowLayout());

		add(can);
		add(bt_prev);
		add(bt_pause);
		add(bt_auto);
		add(bt_next);
		setSize(700, 650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	// 다음 사진 나오게
	public void next() {
		if (index < img.length - 1) {
			index++;
			can.repaint();
		}
	}

	// 이전 사진 나오게
	public void prev() {
		if (index > 0) {
			index--;
			can.repaint();
		}
	}

	public static void main(String[] args) {
		new AutoGallery();
	}

}
