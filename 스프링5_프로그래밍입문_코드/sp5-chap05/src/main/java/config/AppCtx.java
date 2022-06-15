package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.MemberSummaryPrinter;
import spring.VersionPrinter;

@Configuration
@ComponentScan(basePackages= {"spring"},
 //excludeFilters = @Filter(type=FilterType.REGEX, pattern = "spring\\..*Dao"))//특정 클래스 스캔 대상에서 제외
excludeFilters = @Filter(type=FilterType.ASPECTJ, pattern = "spring.*Dao")) //aspectj 사용해서 
public class AppCtx {
	
	
	
	
	@Bean
	@Qualifier("printer")
	public MemberPrinter memberprinter1() {
		return new MemberPrinter();
	}
	
	//@Qualifier로 한정자 설정 없으면 이름이 한정자 
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberprinter2() {
		return new MemberSummaryPrinter();
	}
	
	//기본 데이터 타입 값 설정
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
