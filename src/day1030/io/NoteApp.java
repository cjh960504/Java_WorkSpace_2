package day1030.io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NoteApp extends JFrame implements ActionListener{
	JMenuBar bar; // 메뉴들을 올려놓을 막대
	JMenu m_file, m_edit, m_style, m_view, m_help;
	//객체자료형도 자료형이므로, 배열이 가능
	JMenuItem[] items;
	String[] item_title = {  "열기(O)", 
			"저장(S)" };
 	JTextArea area;
	JScrollPane scroll; //area에 붙여질 스크롤
	String path; //얻어온 파일 경로
	
	public NoteApp() {
		this.setTitle("메모장");
		bar = new JMenuBar(); // 바 생성
		
		//메뉴들 생성
		m_file = new JMenu("파일(F)");
		m_edit = new JMenu("편집(E)");
		m_style = new JMenu("서식(O)");
		m_view = new JMenu("보기(V)");
		m_help = new JMenu("도움말(H)");
		
		//아이템 생성
		//주의 ) 메뉴아이템이 생성된 게 아니라, 아이템이 들어갈 자리를 8칸 확보한 상태
		//js와는 달리, 자바에서는 배열의 자료형이 이미 결정되면, 해당 자료형만 넣을 수 있음!!
		items = new JMenuItem[item_title.length];
		for(int i=0;i<items.length;i++) {
			items[i] = new JMenuItem(item_title[i]);
			items[i].addActionListener(this);
			m_file.add(items[i]); // 파일 메뉴에 아이템 부착
			if(i==4 || i==6) {
				m_file.addSeparator();				
			}
		}
		area = new JTextArea();
		scroll = new JScrollPane(area); //스크롤 달고 싶은 컴포넌트를 생성자 매개변수로
		
		//바에 메뉴들 부착!
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);
		
		//바는 워낙 특수성이 있기 때문에 배치관리자와 상관없이 언제나 윈도우의 상단영역에 붙여짐!!
		this.setJMenuBar(bar); //JFrame에 바 부착!
		
		//프레임에 scroll부착 (얼핏 보기에는 area를 부착해야될 것 같으나, scroll이 area를 포함하고 있으므로
		//scroll을 붙여줘야한다!!!
		add(scroll);
		
		//bar 스타일 적용
		bar.setBackground(Color.black);
		m_file.setForeground(Color.white);
		m_edit.setForeground(Color.white);
		m_help.setForeground(Color.white);
		m_view.setForeground(Color.white);
		m_style.setForeground(Color.white);
		
		//메뉴의 크기 조정
		m_file.setPreferredSize(new Dimension(70, 30));
		m_edit.setPreferredSize(new Dimension(70, 30));
		m_style.setPreferredSize(new Dimension(70, 30));
		m_view.setPreferredSize(new Dimension(70, 30));
		m_help.setPreferredSize(new Dimension(70, 30));
		
		//area 스타일 적용
		area.setBackground(Color.LIGHT_GRAY);
		area.setFont(new Font("궁서체", Font.BOLD|Font.ITALIC, 30));
		area.setForeground(Color.black);
		setSize(700, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public void open() {
		JFileChooser chooser = new JFileChooser();
		FileReader fr=null;
		int choose=chooser.showOpenDialog(this);
		if(choose==0) {
			path = chooser.getSelectedFile().getAbsolutePath();
			try {
				fr = new FileReader(path);
				String tmp="";
				int data;
				while(true) {
					data=fr.read();
					if(data==-1) break;
					tmp+=(char)data;
				}
				System.out.println(tmp);
				area.append(tmp);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(fr!=null) {
					try {
						fr.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	public void save() {
		String text = area.getText();
		FileWriter fw=null;
		
		try {
			fw=new FileWriter(path);
			for(int i=0;i<text.length();i++) {
				int data = text.charAt(i);
				fw.write(data);
			}
			JOptionPane.showMessageDialog(this, "저장이 완료되었습니다!");
			path="";
			area.setText("");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "파일에 쓸 수 없습니다.!");
			e.printStackTrace();
		}finally {
			if(fw!=null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==items[0]) {
			open();
		}else if(obj==items[1]) {
			save();
		}
		
	}
	public static void main(String[] args) {
		new NoteApp();
	}
}