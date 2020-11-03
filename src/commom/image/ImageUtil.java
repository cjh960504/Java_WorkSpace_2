package commom.image;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageUtil {
	//아이콘을 반환해주는 메서드 
	//static이라 new할 필요없게!
	public static ImageIcon getIcon(Class target ,String path, int width, int height) {
		ImageIcon icon=null;
		
		//넘어온 클래스 class를 이용하여 이미지 URL가져와서 아이콘 생성
		icon = new ImageIcon(target.getClassLoader().getResource(path));
		
		//크기가 조정된 이미지 생성
		Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}
	
	//이미지를 넘겨받아, 원하는 크기의 이미지를 반환하는 메서드
	public static Image getCustomSize(Image img, int width, int height) {
		return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
}
