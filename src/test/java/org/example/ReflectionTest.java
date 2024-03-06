package org.example;

import org.assertj.core.api.Assertions;
import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

/**
 * @Controller 애노테이션이 설정되어 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan() {
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));

        logger.debug("beans: [{}]", beans);
    }

    @Test
    void controllerAndServiceScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        logger.debug("beans: [{}]", beans);
    }

    /**
     * Reflection 의 또 다른 활용 방법
     * 클래스에 대한 필드, 생성자, 메소드 확인
     */
    @Test
    void showClass() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    /**
     * 힙 영역에 로드되어 있는 클래스 타입의 객체를 가지고오는 방법 3가지
     */
    @Test
    void load() throws ClassNotFoundException {
        // 첫번째 방법
        Class<User> clazz = User.class;

        // 두번째 방법
        User user = new User("serverwizard", "임병국");
        Class<? extends User> clazz2 = user.getClass();

        // 세번째 방법
        Class<?> clazz3 = Class.forName("org.example.model.User");

        logger.debug("clazz : [{}]", clazz);
        logger.debug("clazz : [{}]", clazz2);
        logger.debug("clazz : [{}]", clazz3);

        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz == clazz3).isTrue();

    }

    private Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }


}
