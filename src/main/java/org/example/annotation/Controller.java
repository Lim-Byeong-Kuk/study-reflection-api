package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Controller 애노테이션 만들기
 */

@Target({ElementType.TYPE})         // 대상은 TYPE (클래스, 인터페이스, Enum 등)
@Retention(RetentionPolicy.RUNTIME) // 유지기간 : 런타임기간 까지
public @interface Controller {

}
