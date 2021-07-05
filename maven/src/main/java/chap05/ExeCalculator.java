package chap05;

public class ExeCalculator implements Calculator {
	private Calculator delegrate;
	
	public ExeCalculator(Calculator delegrate) {
		this.delegrate = delegrate;
	}
	@Override
	public long factorial(long num) {
		long start = System.nanoTime();
		
		long result = delegrate.factorial(num);
		
		long end = System.nanoTime();
		System.out.println("Exe : " + (end-start));
		return result;
		
	}

}
 //이 클래스를 자동화하기 위해서 프록시 만들었음. 
