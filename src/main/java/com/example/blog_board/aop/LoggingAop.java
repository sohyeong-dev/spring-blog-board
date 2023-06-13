package com.example.blog_board.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LoggingAop {
    /* TODO
    point Cut과 Around를 사용하여 Controller의 각 API가 실행하는 시간을 로깅 출력
     */

}





/* hint
    @Pointcut("execution(* com.example.blog_board.controller..*.*(..))")
    
    @Around("Controller()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        joinPoint.proceed();
    }

 */

