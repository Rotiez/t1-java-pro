package edu.t1;

import edu.t1.annotation.*;

public class TestClass {
    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("BeforeSuite method");
    }

    @AfterSuite
    public static void afterSuite() {
        System.out.println("AfterSuite method");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("beforeTest method");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("afterTest method");
    }

    @Test(priority = 11)
    public void test1() {
        System.out.println("priority 8");
    }

    @Test(priority = 2)
    public void test2() {
        System.out.println("priority 2");
    }

    @Test
    public void test3() {
        System.out.println("priority default (5)");
    }

    @CsvSource("10, Java, 20, true")
    @Test(priority = 1)
    public void testMethod(int a, String b, int c, boolean d) {
        System.out.println("priority 1 - TestCsv: " + a + ", " + b + ", " + c + ", " + d);
    }
}
