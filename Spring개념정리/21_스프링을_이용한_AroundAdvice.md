# 스프링이 제공하는 프록시를 이용하여 AOP

> ### 서비스 로직 앞 뒤에서 실행하는 **AroundAdvice** 사용)
> - 객체 생성 조립 위한 xml, cross cutting concern 로직 구현을 위한 클래스 필요 
>#### setting.xml
```xml
<!-- 서비스 로직 구현위한 객체 생성 -->
<bean id="target" class="spring.aop.entity.NewlecExam" p:kor="1"  p:eng="1" p:math="1" p:com="1"/>

<!-- cross-cutting concern 로직 구현 클래스 알려주기  -->
<bean id="logAroundAdvice" class="spring.aop.advice.LogAroundAdvice"/>

<!-- 프록시 생성 -->
<bean id="proxy" class="org.springframework.aop.framework.ProxyFactoryBean">
	<!-- 위에서 생성한 객체 프로퍼티로 사용  -->
	<property name="target" ref="target"></property>

	<!-- cross-cutting concern 로직  프로퍼티로 사용-->
	<property name="interceptorNames">
		<list>
			<value>logAroundAdvice</value>
		</list>
	</property>
</bean>
```

>#### LogAroundAdvice.java(cross cutting concern 로직 구현 클래스)
```java
public class LogAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		
		
		//*****************************
		Object result= invocation.proceed();//서비스 로직
		//*****************************
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end =System.currentTimeMillis();
		
		String message = (end-start)+"ms 시간이 걸렸습니다.";
		System.out.println(message);
		return result;
	}

}
```

> #### 실행 
```java
	ApplicationContext context = new ClassPathXmlApplicationContext("spring/aop/setting.xml");//이곳에 지시서를 읽어라 
	//ApplicationContext context = new AnnotationConfigApplicationContext(NewlecDIConfig.class); //이곳에 지시서를 읽어라 

	Exam proxy = (Exam)context.getBean("proxy");

	System.out.printf("total is %f \n",proxy.avg());
	System.out.printf("avg is %d \n",proxy.total());
```
