import edu.t1.TestRunner;
import org.junit.jupiter.api.Test;
import tests.TestClassWithAnnotationOnNotStaticMethod;
import tests.TestClassWithMultipleAnnotatedMethods;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    public void shouldThrowExceptionWhenMultipleAnnotatedMethods() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TestRunner.runTests(TestClassWithMultipleAnnotatedMethods.class);
        });

        String expectedMessage = "Аннотация BeforeSuite использована больше чем один раз";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWhenAnnotatedNotStaticMethod() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TestRunner.runTests(TestClassWithAnnotationOnNotStaticMethod.class);
        });

        String expectedMessage = "Метод beforeSuite должен быть статичным";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}