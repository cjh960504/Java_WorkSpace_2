package day1116.pubapi;

//산 1개를 담게 될 클래스 정의

public class Mountain {
	
	private int mntnid;//산의 고유 코드
	private String mntnnm; // 산 이름을 담게 될 변수
	private String mntninfopoflc;//소재지
	private int mntninfohght; //산 높이
	
	public int getMntnid() {
		return mntnid;
	}
	public void setMntnid(int mntnid) {
		this.mntnid = mntnid;
	}
	public String getMntnnm() {
		return mntnnm;
	}
	public void setMntnnm(String mntnnm) {
		this.mntnnm = mntnnm;
	}
	public String getMntninfopoflc() {
		return mntninfopoflc;
	}
	public void setMntninfopoflc(String mntninfopoflc) {
		this.mntninfopoflc = mntninfopoflc;
	}
	public int getMntninfohght() {
		return mntninfohght;
	}
	public void setMntninfohght(int mntninfohght) {
		this.mntninfohght = mntninfohght;
	}
}
