package day1030.io;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FileCopyApp extends JFrame implements ActionListener {
	JLabel la_ori, la_dest;
	JTextField tf_ori, tf_dest;
	JButton bt_copy;

	public FileCopyApp() {
		super("복사프로그램");
		// 생성
		la_ori = new JLabel("원본경로");
		la_dest = new JLabel("복사경로");
		tf_ori = new JTextField(50);
		tf_dest = new JTextField(50);
		bt_copy = new JButton("복사");

		// 스타일
		la_ori.setPreferredSize(new Dimension(150, 30));
		la_dest.setPreferredSize(new Dimension(150, 30));
		tf_ori.setPreferredSize(new Dimension(500, 30));
		tf_dest.setPreferredSize(new Dimension(500, 30));

		// 조립
		add(la_ori);
		add(tf_ori);
		add(la_dest);
		add(tf_dest);
		add(bt_copy);

		// 리스너 연결
		bt_copy.addActionListener(this);
		
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		setSize(740, 180);
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE); 
//		여러파일을 복사할 때는 하면 안됀다! 
//		스트림을 닫을 기회가 없기 떄문에!!
//		해결책) 윈도우창을 닫을 때, 이벤트를 구현해야한다. 즉 WindowListener구현..
	}

	public void copy() {
		//메서드내의 지역변수는 반드시 개발자가 초기화해야한다!!.. 멤버변수가 아님
		FileInputStream fis=null;
		FileOutputStream fos=null;
		String ori=tf_ori.getText();
		String dest=tf_dest.getText();
		try {
			fis = new FileInputStream(ori);//입력스트림 생성
			fos = new FileOutputStream(dest);//출력스트림 생성
	
			//읽고 내뱉자!!
			int data;
			while (true) {
				data = fis.read();
				if (data == -1)
					break;
				fos.write(data);
			}

			JOptionPane.showMessageDialog(this, "복사가 완료되었습니다!");
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(this, "파일을 열 수 없습니다!");
			e1.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, "파일을 읽을 수 없습니다!");
			e1.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bt_copy) {
			copy();
		}
	}

	public static void main(String[] args) {
		new FileCopyApp();
	}
}
