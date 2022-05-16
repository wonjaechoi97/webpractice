# Before_AfterReturning_AfterThrowing 프록시 사용하기

## 1. Before
- 서비스 로직 실행 전에만 끼워 넣는 방식

- ### xml 설정
``` xml
<bean id="logBeforeAdvice" class="spring.aop.advice.LogBeforeAdvice"/>

<!-- 프록시 생성 -->
<bean id="proxy" class="org.springframework.aop.framework.ProxyFactoryBean">
  <!-- 위에서 생성한 객체 프로퍼티로 사용  -->
  <property name="target" ref="target"></property>

  <!-- cross-cutting concern 로직  프로퍼티로 사용-->
  <property name="interceptorNames">
    <list>
      <value>logAroundAdvice</value>
      <value>logBeforeAdvice</value>
    </list>
  </property>
</bean>
```
- ### cross-cutting concern 로직 구현 클래스 
``` java
import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class LogBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("앞에서 실행될 로직");
		
	}
}
```
- ### 결과
```txt
앞에서 실행될 로직
208ms 시간이 걸렸습니다.
total is 1.000000 
앞에서 실행될 로직
211ms 시간이 걸렸습니다.
avg is 4 
```

## 2. AfterReturning 
- 서비스 로직을 성공적으로 수행한 후 실행되는 방식
- ### xml 설정

```xml
<bean id="logAfterReturningAdvice" class="spring.aop.advice.LogAfterReturningAdvice"/>

<bean id="proxy" class="org.springframework.aop.framework.ProxyFactoryBean">
	<!-- 위에서 생성한 객체 프로퍼티로 사용  -->
	<property name="target" ref="target"></property>

	<!-- cross-cutting concern 로직  프로퍼티로 사용-->
	<property name="interceptorNames">
		<list>
			<value>logAroundAdvice</value>
			<value>logBeforeAdvice</value> 
			<value>logAfterReturningAdvice</value>
		</list>
	</property>
</bean>
```
- ### cross-cutting concern 로직 구현 클래스
```java
import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class LogAfterReturningAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("returnValue:"+returnValue+",method:" +method.getName());
	}

}
```
- ### 결과
- 메서드 반환 값과 메서드 명을 출력한다.
```txt
returnValue:1.0,method:avg****
208ms 시간이 걸렸습니다.
total is 1.000000 
앞에서 실행될 로직
returnValue:4,method:total****
208ms 시간이 걸렸습니다.
avg is 4 
```

## 3. AfterThrowing
- 서비스 로직에서 예외가 발생하고 실행되는 방식
- ### xml 설정

```xml
...
<bean id="logAfterReturningAdvice" class="spring.aop.advice.LogAfterReturningAdvice"/>
<bean id="logAfterThrowingAdvice" class="spring.aop.advice.LogAfterThrowingAdvice"/>

<bean id="proxy" class="org.springframework.aop.framework.ProxyFactoryBean">
	<!-- 위에서 생성한 객체 프로퍼티로 사용  -->
	<property name="target" ref="target"></property>

	<!-- cross-cutting concern 로직  프로퍼티로 사용-->
	<property name="interceptorNames">
		<list>
			<value>logAroundAdvice</value>
			<value>logBeforeAdvice</value> 
			<value>logAfterReturningAdvice</value>
			<value>logAfterThrowingAdvice</value>
		</list>
	</property>
</bean>
```
- ### cross-cutting concern 로직 구현 클래스
```java
import org.springframework.aop.ThrowsAdvice;

public class LogAfterThrowingAdvice implements ThrowsAdvice{
	public void afterThrowing(IllegalArgumentException e) throws Throwable{
		System.out.println("예외가 발생하였습니다.: "+e.getMessage());
	}
}
```
- ### 결과
- 예외 메세지를 출력한다.
```txt
앞에서 실행될 로직
Exception in thread "main" 예외가 발생하였습니다.: 유효하지 않은 국어점수
java.lang.IllegalArgumentException: 유효하지 않은 국어점수
```


