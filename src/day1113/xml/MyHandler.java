/*
	XML의 모든 노드에서 이벤트가 발생할때마다 아래의 핸들러 객체의 메서드를 
	알맞게 오버라이드하면 됨.
*/
package day1113.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {
	ArrayList<Pet> petList;
	Pet pet;
	boolean isType;
	boolean isName;
	boolean isAge;
	boolean isGender;

	// 문서가 시작되면 호출됨
	@Override
	public void startDocument() throws SAXException {
		System.out.println("문서를 시작합니다");
	}

	// 여는 태그 만났을때 호출됨 <태그> (/가 없는 태그)
	@Override
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		System.out.print("<" + tag + ">");

		// 여러 태그 중 pets를 만나면 ArrayList를 생성하자!!
		if (tag.equals("pets")) {
			petList = new ArrayList<Pet>();
		} else if (tag.equals("pet")) { // 하나의 pet 인스턴스 생성할 타임이다!!
			pet = new Pet();
		} else if (tag.equals("type")) { // ???
			// 시작 태그를 지금 지나가고 있음을 알려주자!!!
			isType = true;
		} else if (tag.equals("name")) { // ???
			isName = true;
		} else if (tag.equals("age")) { // ???
			isAge = true;
		} else if (tag.equals("gender")) { // ???
			isGender = true;
		}

	}

	// 태그와 태그사이의 데이터를 만났을 때 호출
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		System.out.print(data);
		if(isType) {
			pet.setType(data);			
		}else if(isName) {
			pet.setName(data);
		}else if(isAge) {
			pet.setAge(Integer.parseInt(data));
		}else if(isGender) {
			pet.setGender(data);
		}
	}

	// 닫는 태그를 만났을 때
	@Override
	public void endElement(String uri, String localName, String tag) throws SAXException {
		System.out.print("</" + tag + ">");
		
		if (tag.equals("pet")) { // 이 시점에 하나의 Pet이 완성된 시점이므로, 리스트에 담아두자!!
			petList.add(pet);
		} else if (tag.equals("type")) { // ???
			// 실행부가 지나가고 있는 위치를 알려주는 모든 논리값들을 다시 초기화!!
			isType = false;
		} else if (tag.equals("name")) { // ???
			// 실행부가 지나가고 있는 위치를 알려주는 모든 논리값들을 다시 초기화
			isName = false;
		} else if (tag.equals("age")) { // ???
			// 실행부가 지나가고 있는 위치를 알려주는 모든 논리값들을 다시 초기화
			isAge = false;
		} else if (tag.equals("gender")) { // ???
			// 실행부가 지나가고 있는 위치를 알려주는 모든 논리값들을 다시 초기화
			isGender = false;
		}
	}

	// 문서가 끝날 때
	@Override
	public void endDocument() throws SAXException {
		System.out.println("문서를 종료합니다");
		System.out.println("결과 보고서 : 총 " + petList.size() + "가 존재합니다.");
		for(Pet pet : petList) {
			System.out.println("type : " + pet.getType());
			System.out.println("name : " + pet.getName());
			System.out.println("age : " + pet.getAge());
			System.out.println("gender : " + pet.getGender());
			System.out.println("-------------------------------------------");
		}
	}
}
