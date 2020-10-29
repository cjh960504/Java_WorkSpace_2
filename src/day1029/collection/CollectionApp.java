package day1029.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;

/*
 * Collection Framework(전제조건은 객체만을 대상으로 한다.)
 * 자바언어에서는 객체를 모아서 처리할 떄 유용한 api를 지원하는데, 이 api를 가리켜
 * 컬렉션 프레임웍이라 한다. 그리고 java.util에서 지원한다.
 * 컬렉션 프레임웍에서 지원하는 객체의 수는 상다히 많기는 하지만, 크게는 모여진 모습에 따라서
 * 1)순서있는 유형 List형: 배열과 거의 같다!!
 *자바의 배열과 차이가 있다면?
 *	- 배열 : 1.생성 시 크기 명시, 따라서 동적일 수 없다.
 *				2. 자로형을 섞어 사용할 수 없다. 
 * 			ex)int[] arr= new Int[5];
 * - 리스트 : 크기가 자유롭다. 객체 자료형을 섞어서 넣을 수 있다.
 * 2)순서없는 유형 Set형:
 * 3)key-value의 유형 Map형:
*/
public class CollectionApp {
	//List형을 테스트해보자
	public void showList() {
		//List형의 최상위 객체인 List는 인터페이스이며, List로 기본적으로 
		//가져야할 추상메서드가 명시되어 있다.
		//Generic Type으로 선언하면, 컬렉션 프레임웍에 넣을 수 있는 자료형을 제한할 수 있다!
		//<>=안전장치!
		ArrayList<JButton> list = new ArrayList<JButton>(3);//리스트 구조들은 배열과 거의 같다! 
		
		//js의 배열과 동작방식이 동일하다!
		list.add(new JButton("첫 버튼"));
//		list.add("똥");
//		list.add("마");
//		list.add("려");
//		list.add("워");
		list.add(new JButton("마지막 버튼"));
		
		//배열은 length이지만, collection framework는 모두 size()
		System.out.println("데이터 수는 " + list.size());
		
		for(int i=0;i<list.size();i++) {
			JButton bt1= list.get(i);
			System.out.println(bt1.getText());
		}
		
		//리스트는 중복된 데이터를 허용할까?
		//결론: 중복여부는 따지지 않고 무조건 넣는다!!
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("apple");
		list2.add("apple");
		list2.add("apple");
		list2.add("apple");
		System.out.println("list2의 데이터 수는 "+list2.size());
	}
	public void showSet() {
		HashSet<String> set = new HashSet<String>();
		set.add("banana");
		set.add("banana");
		set.add("banana");
		set.add("banana");
		System.out.println("HashSet의 크기는 "+ set.size());
		//결론 : 똑똑하다. 즉, 중복된 데이터 받아들이지 않음
		
		HashSet<String> set2 = new HashSet<String>();
		set2.add("딸기");
		set2.add("바나나");
		set2.add("포도");
		
		//반복문으로 set2의 모든 데이터를 출력!
		Iterator<String> it = set2.iterator();
		while(it.hasNext()) {//요소가 존재하는지 판단
			String s=it.next();//다음 요소를 반환
			System.out.println(s);
		}
	}
	public void showMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("k1", "장미");
		map.put("k2", "튤립");
		map.put("k3", "안개꽃");
		map.put("k3", "할미꽃");
		System.out.println("map의 길이는 "+map.size()+" / 중복확인 "+map.get("k3"));
		//결론 : key값은 중복을 허용하지 않는다.. 따라서 대체되어 버린다...		
		
		//반복문을 이용해서 모든 꽃을 출력하기
		Set<String> keySet = map.keySet();//key만 따로 추출!
		//그리고 Set은 iterator를 지원하므로, key를 일렬로 늘여뜨리자!!
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(map.get(key));
		}
	}
	public static void main(String[] args) {
//		new CollectionApp().showList();
//		new CollectionApp().showSet();
		new CollectionApp().showMap();
	}
}
