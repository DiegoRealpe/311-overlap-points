import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Test cases for every mothod that is testable.
 * @author Diego Realpe, diegort
 * @author Adrian Hamill, adrianh
 */
@RunWith(JUnit4.class)
public class IntervalsTest {

    Intervals test;

    @Before
    public void init(){
        test = new Intervals();
    }

    @org.junit.Test
    public void intervalInsert() {
        /**
         *      -9  -8  -7  -6  -5  -4  -3  -2  -1   0   1   2   3   4   5   6   7   8   9   10
         *      |----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
         *                                           e1----------e2
         *                                               e3----------e4
         *                                               e5----------e6
         *                                                  e7e8
         *                                       e9----------------------e10
         *               e11-------------------------------------e12
         */
        test = new Intervals();
        test.intervalInsert(0, 3);
        test.intervalInsert(1, 4);
        test.intervalInsert(1, 4);
        test.intervalInsert(2, 2);
        test.intervalInsert(-1, 5);
        test.intervalInsert(3, -7);
    }

    @org.junit.Test
    public void findPOM() throws Exception {
        /**
         *      1   2   3   4   5   6   7   8   9   10  11
         *      |---|---|---|---|---|---|---|---|---|---|
         *      e1--------------------------------------e2
         *          e3------------------------------e4
         *              e5----------------------e6
         *                  e7--------------e8
         *                      e9------e10
         */
        test = new Intervals();
        test.intervalInsert(1, 11);
        test.intervalInsert(2, 10);
        test.intervalInsert(3, 9);
        test.intervalInsert(4, 8);
        test.intervalInsert(5, 7);
        assertEquals(5, test.findPOM());

        /**
         *     -8  -7  -6  -5  -4  -3  -2  -1   0   1   2   3   4   5   6   7   8   9   10
         *      |---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
         *                                      e1----------e2
         *                                          e3----------e4
         *                                          e5----------e6
         *                                             e7e8
         *                                  e9----------------------e10
         *          e11-------------------------------------e12
         */
        test = new Intervals();
        test.intervalInsert(0, 3);
        test.intervalInsert(1, 4);
        test.intervalInsert(1, 4);
        test.intervalInsert(2, 2);
        test.intervalInsert(-1, 5);
        test.intervalInsert(3, -7);
        assertEquals(6, test.findPOM());

        /**
         *     -1   0   1   2   3   4   5   6   7   8   9   10
         *      |---|---|---|---|---|---|---|---|---|---|---|
         *          e1----------e2
         *              e3----------e4
         *              e5----------e6
         *                e7e8
         */
        test = new Intervals();
        test.intervalInsert(0, 3);
        test.intervalInsert(1, 4);
        test.intervalInsert(1, 4);
        test.intervalInsert(2, 2);
        assertEquals(4, test.findPOM());

        /**
         *     -1   0   1   2   3   4   5   6   7   8   9   10
         *      |---|---|---|---|---|---|---|---|---|---|---|
         *          e1----------e2
         *              e3----------e4
         *              e5----------e6
         */
        test = new Intervals();
        test.intervalInsert(0, 3);
        test.intervalInsert(1, 4);
        test.intervalInsert(1, 4);
        assertEquals(3, test.findPOM());

        /**
         *     -1   0   1   2   3   4   5   6   7   8   9   10
         *      |---|---|---|---|---|---|---|---|---|---|---|
         *          e1------------------------------e2
         *              e3--e4
         *                          e5----------e6
         */
        test = new Intervals();
        test.intervalInsert(0, 8);
        test.intervalInsert(1, 2);
        test.intervalInsert(4, 7);
        assertEquals(2, test.findPOM());


        test = new Intervals();
        assertEquals(0, test.findPOM());
    }

    @org.junit.Test
    public void intervalDelete() throws Exception {
        /**
         *     -8  -7  -6  -5  -4  -3  -2  -1   0   1   2   3   4   5   6   7   8   9   10
         *      |---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
         *                                      e1----------e2
         *                                          e3----------e4
         *                                          e5----------e6
         *                                             e7e8
         *                                  e9----------------------e10
         *          e11-------------------------------------e12
         */
        test = new Intervals();
        test.intervalInsert(0, 3);
        test.intervalInsert(1, 4);
        test.intervalInsert(1, 4);
        test.intervalInsert(2, 2);
        test.intervalInsert(-1, 5);
        test.intervalInsert(3, -7);
        
        assertEquals(1, test.getRBTree().getRoot().getEmax().getDir()); //startpoint
        assertEquals(2, test.getRBTree().getRoot().getEmax().getValue()); //Key == 2
        
        
        assertEquals(false, test.intervalDelete(1));
    }

    @org.junit.Test
    public void getRBTree() throws Exception {
        /**
         *      -9  -8  -7  -6  -5  -4  -3  -2  -1   0   1   2   3   4   5   6   7   8   9   10
         *      |----|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
         *                                           e1----------e2
         *                                               e3----------e4
         *                                               e5----------e6
         *                                                  e7e8
         *                                       e9----------------------e10
         *               e11-------------------------------------e12
         */
        test = new Intervals();
        test.intervalInsert(0, 3);
        test.intervalInsert(1, 4);
        test.intervalInsert(1, 4);
        test.intervalInsert(2, 2);
        test.intervalInsert(-1, 5);
        test.intervalInsert(3, -7);
        test.getRBTree();
    }

    @org.junit.Test
    public void getHeight(){
        /**
         *      1   2   3   4   5   6   7   8   9   10  11
         *      |---|---|---|---|---|---|---|---|---|---|
         *      e1--------------------------------------e2
         *          e3------------------------------e4
         *              e5----------------------e6
         *                  e7--------------e8
         *                      e9------e10
         */
        test = new Intervals();
        test.intervalInsert(1, 11);
        test.intervalInsert(2, 10);
        test.intervalInsert(3, 9);
        test.intervalInsert(4, 8);
        test.intervalInsert(5, 7);
        assertEquals(5, test.getRBTree().getHeight());
    }

    @org.junit.Test
    public void getSize(){
        /**
         *      1   2   3   4   5   6   7   8   9   10  11
         *      |---|---|---|---|---|---|---|---|---|---|
         *      e1--------------------------------------e2
         *          e3------------------------------e4
         *              e5----------------------e6
         *                  e7--------------e8
         *                      e9------e10
         */
        test = new Intervals();
        test.intervalInsert(1, 11);
        test.intervalInsert(2, 10);
        test.intervalInsert(3, 9);
        test.intervalInsert(4, 8);
        test.intervalInsert(5, 7);
        assertEquals(10, test.getRBTree().getSize());
    }

    @org.junit.Test
    public void getEmax(){
        /**
         *      1   2   3   4   5   6   7   8   9   10  11
         *      |---|---|---|---|---|---|---|---|---|---|
         *      e1--------------------------------------e2
         *          e3------------------------------e4
         */
        test = new Intervals();
        test.intervalInsert(1, 11);
        test.intervalInsert(2, 10);
        assertEquals(test.getRBTree().getRoot().point, test.getRBTree().getRoot().getEmax());
    }

    @org.junit.Test
    public void getEndpoint(){
        /**
         *      1   2   3   4   5   6   7   8   9   10  11
         *      |---|---|---|---|---|---|---|---|---|---|
         *      e1--------------------------------------e2
         *          e3------------------------------e4
         */
        test = new Intervals();
        test.intervalInsert(1, 11);
        test.intervalInsert(2, 10);
        assertEquals(test.getRBTree().getRoot().point, test.getRBTree().getRoot().getEndpoint());
        assertEquals(test.getRBTree().getRoot().right.point, test.getRBTree().getRoot().right.getEndpoint());
        assertEquals(test.getRBTree().getRoot().left.point, test.getRBTree().getRoot().left.getEndpoint());
    }

    @org.junit.Test
    public void getColor(){
        /**
         *      1   2   3   4   5   6   7   8   9   10  11
         *      |---|---|---|---|---|---|---|---|---|---|
         *      e1--------------------------------------e2
         *          e3------------------------------e4
         *                      e5------e6
         *              e7----------e8
         */
        test = new Intervals();
        test.intervalInsert(1, 11);
        test.intervalInsert(2, 10);
        test.intervalInsert(5, 7);
        test.intervalInsert(3, 6);
        assertEquals(1, test.getRBTree().getRoot().getColor());
        assertEquals(0, test.getRBTree().getRoot().left.getColor());
        assertEquals(0, test.getRBTree().getRoot().right.getColor());
    }

    @org.junit.Test
    public void getP(){
        /**
         *      1   2   3   4   5   6   7   8   9   10  11
         *      |---|---|---|---|---|---|---|---|---|---|
         *      e1--------------------------------------e2
         *          e3------------------------------e4
         */
        test = new Intervals();
        test.intervalInsert(1, 11);
        test.intervalInsert(2, 10);
        assertEquals(1, test.getRBTree().getRoot().getP());
        assertEquals(-1, test.getRBTree().getRoot().right.getP());
    }
}
