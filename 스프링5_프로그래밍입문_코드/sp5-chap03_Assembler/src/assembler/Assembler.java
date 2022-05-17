package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler {
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	public Assembler() {
		memberDao = new MemberDao();//MemberDao를 상속받은 객체를 전달하려면 이 부분만 바꾸면 된다.
		//생성자를 통한 주입
		regSvc = new MemberRegisterService(memberDao);
		pwdSvc = new ChangePasswordService();
		//setter를 통한 주입
		pwdSvc.setMemberDao(memberDao);
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
	
	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}
	
	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}
	
}
