package resources;

import configuration.KnapsackClientConfiguration;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

/**
 * To Punish and Enslave!
 * User: stelios
 * Date: 2016-09-22
 * Time: 15:44
 * Project: knapsack-service
 */
public class ClientBuilder {
    public Client buildClient(Environment environment, KnapsackClientConfiguration knapsackConfiguration) {
        return new JerseyClientBuilder(environment)
                .using(knapsackConfiguration.getJerseyClientConfiguration())
                .build("knapsack-client");
    }
}
