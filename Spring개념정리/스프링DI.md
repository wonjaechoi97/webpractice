# 스프링에서 DI하는 방법


## 1. xml에서 객체 생성하는 방법 

### spring.di.entity위치의 NewlecExam 타입의 객체를 생성 setter를 통해서 혹은 생성자를 통해서 값을 설정할 수 있다.
```xml
<bean  id="exam" class="spring.di.entity.NewlecExam">
    <!-- setter를 통한 값 설정 -->
     <!-- <property name="kor" value="10"/>
		<property name="eng" value="10"/>
		<property name="math" value="10"/>
		<property name="com" value="10"/> -->
		
		<!-- 생성자를 통한 값 설정 -->
		<!-- <constructor-arg name="kor" type="int" value="10"/>
		<constructor-arg name="math" value="20"/>
		<constructor-arg name="com" value="30"/>
		<constructor-arg name="eng" value="40"/>-->
</bean>

```

### 위의 exam을 필요로 하는 객체 생성
```xml
<bean id = "console" class="spring.di.ui.InlineExamConsole" >
		<!-- console.setExam(exam); -->
		<!-- SetExam ->Exam->exam -->
		<property name="exam" ref="exam"/>
</bean>
```

### collection 생성하기

```xml
<bean name="exams" class="java.util.ArrayList">
		<constructor-arg>
			<list>
			속성으로 넣어주기
				<bean id="exam" class="spring.di.entity.NewlecExam" p:kor="1"  p:eng="1" p:math="1" p:com="1"/>
			위에서 생성한 것 가져오기
				<ref bean ="exam"/>
			</list>
		</constructor-arg>
</bean>


<!-- 더 간단히     namespace util 추가 필요-->
	
<util:list id="exams" list-class="java.util.ArrayList">
			속성으로 넣어주기
				<bean id="exam" class="spring.di.entity.NewlecExam" p:kor="1"  p:eng="1" p:math="1" p:com="1"/>
			위에서 생성한 것 가져오기
				<ref bean ="exam"/> 
</util:list> 
```
