package api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parameters.Problem;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-20
 * Time: 18:59
 * Project: knapsack-service
 */

@RunWith(Parameterized.class)
public class KnapsackServiceTest {
    public KnapsackService service;

    public KnapsackServiceTest(KnapsackService service) {
        this.service = service;
    }

    @Test
    public void testKnapsackService() throws IOException {
        Problem problem = new Problem(0, null);
        assertEquals(null, service.getSolution(problem));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
                new Object[]{new KnapsackServiceImpl()},
                new Object[]{new KnapsackServiceImpl2()});
    }
}
