/*
SAX Parsing 시 모든 node(요소, 텍스트 등 xml을 이루는 요소들을 일컫음)마다 이벤트를 발생시켜주는 객체
*/
package day1116.pubapi;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MountainHandler extends DefaultHandler{
	//발견되는 산이 있을 때 VO로 생성 후, 그 VO를 담게될 벡터
	Vector<Mountain> mtList;
	Mountain mt;
	
	//현재 실행부가 어느 위치를 지났는지 알기 위한 변수
	boolean isMntnid;
	boolean isMntnnm;
	boolean isMntninfopoflc;
	boolean isMntninfohght;
	
	@Override //xml문서 시작할 때
	public void startDocument() throws SAXException {
		//문서가 시작되면 비어있는 벡터를 생성한다!
	}
	
	@Override//모든 시작태그를 만날 때 호출
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.println("<" + tag + ">");
		
		if(tag.equals("items")){ //여기서 벡터 만들고
			mtList = new Vector<Mountain>();
		}else if(tag.equals("item")) { //여기서 VO 생성
			mt = new Mountain();
		}else if(tag.equals("mntnid")) { // 실행부가 아이디를 지나가다고 표시
			isMntnid = true;
		}else if(tag.equals("mntnnm")) {// 실행부가 산이름을 지나간다고 표시
			isMntnnm=true;
		}else if(tag.equals("mntninfopoflc")) { //실행부가 산의 위치를 지나간다고 표시
			isMntninfopoflc=true;
		}else if(tag.equals("mntninfohght")) {//실행부가 산의 높이를 지나간다고 표시
			isMntninfohght=true;
		}
	}
	
	@Override //태그 사이의 텍스트를 만날 때 호출되는 메서드
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		if(isMntnid) {
			mt.setMntnid(Integer.parseInt(data));
		}else if(isMntnnm) {
			mt.setMntnnm(data);
		}else if(isMntninfopoflc) {
			mt.setMntninfopoflc(data);
		}else if(isMntninfohght) {
			mt.setMntninfohght(Integer.parseInt(data));
		}
	}
	
	@Override //닫는 태그를 만날 때 호출되는 메서드
	public void endElement(String uri, String localName, String tag) throws SAXException {
		if(tag.equals("item")) {
			mtList.add(mt);
		}else if(tag.equals("mntnid")) { // 실행부가 아이디를 지나가다고 표시
			isMntnid = false;
		}else if(tag.equals("mntnnm")) {// 실행부가 산이름을 지나간다고 표시
			isMntnnm=false;
		}else if(tag.equals("mntninfopoflc")) { //실행부가 산의 위치를 지나간다고 표시
			isMntninfopoflc=false;
		}else if(tag.equals("mntninfohght")) {//실행부가 산의 높이를 지나간다고 표시
			isMntninfohght=false;
		}
	}
	@Override //문서가 끝날 떄
	public void endDocument() throws SAXException {
		System.out.println("검색된 산의 갯수는 " + mtList.size());
		for(int i=0;i<mtList.size();i++) {
			Mountain mt = mtList.get(i);
			System.out.println("산아이디 : "+mt.getMntnid());
			System.out.println("산이름 : "+mt.getMntnnm());
			System.out.println("산위치 : "+mt.getMntninfopoflc());
			System.out.println("산높이 : "+mt.getMntninfohght());
			System.out.println("-------------------------------------------------");
		}
	}
	
}
