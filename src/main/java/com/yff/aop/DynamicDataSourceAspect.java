package com.yff.aop;

import com.yff.multids.DataSourceHolder;
import com.yff.anno.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class DynamicDataSourceAspect {


    @Pointcut("execution(* com.yff.service.*.*(..))")
    public void dynamicDataSource() {

    }
    //&&表示切入点表达是必须满足一下两个条件才进行AOP
    @Before(value = "dynamicDataSource() &&  @annotation(dataSource)")
    public void before(JoinPoint point, TargetDataSource dataSource) throws Throwable {
        System.out.println("DynamicDataSourceAspect == before");
        System.out.println("dataSource.name() = " + dataSource.name());
        DataSourceHolder.setDataSourceType(dataSource.name());
    }

    @After(value = "dynamicDataSource() && @annotation(dataSource)")
    public void after(JoinPoint point, TargetDataSource dataSource) {
        System.out.println("DynamicDataSourceAspect == after");
        DataSourceHolder.clearDataSourceType();
    }

}
