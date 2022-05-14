# Dependency Injection

## 스프링 프레임 워크의 코어 기능
> - **Dependency Injection**
> - Inversion of Control

## 종속성이란?
> ![hasa부품](https://user-images.githubusercontent.com/62707891/163686634-ab38ca07-b07c-427a-ae3b-52fc66234b36.png)
> - 프로그램은 여러 객체들의 조립으로 이루어진다.
> - 위와 같은 경우 A클래스가 B 타입의 객체를 만들어 사용하기 때문에 B가 A의 Dependency이라고 한다.
> - 쉽게 표현하면 B가 A의 부품으로 볼 수 있다.
> - 위 이미지의 좌측의 방식은 A가 직접 객체를 생성해서 사용하는 방법(일체형)이고, **우측은 A가 외부에서 미리 생성한 객체를 setting해서 사용하는 방법(조립형)이다.**
> - 부품을 갈아 끼우는 경우가 많다면 조립형으로 만들어 의존성을 낮추고 갈아끼우는 조립형을 선택하는 것이 유리하다. 
## Dependency Injection란?
> - 생성한 dependency 객체(부품)를 주입하는 것을 Dependency Injection이라고 한다.
> - 종속성 주입 보다는 부품 조립으로 보는 것이 좋다.


## Dependecy Injection의 두 가지 방법
> - ### Setter Injection 
> ```java
> B b = new B(); // dependency 객체 생성 
> A a = new A();
>
> a.set(B); //injection 
> ```
> - ### Construction Injection 
> ```java
> B b = new B(); // dependency 객체 생성 
> A a = new A(b);
> ```

## 스프링과 Dependency Injection
> - 스프링은 부품을 생성하고 조립해주는 역할을 대신해준다.
> - 우리가 원하는 부품과 어떻게 결합하기 원하는지 설정만 해주면 스프링이 해준다.
