import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest extends Assert {

    private LinkedListRealization<String> testArr = new LinkedListRealization<>();
    private LinkedListRealization<Integer> testArrInteget = new LinkedListRealization<>();

    @Before
    public void initList() {
        testArr.add("One");
        testArr.add("Two");
        testArr.add("Three");
        testArr.add("Four");
        testArr.add("Five");
        testArr.add("Six");
        testArr.add("seven");
        testArr.add("Eight");
    }

    @Test
    public void getTest() {
        testArr.get(0);
        testArr.get(5);
        testArr.get(20);
        testArr.get(-20);
        testArr.get(999999);
    }

    @Test
    public void addTest() {
        testArr.add("Nine");
        testArr.add("Ten");
        testArr.get(8);
        testArr.get(9);
    }

    @Test
    public void clearTest() {
        testArr.clear();
        testArr.getSize();
    }

    @Test
    public void addArrToList() {
        Integer[] intNumb = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        testArrInteget.addAll(intNumb);
        testArrInteget.getSize();
        testArrInteget.get(0);
        testArrInteget.get(99999);
        testArrInteget.get(12);
    }

    @After
    public void clearList() {
        testArr.clear();
    }

}
