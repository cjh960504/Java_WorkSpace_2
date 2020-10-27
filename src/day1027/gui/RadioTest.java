package day1027.gui;

/*
 -단축기 모두 보기 : crtl + shift + L
    -자동 import : crtl + shift + O
    -자동 정렬 : crtl + shitf + F
    -해당 객체의 api문서 바로가기 : 해당 클래스 커서 올린 후 shift + F2
    -출력 sout이라는 단축단어 입력과 동시에 ctrl + space
 */
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Frame;

public class RadioTest extends Frame {
	CheckboxGroup group;

	public RadioTest() {
		setLayout(new FlowLayout());
		group = new CheckboxGroup();
		this.add(new Checkbox("운동", group, false));
		this.add(new Checkbox("요리", group, false));
		this.add(new Checkbox("수영", group, false));
		this.add(new Checkbox("게임", group, false));
		this.add(new Checkbox("산책", group, false));
		this.add(new Checkbox("오목", group, false));
		this.add(new Checkbox("잠자기", group, false));
		this.add(new Checkbox("술먹기", group, true));
		
		setSize(300, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new RadioTest();
	}
}
