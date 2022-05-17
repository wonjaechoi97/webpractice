package spring;

import java.time.LocalDateTime;

public class Member {
	private Long id;
	private String eamil;
	private String password;
	private String name;
	private LocalDateTime registerDateTime;
	
	
	
	public Member( String eamil, String password, String name, LocalDateTime registerDateTime) {
		this.eamil = eamil;
		this.password = password;
		this.name = name;
		this.registerDateTime = registerDateTime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEamil() {
		return eamil;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}
	
	public void changePassword(String oldPassword, String newPassword) {
		if(!password.equals(oldPassword)) {
			throw new WrongPasswordException();
		}
		this.password = newPassword;
	}
	
}
