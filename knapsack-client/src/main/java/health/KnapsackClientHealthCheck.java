package health;

import client.KnapsackClient;
import com.codahale.metrics.health.HealthCheck;
import configuration.KnapsackClientConfiguration;
import parameters.Campaign;
import parameters.Problem;
import parameters.Solution;

import javax.ws.rs.client.Client;
import java.util.ArrayList;
import java.util.List;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-21
 * Time: 15:22
 * Project: knapsack-service
 */
public class KnapsackClientHealthCheck extends HealthCheck {
    private KnapsackClientConfiguration config;
    private int inventory;
    private List<Campaign> campaigns;
    private Problem problem;
    private Client jerseyClient;

    public KnapsackClientHealthCheck(Client jerseyClient, KnapsackClientConfiguration config) {
        this.jerseyClient = jerseyClient;
        this.config = config;
    }

    @Override
    protected Result check() {
        sampleProblem();
        KnapsackClient client = new KnapsackClient(config.getServerHostname(), jerseyClient);
        try {
            Solution solution = client.getSolution(problem);
            if(solution.getMaximizedRevenue() != 10950) {
                return Result.unhealthy("Server sends wrong results. What happened???");
            }
        } catch (Exception e) {
            return Result.unhealthy("Something happened while calling server\n"
                    + e.getMessage() + "\n" + e.getCause());
        }
        return Result.healthy();
    }


    private void sampleProblem() {
        inventory = 15;
        campaigns = new ArrayList<>();
        campaigns.add(new Campaign("Acme", 2, 200));
        campaigns.add(new Campaign("Lorem", 5, 400));
        campaigns.add(new Campaign("Ipsum", 5, 210));
        campaigns.add(new Campaign("Dolor", 1, 730));
        problem = new Problem(inventory, campaigns);
    }
}
