package ex15usefulclass;

import java.util.Random;

import ex01start.E02SystemOutPrintln;

public class E06Random {

	public static void main(String[] args) {
		
		/*
		컴퓨터는 인자로 전달되는 seed(씨앗)을 기반으로 난수를 생성한다.
		만약 seed값읻 동일하다면 항상 같은 패턴의 난수만 생성하게 되는데
		이를 가짜난수(Pseudo- random number) 라고 한다.
		 */
		System.out.println("### 난수생성1: seed로 32를 사용");
		Random random1 = new Random(32);
		for(int i = 0; i<10; i++) {
			/*
			nextInt()의 인자값 100이 전달되면 0~99사이의 난수가 생성된다.
			 */
			System.out.println(random1.nextInt(100));
		}
		
		System.out.println("### 난수생성2: seed로 없음");
		//정상적인 난수가 생성된다.
		Random random2 = new Random();
		for(int i = 0; i<10; i++) {
			System.out.println(random2.nextInt(100));
		}
		
		System.out.println("### 난수생성3 : 항상 다른 seed를 사용");
		Random random3 = new Random();
		//현재 시간을 밀리 초 단위로 발환하는 메소드를 통해 seed를 설정한다.
		System.out.println(System.currentTimeMillis());
		random3.setSeed(System.currentTimeMillis());
		for(int i =0; i<10; i++) {
			System.out.println(random3.nextInt(100));
		}
		
		System.out.println("### 난수생성4 : 항상 다른 seed를 사용, nextInt() 인자없음");
		Random random4 = new Random();
		//현재 시간을 밀리 초 단위로 반환하는 메소드
		random4.setSeed(System.currentTimeMillis());
		for (int i =0; i<10; i++) {
			/*
			nextInt()에 인자값이 없는 경우에는 음수, 양수가 혼용되어
			난수가 생성된다.
			 */
			System.out.println(random4.nextInt());
		}

	}

}
