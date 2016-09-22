package algorithm;

import org.junit.Test;
import parameters.Campaign;
import parameters.Problem;
import parameters.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-20
 * Time: 18:03
 * Project: knapsack-service
 */
public class KnapsackTest {
    private int inventory;
    private List<Campaign> campaigns;
    private Problem problem;

    @Test
    public void testKnapsackExhaustive() {
        sample1();

        Knapsack knapsack = new Knapsack(100000000);
        int[] optimal = knapsack.calculateKnapsack(problem);
        int[] expected = {0, 8, 0, 0, 0, 2, 1};
        assertTrue(Arrays.equals(optimal, expected));
    }

    @Test
    public void testKnapsackFractorial() {
        sample3();

        Knapsack knapsack = new Knapsack(100000000);
        int[] optimal =  knapsack.calculateKnapsack(problem);
        int[] expected = {2, 0, 666};
        assertTrue(Arrays.equals(optimal, expected));
    }

    @Test
    public void testCreateSolution() {
        sample3();

        Knapsack knapsack = new Knapsack(100000000);
        int[] optimal = {2, 0, 666};
        Solution solution = knapsack.createSolution(optimal, campaigns);
        assertEquals(13330000, solution.getMaximizedRevenue());
        assertEquals(2000000000, solution.getMaximizedImpressions());
        assertEquals(2, solution.getMaximizedCampaigns().get(0).getNumberOfCampaigns());
        assertEquals(0, solution.getMaximizedCampaigns().get(1).getNumberOfCampaigns());
        assertEquals(666, solution.getMaximizedCampaigns().get(2).getNumberOfCampaigns());
    }

    private void sample1() {
        inventory = 32356000;
        campaigns = new ArrayList<>();
        campaigns.add(new Campaign("Acme", 2000000, 200));
        campaigns.add(new Campaign("Lorem", 3500000, 400));
        campaigns.add(new Campaign("Ipsum", 2300000, 210));
        campaigns.add(new Campaign("Dolor", 8000000, 730));
        campaigns.add(new Campaign("SIT", 10000000, 1000));
        campaigns.add(new Campaign("Amet", 1500000, 160));
        campaigns.add(new Campaign("Mauris", 1000000, 100));
        problem = new Problem(inventory, campaigns);
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
