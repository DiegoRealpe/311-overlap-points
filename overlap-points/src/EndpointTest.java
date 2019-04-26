import org.junit.Test;

import static org.junit.Assert.*;

public class EndpointTest {

    @Test
    public void testGetValue() {
        Endpoint endpoint = new Endpoint(4, 1, -1);
        assertEquals(4, endpoint.getValue());
    }

}