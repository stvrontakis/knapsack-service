package health;

import algorithm.Knapsack;
import com.codahale.metrics.health.HealthCheck;
import parameters.Campaign;
import parameters.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-21
 * Time: 14:58
 * Project: knapsack-service
 */
public class KnapsackHealthCheck extends HealthCheck {
    private int inventory;
    private List<Campaign> campaigns;
    private Problem problem;

    @Override
    protected Result check() throws Exception {
        sample1();

        Knapsack knapsack = new Knapsack(3, 100000000);
        int[] optimal = knapsack.calculateKnapsack(problem);
        int[] expected = {0, 8, 0, 0, 0, 2, 1};
        if(Arrays.equals(optimal, expected)) {
            return Result.healthy();
        } else {
            return Result.unhealthy("Problem with knapsack algorithm");
        }
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

}
