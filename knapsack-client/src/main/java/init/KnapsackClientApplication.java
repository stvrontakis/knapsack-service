package init;

import configuration.KnapsackClientConfiguration;
import health.KnapsackClientHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parameters.Campaign;
import parameters.Problem;
import resources.ClientBuilder;
import resources.KnapsackClientInfoResource;
import resources.KnapsackClientResource;

import java.util.ArrayList;
import java.util.List;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-20
 * Time: 19:51
 * Project: knapsack-service
 */
public class KnapsackClientApplication extends Application<KnapsackClientConfiguration> {
    private static final Logger logger = LoggerFactory.getLogger(KnapsackClientApplication.class);

    public static void main(String[] args) throws Exception {
        new KnapsackClientApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<KnapsackClientConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/clientpage", "index.html"));
    }

    @Override
    public void run(KnapsackClientConfiguration configuration, Environment environment) throws Exception {
        logger.info("Knapsack client spinning up");
        setupResources(configuration, environment);
        registerHealthChecks(configuration, environment);
    }

    private void setupResources(KnapsackClientConfiguration config, Environment environment) {
        final KnapsackClientResource knapsackClientResource = new KnapsackClientResource(environment, config, new ClientBuilder());
        environment.jersey().register(knapsackClientResource);
        final KnapsackClientInfoResource knapsackClientInfoResource = new KnapsackClientInfoResource(config);
        environment.jersey().register(knapsackClientInfoResource);
    }

    private void registerHealthChecks(KnapsackClientConfiguration config, Environment environment) {
        environment.healthChecks().register("Knapsack server call health check", new KnapsackClientHealthCheck(environment, config));
    }

    private Problem createProblem() {
        int inventory = 32356000;
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(new Campaign("Acme", 2000000, 200));
        campaigns.add(new Campaign("Lorem", 3500000, 400));
        campaigns.add(new Campaign("Ipsum", 2300000, 210));
        campaigns.add(new Campaign("Dolor", 8000000, 730));
        campaigns.add(new Campaign("SIT", 10000000, 1000));
        campaigns.add(new Campaign("Amet", 1500000, 160));
        campaigns.add(new Campaign("Mauris", 1000000, 100));
        Problem problem = new Problem(inventory, campaigns);
        return problem;
    }
}
