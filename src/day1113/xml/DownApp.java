package day1113.xml;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import commom.file.FileManager;

public class DownApp extends JFrame {
	JTextField t_url;
	JButton bt;
	JProgressBar bar;
	URL url;
	URLConnection con;
	HttpURLConnection http;
	FileOutputStream fos;

	public DownApp() {

		t_url = new JTextField(60);
		bt = new JButton("다운로드");
		bar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);

		bar.setPreferredSize(new Dimension(750, 70));
		bar.setStringPainted(true);
		add(t_url);
		add(bt);
		add(bar);

		setLayout(new FlowLayout());
		setSize(700, 200);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		bt.addActionListener((e) -> {
			try {
				bar.setValue(0);
				
				int readCount =0; //지금까지 읽은 바이트 수
				int total = 0; //다운받을 자원의 총 바이트 수
				url = new URL(t_url.getText());
				String name = FileManager.getFileName(url.getPath());
				con = url.openConnection();
				http = (HttpURLConnection) con;
				InputStream is = http.getInputStream();
				File file = new File("C:/java_workspace/workspace/res/data/" + name);
				fos = new FileOutputStream(file);
				total  = con.getContentLength();

				int data = -1;
				
				while (true) {
					data = is.read();
				
					bar.setValue((int)getPercent(readCount, total));
					System.out.println((int)getPercent(readCount, total));
					bar.updateUI();
					
					if (data == -1) { 
						break;
					}
					readCount++;
					fos.write(data);
				}
				JOptionPane.showMessageDialog(this, "다운로드 완료!");
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	//퍼센트를 구하는 메서드 정의
	public float getPercent(int read, float total) {
		// 읽은수/총바이트수 * 100
		return (read/total)*100; 
	}
	
	public static void main(String[] args) {
		new DownApp();
	}
}
