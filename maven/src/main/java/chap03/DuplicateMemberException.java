package chap03;

public class DuplicateMemberException extends RuntimeException { //회원정보가 중복될 경우의 Exception
	public DuplicateMemberException(String message) {
		super(message); //메시지 하나를 받아서 부모에다가 
	}
	

}
