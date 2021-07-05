package aop;

import chap05.Calculator;
import chap05.ExeCalculator;
import chap05.ImplCalculator;

public class MainCalculator {

	public static void main(String[] args) {
		Calculator cal = new ExeCalculator(new ImplCalculator());
		//long start = System.currentTimeMillis();
		long r = cal.factorial(10);
		//long end = System.currentTimeMillis();
		//System.out.println("for문 : "+(end-start));
		
		System.out.println(r);
		System.out.println(cal.getClass().getName());
	}

}
