package day1028.graphic.color;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/*나만의 패널 정의하기 - 기존 패널의 커스텀마이징 */
public class ThumbPanel extends JPanel implements MouseListener{
	JPanel p_center;
	Color color;
	//너비, 높이, 색상을 가진 패널이 태어나게!
	public ThumbPanel(JPanel p_center, Color color) {
		this.color = color;
		this.p_center = p_center;
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(this.color);
		this.addMouseListener(this);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//센터영역패널의 배경색을 나(현재)와 같은 색상으로 설정하자!!
		p_center.setBackground(color);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
