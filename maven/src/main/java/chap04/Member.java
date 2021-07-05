package chap04;

import java.time.LocalDateTime;

public class Member { //VO역할
	private Long id;
	private String email;
	private String password;
	private String name;
	private LocalDateTime registerDateTime;
	
	public Member(String email, String password, String name, LocalDateTime registerDateTime) { 
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = registerDateTime;
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}
	public void setRegisterDateTime(LocalDateTime registerDateTime) {
		this.registerDateTime = registerDateTime;
	}
	
	public void changePassword(String oldPassword, String newPassword) {
		if(!password.equals(oldPassword)) { //비밀번호가 틀리면 예외를 발생시키려고
			throw new WrongIdPasswordException();
		} //비밀번호가 같다면(else꼭 안 해도~)
		this.password = newPassword; 
	}
	
}
