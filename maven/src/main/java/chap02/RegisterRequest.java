package chap02;

public class RegisterRequest {//얘도 VO역할 //코멘드객체...가 뒤에 나오는데  //사용자가 전송하는 데이터들을 여기에다 한꺼번에 모아두기 위한.
	private String email;
	private String password;
	private String confirmPassword;
	private String name;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	 //회원가입할 때 비밀번호 확인 2번. 패스워드랑 컨폼패스워드랑 2개가 같이 전송될 거 그래서. 2개가 같이 넘어오면 여기서 비교를 할 거임 같은지 다른지.
	public boolean isPasswordEqualToConfirmPassword() {//패스워드랑 컨폼패스워드랑 같냐~?
		return password.equals(confirmPassword); //같으면 true 다르면 false가 리턴이 될 거야 그 리턴값을 
		//equals의 리턴값을 리턴하는 거
		
	}
	
	
	
	
	
}
