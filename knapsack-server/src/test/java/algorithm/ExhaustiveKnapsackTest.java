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
 * Time: 17:50
 * Project: knapsack-service
 */
public class ExhaustiveKnapsackTest {
    private int inventory;
    private List<Campaign> campaigns;
    private Problem problem;

    @Test
    public void testUnboundedKnapsackWithSample1() throws IOException {
        sample1();

        ExhaustiveKnapsack ubSack = new ExhaustiveKnapsack(3);
        int[] optimal = ubSack.calculateKnapsack(problem);
        int[] expected = {0, 8, 0, 0, 0, 2, 1};
        assertTrue(Arrays.equals(optimal, expected));
    }

    @Test
    public void testUnboundedKnapsackWithSample2() throws IOException {
        sample2();

        ExhaustiveKnapsack ubSack = new ExhaustiveKnapsack(3);
        int[] optimal = ubSack.calculateKnapsack(problem);
        int[] expected = {0, 10000, 0, 14, 1};

        assertTrue(Arrays.equals(optimal, expected));
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

    private void sample2() {
        inventory = 50000000;
        campaigns = new ArrayList<>();
        campaigns.add(new Campaign("Acme", 1, 0));
        campaigns.add(new Campaign("Lorem", 2, 2));
        campaigns.add(new Campaign("Ipsum", 3, 2));
        campaigns.add(new Campaign("Dolor", 70000, 71000));
        campaigns.add(new Campaign("Mauris", 49000000, 50000000));
        problem = new Problem(inventory, campaigns);
    }
}
