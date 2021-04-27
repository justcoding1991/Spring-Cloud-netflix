package com.springcloud.learn.example.userserviceprovider.hystrix;

import ch.qos.logback.core.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

@Component
@Aspect
public class MyHystrixCommandAspect {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Pointcut(value = "@annotation(MyHystrixCommand)")
    public void pointCut(){}

    @Around(value="pointCut()&&@annotation(hystrixCommand)")
    public Object doPointCut(ProceedingJoinPoint joinPoint, MyHystrixCommand hystrixCommand) throws InterruptedException, ExecutionException, TimeoutException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int timeout = hystrixCommand.timeout();
        Future future = executorService.submit(()->{
            try{
                return joinPoint.proceed();//执行目标方法
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
            return null;
        });

        Object result;
        try{
            result = future.get(timeout, TimeUnit.MILLISECONDS);

        }catch (InterruptedException | ExecutionException | TimeoutException e){
            System.out.println("-----test pointcut------");
            e.printStackTrace();
                future.cancel(true);
            if (StringUtils.isBlank(hystrixCommand.fallback())){
                throw e;
            }
            //调用fallback
            return invokeFallBack(joinPoint,hystrixCommand.fallback());
        }

        return result;
    }

    private Object invokeFallBack(ProceedingJoinPoint joinPoint,String fallback) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();//获取代理方法的参数列表 和 方法
        System.out.println("-----------test--------------------");
        try{
            Method fallbackMethod = joinPoint.getTarget().getClass().getMethod(fallback,parameterTypes);
            fallbackMethod.setAccessible(true);

            //完成反射调用
           return  fallbackMethod.invoke(joinPoint.getTarget(),joinPoint.getArgs());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
//        return fal
    }

}
