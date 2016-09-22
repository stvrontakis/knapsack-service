package endpoint;

import configuration.KnapsackConfiguration;
import org.junit.Test;
import parameters.Campaign;
import parameters.Problem;
import parameters.Solution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-20
 * Time: 22:52
 * Project: knapsack-service
 */
public class KnapsackEndpointTest {
    private int inventory;
    private List<Campaign> campaigns;
    private Problem problem;


    @Test
    public void testCalculateKnapsack() throws IOException {
        sample3();
        KnapsackConfiguration knapsackConfiguration = new KnapsackConfiguration();
        knapsackConfiguration.setOptimizeDp(3);
        knapsackConfiguration.setLargeProblemSet(1000000);
        KnapsackEndpoint endpoint = new KnapsackEndpoint(knapsackConfiguration);
        Solution solution = endpoint.calculate(problem);
        assertEquals(13330000, solution.getMaximizedRevenue());
        assertEquals(2000000000, solution.getMaximizedImpressions());
        assertEquals(2, solution.getMaximizedCampaigns().get(0).getNumberOfCampaigns());
        assertEquals(0, solution.getMaximizedCampaigns().get(1).getNumberOfCampaigns());
        assertEquals(666, solution.getMaximizedCampaigns().get(2).getNumberOfCampaigns());
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
