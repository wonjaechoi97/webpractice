package aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CacheAspect {
	private Map<Long, Object> cache = new HashMap<>();
	
	@Pointcut("execution(public * chap07..*(long))" )
	public void cacheTarget(){
		
	}
	
	//@Around("aspect.ExeTimeAspect.publicTarget()") <=위에 것 생략하고 다른 클래스의 포인트 컷 재사용 가능
	//여기서는 ExeTimeAspect의 publicTarget()에 정의된 pointcut사용
	//별도의 클래스에 포인트 컷만 정의해 놓으면 사용하기 편하다
	@Around("cacheTarget()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		Long num = (Long)joinPoint.getArgs()[0];
		if(cache.containsKey(num)) {
			System.out.printf("CacheAspect: Cache에서 구함[%d]\n", num);
			return cache.get(num);
		}
		Object result = joinPoint.proceed();
		cache.put(num,result);
		System.out.printf("CacheAspect: Cache에 추가[%d]\n", num);
		return result;
	}
	
}
