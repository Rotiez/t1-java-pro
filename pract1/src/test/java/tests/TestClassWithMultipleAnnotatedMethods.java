package tests;

import edu.t1.annotation.*;

public class TestClassWithMultipleAnnotatedMethods {
    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("beforeSuite method");
    }

    @BeforeSuite
    public static void anotherBeforeSuite() {
        System.out.println("another beforeSuite method");
    }
}