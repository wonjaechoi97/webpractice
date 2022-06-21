package aspect;

import org.aspectj.lang.Signature;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExeTimeAspect {
	
	//chap07패키지 이하의 public 메소드를pointcut 
	@Pointcut("execution(public * chap07..*(..))")
	public void publicTarget() {
		
	}
	
	//publicTarget메서드에 정의한 pointcut에 공통기능을 적용할 예정
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint)throws Throwable{
		long start = System.nanoTime(); //실행 전에 수행할 것
		try {
			Object result = joinPoint.proceed();//메서드 실행 
			return result;
		}finally {
			long finish = System.nanoTime();//메서드 후 실행할 것 
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(), Arrays.toString(joinPoint.getArgs()),
					(finish-start));
		}
	}

}
