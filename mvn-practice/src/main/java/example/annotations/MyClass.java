package com.example.annotations;

public class MyClass {
    @MyAnnotation(description = "This is a test method")
    public void testMethod() {
        System.out.println("Executing testMethod");
    }
}