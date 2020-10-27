package day1027.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*버튼에는 클릭, 텍스트박스-엔터 이벤트 등
 * 버튼에 클릭이벤트를 감지해보자!!!
 * */
public class MyActionListener implements ActionListener {
	private JTextField t_input;
	private JTextArea area;

	public MyActionListener(JTextField t_input, JTextArea area) {
		this.t_input = t_input;
		this.area = area;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = t_input.getText();
		if (!msg.equals("")) {
			area.append(msg + "\n");
			t_input.setText("");
		}
	}
}
