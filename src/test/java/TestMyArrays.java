import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMyArrays {
    @RunSuite.BeforeSuite
    public void beforeTests() {
        System.out.println("start");
    }

    @RunSuite.Test(priority = 3)
    public void testMakeNewArr1() {
        int[] a = {1, 2, 3};
        int[] b = {4, 4, 1, 2, 3};
        Assertions.assertArrayEquals(a, MyArrays.makeNewArr(b));
    }

    @RunSuite.Test(priority = 4)
    public void testMakeNewArr2() {
        int[] a = {4,5};
        int[] b = {1, 4, 2, 4, 3, 4, 5};
        Assertions.assertArrayEquals(a, MyArrays.makeNewArr(b));
    }

    @RunSuite.Test(priority = 3)
    public void testMakeNewArr3() {
        int[] a = {};
        int[] b = {1, 2, 3, 4, 4};
        Assertions.assertArrayEquals(a, MyArrays.makeNewArr(b));
    }

    @RunSuite.Test(priority = 1)
    public void testMakeNewArrExc() {
        int[] b = {1, 2, 3};
        Assertions.assertThrows(RuntimeException.class, () -> MyArrays.makeNewArr(b));
    }

    @RunSuite.Test
    public void testCheckArr() {
        int[] a = {1,4,4,4,4,4};
        Assertions.assertTrue(MyArrays.checkArr(a));
    }
    @RunSuite.Test
    public void testCheckArr2() {
        int[] a = {4,4,4,4,4};
        Assertions.assertFalse(MyArrays.checkArr(a));
    }
    @RunSuite.Test
    public void testCheckArr3() {
        int[] a = {1,4,4,4,4,3};
        Assertions.assertFalse(MyArrays.checkArr(a));
    }
    @RunSuite.Test(priority = 4)
    public void testCheckArr4() {
        int[] a = {1,1,1,1,1};
        Assertions.assertFalse(MyArrays.checkArr(a));
    }
    @RunSuite.Test
    public void testCheckArr5() {
        int[] a = {};
        Assertions.assertTrue(MyArrays.checkArr(a));
    }
    @RunSuite.AfterSuite
    public void afterTests() {
        System.out.println("end");
    }
}
