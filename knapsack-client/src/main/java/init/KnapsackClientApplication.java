package init;

import configuration.KnapsackClientConfiguration;
import health.KnapsackClientHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resources.ClientBuilder;
import resources.KnapsackClientInfoResource;
import resources.KnapsackClientResource;

import javax.ws.rs.client.Client;

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
        Client client = new ClientBuilder().buildClient(environment, configuration);
        setupResources(configuration, environment, client);
        registerHealthChecks(configuration, environment, client);
    }

    private void setupResources(KnapsackClientConfiguration config, Environment environment, Client client) {
        final KnapsackClientResource knapsackClientResource = new KnapsackClientResource(client, environment, config);
        environment.jersey().register(knapsackClientResource);
        final KnapsackClientInfoResource knapsackClientInfoResource = new KnapsackClientInfoResource(config);
        environment.jersey().register(knapsackClientInfoResource);
    }

    private void registerHealthChecks(KnapsackClientConfiguration config, Environment environment, Client client) {
        environment.healthChecks().register("Knapsack server call health check", new KnapsackClientHealthCheck(client, config));
    }
}
