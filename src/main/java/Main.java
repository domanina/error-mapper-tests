import org.testng.TestNG;
import runners.TestitRunner;

public class Main {
    public static void main(String[] args) {

        TestNG testng = new TestNG();
        testng.setOutputDirectory("test-output");
        testng.setTestClasses(new Class[]{TestITRunner.class});
        testng.run();
    }
}