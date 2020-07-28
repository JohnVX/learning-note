package spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CleanAction {
    @Around("execution(* spring.TestAop.*(..))")
    public Object clean(ProceedingJoinPoint joinPoint) throws Throwable{

        System.out.println("开始测试前的数据清理 " + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        System.out.println("开始测试后的数据清理 " + joinPoint.getSignature());

        return result;
    }
}
