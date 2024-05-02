package com.example.annotations;

import java.lang.reflect.Method;

public class AnnotationProcessor {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = MyClass.class;
        Method method = clazz.getMethod("testMethod");
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            System.out.println("Description: " + myAnnotation.description());
        }
    }
}