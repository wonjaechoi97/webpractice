# 스프링 프레임워크 강의 16강 - 특화된 @Component 어노테이션 (@Controller/@Service/@Repository)

## 초기값 설정하는 방법
- @Value를 사용하여 초기값 설정
``` java
@Component
public class NewlecExam implements Exam {
	
	@Value("20")
	private int kor;
	private int eng;
	private int math;
	private int com;
	public NewlecExam() {
		// TODO Auto-generated constructor stub
	}
	
```

## 특화된 @Component 어노테이션
- 컴포넌트는 mvc 방식으로 웹 어플리케이션을 만들 경우 mvc를 구성하며 업무용 로직을 가진 자바 코드들을 컴포넌트라고 한다.
- 컴포넌트라고 하는 일반적인 것이 아닌 조금 더 의미를 지닌 어노테이션이 @Controller/@Service/@Repository이다. 
- 역할의 의미가 부여된다. 
