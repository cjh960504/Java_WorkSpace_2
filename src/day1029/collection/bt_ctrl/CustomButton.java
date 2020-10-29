package day1029.collection.bt_ctrl;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CustomButton extends JButton implements ActionListener{
	public CustomButton() {
		// TODO Auto-generated constructor stub
		this.addActionListener(this);
	}
	 @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setBackground(Color.green);
	}
}
