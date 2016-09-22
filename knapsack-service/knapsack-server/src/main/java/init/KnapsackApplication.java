package init;

import configuration.KnapsackConfiguration;
import endpoint.KnapsackEndpoint;
import health.KnapsackHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-16
 * Time: 19:39
 * Project: knapsack-service
 */
public class KnapsackApplication extends Application<KnapsackConfiguration> {
    private static final Logger logger = LoggerFactory.getLogger(KnapsackConfiguration.class);

    public static void main(String[] args) throws Exception {
        new KnapsackApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<KnapsackConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/htmlinfo", "index.html"));
    }

    @Override
    public void run(KnapsackConfiguration knapsackConfiguration, Environment environment) throws Exception {
        logger.info("Starting TelesignEndpoint in " + knapsackConfiguration.getEnvironment() + " environment");
        KnapsackEndpoint knapsackEndpoint = new KnapsackEndpoint(knapsackConfiguration);

        environment.jersey().register(knapsackEndpoint);

        registerHealthChecks(environment);
    }

    private void registerHealthChecks(Environment environment) {
        environment.healthChecks().register("Knapsack algorithm health check", new KnapsackHealthCheck());
    }
}
