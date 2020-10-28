package day1028.graphic.photoalbum;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

//Canvas를 사용하지 않고도, 패널을 이용하면 동일한 효과를 낼 수 있다!!
public class AlbumPanel extends JPanel {
	// 보여질 이미지 10개를 배열로 준비하면 효율성이 있을 거임
	int count=0;
	Toolkit kit=Toolkit.getDefaultToolkit();
	String dir = "C:/java_workspace/workspace/res/travel/";
	String[] src= {"aa.jpg"
			, "ab.jpg"
			, "ax.jpg"
			, "bm.jpg"
			, "et.jpg"
			, "girl.jpg"
			,"kg.jpg"
			,"mx.jpg"
			, "pk.jpg"
			,"ub.jpg"
			, "ya.jpg"
			};
	Image[] img = new Image[11];
	
	//생성자에서 이미지 생성
	public AlbumPanel() {
		for(int i=0;i<img.length;i++) {
			img[i] = kit.getImage(dir+src[i]);
			img[i] = img[i].getScaledInstance(500, 400, Image.SCALE_SMOOTH);
		}
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	//우리가 패널을 눈으로 보고 있다는 것은 이미 paint메서드 호출에 의해, 그림이 완성되었기 때문이다.
	@Override
	public void paint(Graphics g) {
		g.drawImage(img[count], 0, 0, this);
	}

}
