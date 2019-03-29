import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class IntervalsTest {

    Intervals test, test2, test3;

    @Before
    public void init(){
        test = new Intervals();
    }

    @org.junit.Test
    public void intervalInsert() throws Exception {
        test.intervalInsert(0, 3);
        test.intervalInsert(1, 4);
        test.intervalInsert(1, 4);
        test.intervalInsert(2, 2);
        test.intervalInsert(-1, 5);
        test.intervalInsert(3, -7);
    }

    @org.junit.Test
    public void intervalDelete() throws Exception {
    }

    @org.junit.Test
    public void findPOM() throws Exception {
    }

    @org.junit.Test
    public void getRBTree() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Intervals.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
