package spring;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberPrinter {
	//@Autowired(required = false)
	private DateTimeFormatter dateTimeFormatter;
	
	/*@Autowired
	 * private Optional<DateTimeFormatter> dateTimeFormatter;
	 */

	public void print(Member member) {
		if (dateTimeFormatter == null) {
			System.out.printf("회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF \n", member.getId(), member.getEamil(),
					member.getName(), member.getRegisterDateTime());
		} else {
			System.out.printf("회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s \n", member.getId(), member.getEamil(),
					member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
	
	@Autowired(required = false)//자동 주입 필수 아님
	public void setDateTimeFormatter(/*@Nullable*/DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
}
