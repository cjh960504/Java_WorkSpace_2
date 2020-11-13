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
				int barValue = 0;
				url = new URL(t_url.getText());
				String name = FileManager.getFileName(url.getPath());
				con = url.openConnection();
				http = (HttpURLConnection) con;
				InputStream is = http.getInputStream();
				File file = new File("C:/java_workspace/workspace/res/data/" + name);
				fos = new FileOutputStream(file);

				int data = -1;
				while (true) {
					data = is.read();
					barValue += 1;
					bar.setValue(barValue);
		
					if (data == -1) {
						bar.setValue(100); 
						bar.updateUI();
						break;
					}
					
					fos.write(data);
				}
				System.out.println("완료");
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

	public static void main(String[] args) {
		new DownApp();
	}
}
