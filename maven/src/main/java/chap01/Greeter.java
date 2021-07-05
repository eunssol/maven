package chap01;

public class Greeter {
	private String format;
	
	public String greet(String guest) {
		return String.format(format, guest);  
	}
	
	public void setFormat(String format) { //포맷을 먼저 셋을 하고 그 포맷을 통해서 포멧팅. 필드에다가 값을 대입한 다음에 greet을 호출해야하니까..?
		this.format = format; 
	}
}
