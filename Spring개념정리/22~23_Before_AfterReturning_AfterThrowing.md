# Before_AfterReturning_AfterThrowing 프록시 사용하기

## 1. Before

- xml 설정
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
- cross-cutting concern 로직 구현 클래스 
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
- 결과
```txt
앞에서 실행될 로직
208ms 시간이 걸렸습니다.
total is 1.000000 
앞에서 실행될 로직
211ms 시간이 걸렸습니다.
avg is 4 
```


