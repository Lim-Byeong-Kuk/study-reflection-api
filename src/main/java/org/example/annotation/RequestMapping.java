package org.example.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @RequestMapping 애노테이션 만들기
 *  Url-Path 에 해당하는 value
 *  요청 메소드 타입(GET, POST 등) 에 따라서 메소드 호출하는 method
 */
@Target({ElementType.TYPE, ElementType.METHOD}) // 타겟 : TYPE, METHOD
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value() default "";

    RequestMethod[] method() default {};
}
