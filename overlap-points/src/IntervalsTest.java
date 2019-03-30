import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class IntervalsTest {

    Intervals test;

    @Before
    public void init(){
        test = new Intervals();
    }

    @org.junit.Test
    public void intervalInsertAndFindPOM() {
        test = new Intervals();
        test.intervalInsert(0, 3);
        test.intervalInsert(1, 4);
        test.intervalInsert(1, 4);
        test.intervalInsert(2, 2);
        test.intervalInsert(-1, 5);
        test.intervalInsert(3, -7);
        assertEquals(6, test.findPOM());
    }

    @org.junit.Test
    public void intervalDelete() throws Exception {
        test = new Intervals();
        test.intervalInsert(0, 3);
        test.intervalInsert(1, 4);
        test.intervalInsert(1, 4);
        test.intervalInsert(2, 2);
        test.intervalInsert(-1, 5);
        test.intervalInsert(3, -7);
        assertEquals(false, test.intervalDelete(1));
    }

    @org.junit.Test
    public void getRBTree() throws Exception {
        test = new Intervals();
        test.intervalInsert(0, 3);
        test.intervalInsert(1, 4);
        test.intervalInsert(1, 4);
        test.intervalInsert(2, 2);
        test.intervalInsert(-1, 5);
        test.intervalInsert(3, -7);
        test.getRBTree();
    }

}
