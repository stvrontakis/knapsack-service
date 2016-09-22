package algorithm;

import org.junit.Test;
import parameters.Campaign;
import parameters.Problem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-20
 * Time: 17:52
 * Project: knapsack-service
 */
public class FractorialKnapsackTest {
    private int inventory;
    private List<Campaign> campaigns;
    private Problem problem;

    @Test
    public void testUnboundedKnapsackWithSample3() throws IOException {
        sample3();

        FractorialKnapsack ubSack = new FractorialKnapsack();
        int[] optimal =  ubSack.calculateKnapsack(problem);
        int[] expected = {2, 0, 666};
        assertTrue(Arrays.equals(optimal, expected));
    }

    private void sample3() {
        inventory = 2000000000;
        campaigns = new ArrayList<>();
        campaigns.add(new Campaign("Acme", 1000000, 5000));
        campaigns.add(new Campaign("Lorem", 2000000, 9000));
        campaigns.add(new Campaign("Ipsum", 3000000, 20000));
        problem = new Problem(inventory, campaigns);
    }
}
