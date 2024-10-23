package tests;

import edu.t1.annotation.BeforeSuite;

public class TestClassWithAnnotationOnNotStaticMethod {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite on not static method");
    }
}
