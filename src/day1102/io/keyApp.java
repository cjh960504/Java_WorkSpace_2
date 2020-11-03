package day1102.io;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class keyApp extends JFrame{
	JTextField t;
	URL url;
	
	public keyApp() {
		url = this.getClass().getClassLoader().getResource("res/test.txt");
		t=new JTextField(20);
		t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==10) {
					send();
				}
			}
		});
		
		add(t);
		setLayout(new FlowLayout());
		setSize(400,100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
		
	public void send() {
		System.out.println(url.getPath());
		File file;
		FileWriter writer=null;
		try {
			URI uri = url.toURI();
			file = new File(uri);
//			System.out.println(file.length());
			writer = new FileWriter(file, true);
			BufferedWriter buffW = new BufferedWriter(writer);
			buffW.write(t.getText());
			buffW.newLine();
			buffW.flush();
			t.setText("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new keyApp();
	}
}
