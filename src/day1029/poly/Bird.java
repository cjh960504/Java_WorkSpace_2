/*
	다형성에 대해 다시 한번 공부해보자!
*/
package day1029.poly;

public class Bird {
	String name="난 새";
	String local="한국";
	public void fly() {
		System.out.println("새가 날아여~");
	}
	public static void main(String[] args) {
		//새들을 대상으로 다형성 공부하기!!
		Bird b1=new Bird();
		Bird b2=new Duck(); //b2는 bird클래스 변수, 메서드 접근
										//다형성의 특징으로서는 자식메서드를 사용가능
		Bird b3=new Sparrow();
//		Duck d=new Duck();//부모꺼내꺼
	}
}
