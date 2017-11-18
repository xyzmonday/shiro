package com.yff.anno;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    public String name() default TargetDataSource.testDataSource1;
 
    public static String testDataSource1 = "dataSource1";
 
    public static String testDataSource2 = "dataSource2";

 
}